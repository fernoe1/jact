import './styles/main.css';
import { BrowserRouter , Routes , Route } from 'react-router-dom';
import Navigation from './components/Navigation/Navigation';
import NavLogo from './components/Navigation/NavLogo';
import NavItem from './components/Navigation/NavItem';
import { route } from './constants';
import Home from './views/Home';
import NavLeft from './components/Navigation/Parts/NavLeft';
import NavMiddle from './components/Navigation/Parts/NavMiddle';
import NavRight from './components/Navigation/Parts/NavRight';

function App() {
  return (
     <>
      <BrowserRouter>

        <Navigation>
          <NavLeft>
            <NavItem toUrl={route.HOME} text="Home" />
            <NavItem toUrl={route.SHOP} text="Shop" />
          </NavLeft>
          <NavMiddle>
            <NavLogo />
          </NavMiddle>
          <NavRight>

          </NavRight>
        </Navigation>

        <Routes>
          <Route path="/" element={<Home />}/>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
