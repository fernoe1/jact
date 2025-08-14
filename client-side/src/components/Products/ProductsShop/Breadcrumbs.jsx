import { useState, useEffect } from 'react';
import s from './Breadcrumbs.module.css';

const Breadcrumbs = (props) => {

    return (
        <nav aria-label="Breadcrumb" className={s.breadcrumbs}>
            <ul className={s.breadcrumbs__list}>
                {props.children}
            </ul>
        </nav>
    );
}

export default Breadcrumbs;