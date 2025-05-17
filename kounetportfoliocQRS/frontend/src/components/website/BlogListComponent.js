import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { getAllArticles } from '../../api/blog/query';
import '../../assets/css/blog.css';

const BlogListComponent = () => {
  const [articles, setArticles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchArticles = async () => {
      try {
        setLoading(true);
        const data = await getAllArticles();
        // Adapte ici selon la structure de la réponse
        if (Array.isArray(data)) {
          setArticles(data);
        } else if (data && Array.isArray(data.articles)) {
          setArticles(data.articles);
        } else {
          setArticles([]);
        }
        setLoading(false);
      } catch (error) {
        console.error('Error fetching articles:', error);
        setError('Impossible de charger les articles. Veuillez réessayer plus tard.');
        setLoading(false);
      }
    };
    fetchArticles();
  }, []);

  if (loading) return <div className="loading">Chargement des articles...</div>;
  if (error) return <div className="error">{error}</div>;

  return (
    <main className="main">
      {/* ...le reste de ton composant inchangé... */}
    </main>
  );
};

export default BlogListComponent;