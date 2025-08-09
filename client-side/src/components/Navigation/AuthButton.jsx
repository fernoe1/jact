import s from './AuthButton.module.css';

const AuthButton = ({ text, variant }) => {
    return (
        <>
            <button className={`${s.button} ${variant === 'primary' ? s.primary : ''}`}>
                {text}
            </button>
        </>
    );
}

export default AuthButton;