import { BrowserRouter , Routes , Route } from 'react-router-dom';
import Navigation from './components/Navigation/Navigation';

function App() {
  return (
    <>
      <BrowserRouter>
        <Navigation />
        <Routes>

        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
