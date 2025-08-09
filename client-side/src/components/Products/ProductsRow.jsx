import { useState, useEffect } from 'react';
import ProductCard from './ProductCard';
import s from './ProductsRow.module.css';

const ProductsRow = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const response = await fetch('http://localhost:8080/products');
                if (!response.ok) {
                    throw new Error(await response.text())
                }

                const data = await response.json();
                console.log(data);
                setProducts(data);
            } catch (err) {
                console.error(err);
            }
        };

        fetchProducts();
    }, []);

    return (
        <div className={s.products}>
            {products.map(product => (
                <ProductCard key={product.id} product={product} />
            ))}
        </div>
    );
}

export default ProductsRow;