import { NavLink } from "react-router-dom";

import s from './ProductsHeader.module.css';

const ProductsHeader = () => {
    return (
        <div className={s.header}>
            <h2 className={s.header__text}>Products</h2>
            <NavLink className={s.header__link} to={"/shop"}>See all</NavLink>
        </div>
    );
}

export default ProductsHeader;