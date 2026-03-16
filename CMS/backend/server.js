const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const fs = require('fs'); // thêm module fs

const app = express();
const PORT = 5000;

app.use(cors());
app.use(bodyParser.json());

// --- Load dữ liệu từ file posts.json ---
const POSTS_FILE = './posts.json';
let posts = [];

// Hàm load posts từ file
const loadPosts = () => {
  try {
    const data = fs.readFileSync(POSTS_FILE, 'utf-8');
    posts = JSON.parse(data || '[]');
  } catch (err) {
    posts = [];
  }
};

// Hàm lưu posts vào file
const savePosts = () => {
  fs.writeFileSync(POSTS_FILE, JSON.stringify(posts, null, 2));
};

// Load posts lúc khởi động server
loadPosts();

// --- Microkernel Plugin System ---
const plugins = {
  likePluginActive: true // bật/tắt plugin Like
};

// --- Core CMS ---
// Lấy danh sách bài viết
app.get('/posts', (req, res) => {
  res.json(posts);
});

// Tạo bài viết
app.post('/posts', (req, res) => {
  const { title, content } = req.body;
  const newPost = { id: Date.now(), title, content };
  if (plugins.likePluginActive) newPost.likes = 0;
  posts.push(newPost);
  savePosts(); // lưu vào file
  res.json(newPost);
});

// Xóa bài viết
app.delete('/posts/:id', (req, res) => {
  const { id } = req.params;
  posts = posts.filter(post => post.id != id);
  savePosts(); // lưu vào file
  res.json({ message: 'Deleted successfully' });
});

// API để Like Post
app.post('/posts/:id/like', (req, res) => {
  if (!plugins.likePluginActive)
    return res.status(403).json({ message: 'Like plugin inactive' });

  const { id } = req.params;
  const post = posts.find((p) => p.id == id);
  if (!post) return res.status(404).json({ message: 'Post not found' });

  post.likes = (post.likes || 0) + 1;
  savePosts(); // lưu vào file sau khi like
  res.json(post);
});

app.listen(PORT, () => console.log(`Server running on http://localhost:${PORT}`));