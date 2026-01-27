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
import Sneaker from './views/Sneaker';
import Signup from './views/Signup';
import Signin from './views/Signin';
import { HeartOutlined, ShoppingCartOutlined, UserOutlined } from '@ant-design/icons';
import { AuthContextProvider } from './context/AuthContext';
import { BasketContextProvider } from './context/BasketContext';
import { FavoriteContextProvider } from './context/FavoriteContext';

function App() {
  return (
     <>
     <AuthContextProvider>
      <BasketContextProvider>
        <FavoriteContextProvider>
        
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
                <NavItem toUrl={route.HOME} text={<UserOutlined />} />
                <NavItem toUrl={route.HOME} text={<ShoppingCartOutlined />} />
                <NavItem toUrl={route.HOME} text={<HeartOutlined />} />
              </NavRight>
            </Navigation>

            <Routes>
              <Route path="/" element={<Home />}/>
              <Route path="/sneakers/:id" element={<Sneaker />}/>
              <Route path="/signup" element={<Signup />} />
              <Route path="/signin" element={<Signin />} />
            </Routes>
          </BrowserRouter>

        </FavoriteContextProvider>
      </BasketContextProvider>
    </AuthContextProvider>
    </>
  );
}

export default App;
