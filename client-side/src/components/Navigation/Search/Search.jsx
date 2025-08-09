import { SearchOutlined } from "@ant-design/icons";
import s from './Search.module.css';

const Search = () => {
    return (
        <div className={s.searchbar}>
            <SearchOutlined className={s.searchbar__icon}/>
            <input className={s.searchbar__input} type="text" placeholder="Search product..."/>
        </div>
    );
}

export default Search;