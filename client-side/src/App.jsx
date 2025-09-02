import { BrowserRouter , Routes , Route } from 'react-router-dom';
import './styles/main.css';
import Navigation from './components/Navigation/Navigation';
import NavLogo from './components/Navigation/NavLogo';
import NavItem from './components/Navigation/NavItem';
import { route } from './constants';
import Home from './views/Home';

function App() {
  return (
     <>
      <BrowserRouter>

        <Navigation>
          <NavLogo />
          <NavItem toUrl={route.HOME} text="Home" />
          <NavItem toUrl={route.SHOP} text="Shop" />
        </Navigation>

        <Routes>
          <Route path="/" element={<Home />}/>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
