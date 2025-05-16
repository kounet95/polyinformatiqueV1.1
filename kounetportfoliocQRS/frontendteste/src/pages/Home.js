import React from 'react';
import { Container, Typography, Box, Button } from '@mui/material';

export default function Home() {
  return (
    <Container maxWidth="md">
      <Box sx={{ textAlign: 'center', mt: 8 }}>
        <Typography variant="h2" gutterBottom>
          Bienvenue sur PolyInformatique
        </Typography>
        <Typography variant="h5" color="text.secondary" gutterBottom>
          Plateforme Blog & E-commerce moderne, rapide et sécurisée.
        </Typography>
        <Button variant="contained" color="primary" size="large" sx={{ mt: 4 }}>
          Découvrir le Blog
        </Button>
        <Button variant="outlined" color="primary" size="large" sx={{ mt: 4, ml: 2 }}>
          Explorer l’E-commerce
        </Button>
      </Box>
    </Container>
  );
}