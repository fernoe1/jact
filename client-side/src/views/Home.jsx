import Banner from "../components/Banner/Banner";
import { useState, useEffect } from "react";
import Sneakers from "../components/Sneakers/Sneakers";
import BufferShop from "../components/Sneakers/BufferShop";


const Home = () => {
    const [sneakers, setSneakers] = useState([]);

    useEffect(() => {
        const fetchSneakers = async () => {
            try {
            const response = await fetch('http://localhost:8080/sneaker');
            const json = await response.json();

            if (!response.ok) {
                console.error(json.error);
            } else {
                setSneakers(json);
            }
            } catch (err) {
                console.error(err);
            }
        }

        fetchSneakers();
    }, [])

    return (
        <>
            <Banner />
            <BufferShop />
            <Sneakers sneakers={sneakers} />
        </>
    );
}

export default Home;