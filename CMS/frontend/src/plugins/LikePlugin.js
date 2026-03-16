// src/plugins/LikePlugin.js
import React from 'react';
import axios from 'axios';

const API = 'http://localhost:5000/posts';

export default function LikePlugin({ postId, likes, onUpdate }) {
  const handleLike = async () => {
    try {
      await axios.post(`${API}/${postId}/like`);
      if (onUpdate) onUpdate(); // gọi fetchPosts() để reload
    } catch (error) {
      console.error("Like failed:", error);
      alert("Cannot like post. Plugin might be inactive.");
    }
  };

  return (
    <button 
      style={{
        marginLeft: '10px',
        padding: '5px 10px',
        backgroundColor: '#ff4081',
        color: 'white',
        border: 'none',
        borderRadius: '5px',
        cursor: 'pointer'
      }}
      onClick={handleLike}
    >
      Like {likes || 0}
    </button>
  );
}