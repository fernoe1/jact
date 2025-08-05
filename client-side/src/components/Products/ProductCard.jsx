import { productTemplateTransparent } from '../../assets/images';
import s from './ProductCard.module.css';

const ProductCard = ({ product }) => {
    const title = product.title || "template"
    const imageUri = product.imageUri || productTemplateTransparent;
    const price = product.price || 0;

    return (
        <div className={s.card}>
            <div className={s.card__image__container}>
                <img className={s.card__image} src={imageUri} alt="Product image"/>
            </div>
            <h3 className={s.product__name}>{title}</h3>
            <h3 className={s.product__price}>{price}$</h3>
        </div>
    );
}

export default ProductCard;