import s from './AuthButton.module.css';

const AuthButton = ( {text} ) => {
    return (
        <>
            <button className={s.button}>{text}</button>
        </>
    );
}

export default AuthButton;