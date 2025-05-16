import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { List, ListItem, ListItemText, Typography } from '@mui/material';

type Article = {
  id: string;
  title: string;
  content: string;
};

export default function ArticleList() {
  const [articles, setArticles] = useState<Article[]>([]);

  useEffect(() => {
    axios.get('http://localhost:8888/blog/query/article/articles')
      .then(res => setArticles(res.data))
      .catch(() => setArticles([]));
  }, []);

  return (
    <div>
      <Typography variant="h4" gutterBottom>Articles</Typography>
      <List>
        {articles.map(article => (
          <ListItem key={article.id}>
            <ListItemText primary={article.title} secondary={article.content} />
          </ListItem>
        ))}
      </List>
    </div>
  );
}