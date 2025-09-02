import { cjactLogo } from "../../assets/images";
import s from './NavLogo.module.css';

const NavLogo = () => {
    return (
        <div className={s.navlogoContainer}>
            <img src={cjactLogo} className={s.navlogoImage} alt="logo" />
        </div>
    );
}

export default NavLogo;