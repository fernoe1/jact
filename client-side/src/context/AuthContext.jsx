import { createContext, useState, useEffect } from "react";
import { login as loginService, verifyToken } from "../services/authService";

const AuthContext = createContext();

const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [token, setToken] = useState(localStorage.getItem("token"));
    const [authenticated, setAuthenticated] = useState(false);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const verify = async () => {
            const storedToken = localStorage.getItem("token");

            if (storedToken) {
                try {
                    const verification = await verifyToken(storedToken);

                    if (verification.valid) {
                        setToken(storedToken);
                        setUser({
                            id: verification.id,
                            name: verification.name,
                            email: verification.email,
                        });
                        setAuthenticated(true);
                    } else {
                        logout();
                    }
                } catch {
                    logout();
                }
            }
            setLoading(false);
        };

        verify();
    }, []);

    const login = async (credentials) => {
        try {
            const response = await loginService(credentials);

            localStorage.setItem("token", response.token);
            setToken(response.token);

            if (response.user) {
                setUser(response.user);
                setAuthenticated(true);
            } else {
                await verify(response.token);
            }

            return response;
        } catch (err) {
            throw err;
        }
    };

    const verify = async (tokenToVerify) => {
        try {
            const verification = await verifyToken(tokenToVerify);
            if (verification.valid) {
                setUser({
                    id: verification.userId,
                    name: verification.name,
                    email: verification.email,
                });
                setAuthenticated(true);
            }
        } catch (err) {
            logout();
            throw err;
        }
    };

    const logout = () => {
        localStorage.removeItem("token");
        setToken(null);
        setUser(null);
        setAuthenticated(false);
    };

    if (loading) {
        return <div>Loading...</div>;
    }

    return (
        <AuthContext.Provider
            value={{
                user,
                token,
                authenticated,
                loading,
                login,
                logout,
            }}
        >
            {children}
        </AuthContext.Provider>
    );
};

export { AuthContext, AuthProvider };
