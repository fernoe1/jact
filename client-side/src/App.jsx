import { BrowserRouter , Routes , Route } from 'react-router-dom';
import Navigation from './components/Navigation/Navigation';
import Home from './views/Home';
import Shop from './views/Shop';
import Footer from './components/Footer/Footer';
import './styles/main.css';

function App() {
  return (
     <div className="layout">
      <BrowserRouter>
        <Navigation />
        <main className="content">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/shop" element={<Shop />} />
          </Routes>
        </main>
        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
