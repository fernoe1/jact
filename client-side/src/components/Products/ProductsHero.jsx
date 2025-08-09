import s from './ProductsHero.module.css';
import ProductsHeader from './ProductsHeader/ProductsHeader';
import ProductsRow from './ProductsRow/ProductsRow';

const Products = () => {

    return (
        <div className={s.container}>
            <ProductsHeader />
            <ProductsRow />
        </div>
    );
}

export default Products;