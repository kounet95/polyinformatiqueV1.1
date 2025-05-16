import React from 'react';
import { Container, Typography, Box, Button } from '@mui/material';

export default function Ecommerce() {
  return (
    <Container maxWidth="md">
      <Box sx={{ textAlign: 'center', mt: 8 }}>
        <Typography variant="h3" gutterBottom>
          Bienvenue sur la boutique PolyInformatique
        </Typography>
        <Typography variant="h6" color="text.secondary" gutterBottom>
          Découvrez nos produits et profitez d'une expérience d'achat moderne et fluide.
        </Typography>
        <Button variant="contained" color="primary" sx={{ mt: 4 }}>
          Voir les produits
        </Button>
      </Box>
    </Container>
  );
}