import { useAuthContext } from "./useAuthContext"

export const useBasket = () => {
    const { dispatch } = useAuthContext();

    const addBasket = async ( sneaker ) => {
        dispatch({
            type: "ADD",
            payload: sneaker
        });
    }

    const removeBasket = async ( sneaker ) => {
        dispatch({
            type: "REMOVE",
            payload: sneaker
        });
    }

    const clearBasket = async () => {
        dispatch({type: "CLEAR"});
    }
}