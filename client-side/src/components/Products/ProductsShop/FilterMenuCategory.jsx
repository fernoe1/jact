import s from './FilterMenuCategory.module.css';

const FilterMenuCategory = ( { category, hr, clickHandler } ) => {
    return (
        <div className={s.categoryContainer} onClick={() => clickHandler(category)}>
            <span className={s.category}>{category.name.charAt(0).toUpperCase() + category.name.slice(1)}</span>
            {hr && <hr className={s.categoryLine}/>}
        </div>
    );
}

export default FilterMenuCategory;