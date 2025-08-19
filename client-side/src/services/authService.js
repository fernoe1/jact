const BASE_URL = "http://localhost:8080/auth";

const register = async(user) => {
    const response = await fetch(`${BASE_URL}/register`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    });

    if (!response.ok) {
        throw new Error(await response.json().error || 'Registration failed');
    }

    return await response.json();
};

const login = async(credentials) => {
    const response = await fetch(`${BASE_URL}/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    });

    if (!response.ok) {
        throw new Error(await response.json().error || 'Login failed');
    }

    return await response.json();
};

export { register, login };