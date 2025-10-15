import { useContext } from "react";
import BasketContext from '../context/BasketContext';

export const useBasketContext = () => {
    const context = useContext(BasketContext);
    
    if (!context) {
        throw Error("useBasketContext should be used inside AuthContext scope");
    }
    
    return context;
}