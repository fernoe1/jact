import { NavLink } from 'react-router-dom';

import Search from './Search/Search';

import { jactLogoLongTransparent } from '../../assets/images';

import s from './Navigation.module.css';
import AuthButton from './AuthButton/AuthButton';

const Navigation = () => {
    return (
        <div>
            <nav className={s.navbar}>
                <div className={s.navbar__left}>
                    <NavLink to="/">
                        <img className={s.navbar__logo} src={jactLogoLongTransparent} alt="logo"/>
                    </NavLink>
                    <ul className={s.navbar__links}>
                        <li className={s.navbar__links__link}>
                            <NavLink className={s.navlink} to="/">Home</NavLink>
                        </li>
                        <li className={s.navbar__links__link}>
                            <NavLink className={s.navlink} to="/shop">Shop</NavLink>
                        </li>
                    </ul>
                </div>
                
                <div className={s.navbar__right}>
                    <Search />
                    <AuthButton className={s.navbar__auth} text="Sign Up" variant="primary" />
                    <AuthButton className={s.navbar__auth} text="Sign In" />
                </div>
            </nav>
        </div>
    );
}

export default Navigation;