import { BrowserRouter , Routes , Route } from 'react-router-dom';
import Navigation from './components/Navigation/Navigation';
import Banner from './components/Banner/Banner';

function App() {
  return (
    <>
      <BrowserRouter>
        <Navigation />
        <Routes>

        </Routes>
        <Banner />
      </BrowserRouter>
    </>
  );
}

export default App;
