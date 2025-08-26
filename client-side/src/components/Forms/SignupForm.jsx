import { useState } from 'react';
import s from './SignupForm.module.css';

const SignupForm = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errors, setErrors] = useState({
        name: null,
        email: null,
        password: null
    });

    const nameValidator = (str) => {
        if (str === '') {
            return "Full name is required"
        }

        if (str.length < 3) {
            return "Full name should be at least 4 characters long";
        }

        if (!str.includes(" ")) {
            return "Full name should include first and last name";
        }

        return null;
    }

    const inputOnChange = (e, setter, validator, field) => {
        const { value } = e.target;
        setter(value);
        const errorMsg = validator(value);

        setErrors((prev) => ({
            ...prev,
            [field]: errorMsg
        }));
    }

    const submitHandler = (e) => {
        e.preventDefault();
        console.log(name, email, password);
    }

    return (
        <form className={s.form} onSubmit={submitHandler}>
            <div className={s.formInputContainer}>
                <label htmlFor="name">{errors.name ? errors.name : "* Full name"}</label>
                <input
                    className={errors.email ? s.formInputError : s.formInput}
                    type="text" 
                    id="name" 
                    onChange={(e) => inputOnChange(e, setName, nameValidator, "name")}
                    value={name}
                    required
                />

                <label htmlFor="email">* Email</label>
                <input 
                    className={s.formInput}
                    type="email" 
                    id="email" 
                    onChange={(e) => {setEmail(e.target.value)}}
                    value={email}
                />

                <label htmlFor="password">* Password</label>
                <input 
                    className={s.formInput}
                    type="password" 
                    id="password" 
                    onChange={(e) => {setPassword(e.target.value)}}
                    value={password}
                />

                <button className={s.formSubmit} type="submit">Sign Up</button>
            </div>
        </form>
    );
}

export default SignupForm;