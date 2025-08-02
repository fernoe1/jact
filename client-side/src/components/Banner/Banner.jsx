import { jactHomeTransparent } from '../../assets/images';
import { ArrowRightOutlined } from '@ant-design/icons';
import { NavLink } from 'react-router-dom';

import s from './Banner.module.css';

const Banner = () => {
    return (
        <div className={s.banner}>
            <div className={s.banner__left}>
                <h1 className={s.banner__title}><strong>Ja</strong>karta and rea<strong>ct</strong></h1>
                <p className={s.banner__title__description}>A demo e-commerce website built using Jakarta back and React front</p>
                <NavLink className={s.banner__left__button}>
                    <p>Shop now!&nbsp;&nbsp;&nbsp;</p> <ArrowRightOutlined />
                </NavLink>
            </div>
            <div className={s.banner__right}>
                <img className={s.banner__right__image} src={jactHomeTransparent} />
            </div>
        </div>
    );
}

export default Banner;