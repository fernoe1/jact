import s from './FilterMenu.module.css';

const FilterMenu = (props) => {
    return (
        <div className={s.filterMenu}>
            {props.children}
        </div>
    );
}

export default FilterMenu;