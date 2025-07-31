import { NavLink } from 'react-router-dom';

import Search from './Search';

import { jactLogoLongTransparent } from '../../assets/images';

import s from './Navigation.module.css';
import AuthButton from './AuthButton';

const Navigation = () => {
    return (
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
                    <li className={s.navbar__links__link}>
                        <NavLink className={s.navlink} to="/about">About</NavLink>
                    </li>
                </ul>
            </div>
            
            <div className={s.navbar__right}>
                <Search />
                <AuthButton text={'Sign Up'} />
                <AuthButton text={'Sign In'} />
            </div>
        </nav>
    );
}

export default Navigation;