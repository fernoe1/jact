import { NavLink } from "react-router-dom";

import s from './SaleHeader.module.css';

const SaleHeader = () => {
    return (
        <div className={s.saleHeader}>
            <h1 className={s.saleHeaderText}><NavLink className={s.saleHeaderTextLink}>Click here</NavLink> to see our products that are on sale!</h1>
        </div>
    );
}

export default SaleHeader;