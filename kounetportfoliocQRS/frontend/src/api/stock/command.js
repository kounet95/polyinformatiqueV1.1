const BASE_URL = 'http://localhost:8888';

// Stock API - Command
export const addStock = async (stockData) => {
  try {
    const response = await fetch(`${BASE_URL}/stock/command/add`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(stockData),
    });
    return await response.json();
  } catch (error) {
    console.error('Error adding stock:', error);
    throw error;
  }
};