const { createContext, useState, useEffect, useContext } = require("react");
import { login as loginService, verifyToken } from './services/authService';

const AuthContext = createContext();

export const AuthProvider = ( { children } ) => {
    const [user, setUser] = useState(null);
    const [token, setToken] = useState(localStorage.getItem('token'));
    const [authenticated, isAuthenticated] = useState(false);
    const [loading, isLoading] = useState(true);

    useEffect(() => {
        const verify = async () => {
            const storedToken = localStorage.getItem('token');

            if (storedToken) {
                try {
                    const verification = await verifyToken(storedToken);

                    if (verification.valid) {
                        setToken(storedToken);
                        setUser({
                            id: verification.id,
                            name: verification.name,
                            email: verification.email
                        });
                        isAuthenticated(true);
                    } else {
                        logout();
                    }
                } catch (err) {
                    logout();
                }
            }
            isLoading(false);
        };

        verify();
    }, []);

    const login = async (credentials) => {
        try {
            const response = await loginService(credentials);
            localStorage.setItem('token', response.token);
            setToken(response.token);
            setUser(response.token);
            isAuthenticated(true);

            return response;
        } catch (err) {
            throw err;
        }
    }

    const logout = () => {
        localStorage.removeItem('token');
        setToken(null);
        setUser(null);
        isAuthenticated(false);
    };

    if (loading) {
        return <div>Loading..</div>;
    }

    return (
        <AuthContext.Provider value={{
            user,
            token,
            authenticated,
            loading,
            login,
            logout
        }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext);