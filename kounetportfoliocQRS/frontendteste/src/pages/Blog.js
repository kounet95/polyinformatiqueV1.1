import React from 'react';
import { Container, Typography, Box, Button } from '@mui/material';

export default function Blog() {
  return (
    <Container maxWidth="md">
      <Box sx={{ textAlign: 'center', mt: 8 }}>
        <Typography variant="h3" gutterBottom>
          Bienvenue sur le Blog PolyInformatique
        </Typography>
        <Typography variant="h6" color="text.secondary" gutterBottom>
          Retrouvez nos derniers articles, actualités et conseils tech.
        </Typography>
        <Button variant="contained" color="primary" sx={{ mt: 4 }}>
          Voir les articles
        </Button>
      </Box>
    </Container>
  );
}