import React from 'react';
import ReactDOM from 'react-dom/client';
import './css/website/index.css';
import './css/lecontinent/index.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
import 'bootstrap/dist/css/bootstrap.css'
import App from './App';
import { Provider } from 'react-redux';
import reportWebVitals from './reportWebVitals';
import store from './store';
import { BrowserRouter } from 'react-router-dom';
import NavBarComponent from "./components/NavBarComponent";
import FooterComponent from "./components/website/FooterComponent";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
      <BrowserRouter>
          <Provider store={store}>
              <NavBarComponent/>
              <App />
            </Provider>
          <FooterComponent/>
      </BrowserRouter>

  </React.StrictMode>
);

reportWebVitals();
