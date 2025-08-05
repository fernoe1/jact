import { BrowserRouter , Routes , Route } from 'react-router-dom';
import Navigation from './components/Navigation/Navigation';
import Banner from './components/Banner/Banner';
import Products from './components/Products/Products';

function App() {
  return (
    <>
      <BrowserRouter>
        <Navigation />
        <Routes>

        </Routes>
        <Banner />
        <Products />
      </BrowserRouter>
    </>
  );
}

export default App;
