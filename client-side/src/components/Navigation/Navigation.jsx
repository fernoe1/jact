import { useState, useEffect } from 'react';
import s from './Navigation.module.css';

const Navigation = ({ children }) => {
    const [scrolled, isScrolled] = useState(false);

    useEffect(() => {
        const handleScroll = () => {
            isScrolled(window.scrollY > 0);
        }

        window.addEventListener("scroll", handleScroll);
        
        return () => window.removeEventListener("scroll", handleScroll);
    }, []);

    return (
        <div className={`${s.navbarContainer} ${scrolled ? s.scrolled : ''}`}>
            <nav className={s.navbar}>
                { children }
            </nav>
        </div>
    );
}

export default Navigation;