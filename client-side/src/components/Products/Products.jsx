import s from './Products.module.css';
import ProductsHeader from './ProductsHeader';
import ProductsRow from './ProductsRow';

const Products = () => {

    return (
        <div className={s.container}>
            <ProductsHeader />
            <ProductsRow />
        </div>
    );
}

export default Products;