import s from './Breadcrumb.module.css';

const Breadcrumb = ( { category, clickable, clickHandler } ) => {
    return (
        <div className={s.breadcrumb}>
            {clickable ? (
                <span className={`${s.breadcrumb__link} ${s['breadcrumb__link--clickable']}`} onClick={() => clickHandler(category)}>
                    {category.name.charAt(0).toUpperCase() + category.name.slice(1)}
                </span>
            ) : (
                <span className={s.breadcrumb__link}>
                    {category.name.charAt(0).toUpperCase() + category.name.slice(1)}
                </span>
            )}
        </div>
    );
}

export default Breadcrumb;