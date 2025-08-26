import s from './AuthButton.module.css';

const AuthButton = ({ text, variant, onClick }) => {
    return (
        <>
            <button className={`${s.button} ${variant === 'primary' ? s.primary : ''}`} onClick={onClick}>
                {text}
            </button>
        </>
    );
}

export default AuthButton;