import { useBasketContext } from "./useBasketContext";
import { useAuthContext } from "./useAuthContext";

export const useBasket = () => {
  const { dispatch } = useBasketContext();
  const { user } = useAuthContext();

  const fetchBasket = async () => {
    if (!user?.basketId) return;
    const res = await fetch(`http://localhost:4000/baskets/${user.basketId}`);
    const data = await res.json();
    dispatch({ type: "SET", payload: data });
  };

  const addItem = async (sneakerId, size, price) => {
    await fetch(`http://localhost:4000/baskets/${user.basketId}/add`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ sneakerId, size, price })
    });

    fetchBasket();
  };

  const removeItem = async (sneakerId, size) => {
    await fetch(
      `http://localhost:4000/baskets/${user.basketId}/remove/${sneakerId}/${size}`,
      { method: "DELETE" }
    );

    fetchBasket();
  };

  const updateQty = async (sneakerId, size, changeBy) => {
    await fetch(
      `http://localhost:4000/baskets/${user.basketId}/update/${sneakerId}/${size}`,
      {
        method: "PATCH",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ changeBy })
      }
    );

    fetchBasket();
  };

  return {
    fetchBasket,
    addItem,
    removeItem,
    updateQty
  };
};
