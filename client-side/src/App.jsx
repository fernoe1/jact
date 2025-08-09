import { BrowserRouter , Routes , Route } from 'react-router-dom';
import Navigation from './components/Navigation/Navigation';
import Home from './views/Home';
import Shop from './views/Shop';
import Footer from './components/Footer/Footer';

function App() {
  return (
    <>
      <BrowserRouter>
        <Navigation />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/shop" element={<Shop />} />
        </Routes>
        <Footer />
      </BrowserRouter>
    </>
  );
}

export default App;
