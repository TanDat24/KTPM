import React, { useState, useEffect } from 'react';
import axios from 'axios';
import LikePlugin from './plugins/LikePlugin'; // import plugin

function App() {
  const [posts, setPosts] = useState([]);
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');

  const API = 'http://localhost:5000/posts';

  useEffect(() => { fetchPosts(); }, []);

  const fetchPosts = async () => {
    const res = await axios.get(API);
    setPosts(res.data);
  };

  const addPost = async () => {
    if (!title || !content) return alert("Title & Content required!");
    await axios.post(API, { title, content });
    setTitle('');
    setContent('');
    fetchPosts();
  };

  const deletePost = async (id) => {
    await axios.delete(`${API}/${id}`);
    fetchPosts();
  };

  return (
    <div style={styles.container}>
      <h1 style={styles.header}>Simple CMS + Plugin</h1>

      <div style={styles.form}>
        <input placeholder="Title" value={title} onChange={e=>setTitle(e.target.value)} style={styles.input}/>
        <textarea placeholder="Content" value={content} onChange={e=>setContent(e.target.value)} style={styles.textarea}/>
        <button onClick={addPost} style={styles.addButton}>Add Post</button>
      </div>

      <div style={styles.postList}>
        {posts.map(post => (
          <div key={post.id} style={styles.postCard}>
            <h3>{post.title}</h3>
            <p>{post.content}</p>
            <button style={styles.deleteButton} onClick={() => deletePost(post.id)}>Delete</button>
            {/* Dùng plugin */}
            {post.likes !== undefined && (
              <LikePlugin postId={post.id} likes={post.likes} onUpdate={fetchPosts} />
            )}
          </div>
        ))}
        {posts.length === 0 && <p style={{ textAlign: 'center' }}>No posts yet.</p>}
      </div>
    </div>
  );
}

// CSS inline đơn giản
const styles = {
  container: {
    maxWidth: '600px',
    margin: '30px auto',
    fontFamily: 'Arial, sans-serif',
    padding: '0 15px'
  },
  header: {
    textAlign: 'center',
    marginBottom: '20px'
  },
  form: {
    display: 'flex',
    flexDirection: 'column',
    gap: '10px',
    marginBottom: '30px'
  },
  input: {
    padding: '10px',
    fontSize: '16px',
    borderRadius: '5px',
    border: '1px solid #ccc'
  },
  textarea: {
    padding: '10px',
    fontSize: '16px',
    borderRadius: '5px',
    border: '1px solid #ccc',
    resize: 'vertical',
    minHeight: '60px'
  },
  addButton: {
    padding: '10px',
    backgroundColor: '#4CAF50',
    color: 'white',
    border: 'none',
    borderRadius: '5px',
    cursor: 'pointer',
    fontWeight: 'bold'
  },
  postList: {
    display: 'flex',
    flexDirection: 'column',
    gap: '15px'
  },
  postCard: {
    border: '1px solid #ddd',
    borderRadius: '8px',
    padding: '15px',
    backgroundColor: '#f9f9f9',
    position: 'relative'
  },
  deleteButton: {
    position: 'absolute',
    top: '10px',
    right: '10px',
    padding: '5px 10px',
    backgroundColor: '#f44336',
    color: 'white',
    border: 'none',
    borderRadius: '5px',
    cursor: 'pointer'
  }
};

export default App;