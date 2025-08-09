import s from './Footer.module.css';

const Footer = () => {
    return (
        <footer className={s.footer}>
            <div className={s.logo__container}>
                <h3>Jact</h3>
            </div>
            <h3 className={s.developer}>Developed by <a href="https://github.com/fernoe1" target="_blank">Temirlan</a></h3>
            <h3 className={s.copyright}>© 2025</h3>
        </footer>
    );
}

export default Footer;