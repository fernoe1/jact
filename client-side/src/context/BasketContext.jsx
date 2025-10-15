import { createContext, useReducer } from "react";

export const BasketContext = createContext();

export const basketReducer = (state, action) => {
    switch (action.type) {
        case 'ADD':
            return {
                basket: [...state.basket, action.payload]
            };
        case 'REMOVE':
            return {
                basket: state.basket.filter(item => item.id !== action.payload.id)
            };
        case 'CLEAR':
            return {
                basket: []
            }
        default:
            return state;
    }
}

export const BasketContextProvider = ({ children }) => {
    const [state, dispatch] = useReducer(basketReducer, {
        basket: []
    });

    return (
        <BasketContext.Provider value={{...state, dispatch}}>
            { children }
        </BasketContext.Provider>
    );
}