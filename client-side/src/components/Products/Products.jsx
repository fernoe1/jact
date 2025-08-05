import { NavLink } from 'react-router-dom';
import s from './Products.module.css';
import ProductCard from './ProductCard';

const Products = () => {
    return (
        <div className={s.container}>
            <div className={s.container__header}>
                <h2 className={s.container__header__text}>Products</h2>
                <NavLink className={s.container__header__link}>See all</NavLink>
            </div>
            <div className={s.container__products}>
                <ProductCard product={{}}/>
                <ProductCard product={{}}/>
                <ProductCard product={{}}/>
                <ProductCard product={{}}/>
            </div>
        </div>
    );
}

export default Products;