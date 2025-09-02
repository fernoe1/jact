import s from './Navigation.module.css';

const Navigation = ({ children }) => {
    return (
        <nav className={s.navbar}>
            { children }
        </nav>
    );
}

export default Navigation;