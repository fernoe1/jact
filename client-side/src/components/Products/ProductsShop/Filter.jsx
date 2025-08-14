import { useState } from 'react';
import s from './Filter.module.css';

const Filter = (props) => {
    const [open, setOpen] = useState(false);

    return (
        <div className={s.filter} onClick={() => setOpen(!open)}>
            <div className={s.lineWrapper}>
                <hr className={s.filter__line} />
                {open && props.children}
            </div>
            <span className={s.filter__icon}>{props.icon}</span>
        </div>
    );
}

export default Filter;