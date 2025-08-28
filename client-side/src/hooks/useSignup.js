import { useState } from "react";
import { useAuthContext } from "./useAuthContext";

export const useSignup = () => {
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [authError, setAuthError] = useState(null);
    const { dispatch } = useAuthContext();

    const signup = async ( name, email, password ) => {
        setIsSubmitting(true);

        const response = await fetch('http://localhost:8080/auth/register', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({ name, email, password })
        });

        const json = await response.json()

        if (!response.ok) {
            setIsSubmitting(false);
            setAuthError(json.error);
        }

        if (response.ok) {
            localStorage.setItem('token', json.token);
            dispatch({type: "LOGIN", payload: json.token});
            setIsSubmitting(false);
        }
    }

    return { signup, isSubmitting, authError }
}