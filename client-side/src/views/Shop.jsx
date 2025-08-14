import Breadcrumbs from "../components/Products/ProductsShop/Breadcrumbs";
import Buffer from "../components/Navigation/Buffer/Buffer";
import { useEffect, useState } from "react";
import Filter from "../components/Products/ProductsShop/Filter";
import { FilterOutlined } from "@ant-design/icons";
import Breadcrumb from "../components/Products/ProductsShop/Breadcrumb";
import FilterMenu from "../components/Products/ProductsShop/FilterMenu";
import FilterMenuCategory from "../components/Products/ProductsShop/FilterMenuCategory";
import ProductsRow from "../components/Products/ProductsRow/ProductsRow";

const Shop = () => {
    const [breadcrumbs, setBreadcrumbs] = useState([{
        id: 0,
        name: "shop",
        parent_id: null
    }]);

    const addBreadcrumb = (selectedCategory) => {
        setBreadcrumbs(prev => [...prev, selectedCategory]);
        setCurrentCategory(selectedCategory);
    }

    const goBackBreadcrumb = (selectedBreadcrumb) => {
        setBreadcrumbs(prev => {
            const index = prev.findIndex(breadcrumb => breadcrumb.id === selectedBreadcrumb.id);
            
            return prev.slice(0, index + 1);
        });
        setCurrentCategory(selectedBreadcrumb);
    }

    const [currentCategory, setCurrentCategory] = useState({
        id: 0,
        name: "shop",
        parent_id: null
    });

    const [subCategories, setSubCategories] = useState([]);

    useEffect(() => {
        const fetchSubCategories = async () => {
            try {
                const response = await fetch(`http://localhost:8080/categories?parentId=${currentCategory.id}`);
                const json = await response.json();
                setSubCategories(json);
            } catch (err) {
                console.error(err);
            }
        }

        fetchSubCategories();
    }, [currentCategory]);

    return (
        <>
            <Buffer />
            <Breadcrumbs>
                {breadcrumbs.map((category, index) => {
                    const isLast = index === breadcrumbs.length - 1;

                    return isLast ? (
                        <Breadcrumb category={category} />
                    ) : (
                        <Breadcrumb category={category} clickable={true} clickHandler={goBackBreadcrumb}/>
                    );
                })}

                <Filter icon={<FilterOutlined />}>
                    <FilterMenu>
                        {subCategories?.map((category, index) => (
                            <FilterMenuCategory key={category.id} category={category} hr={index !== subCategories.length - 1} clickHandler={addBreadcrumb}/>
                        ))}
                    </FilterMenu>
                </Filter>
            </Breadcrumbs>
            <ProductsRow categoryId={currentCategory.id}/>
        </>
    );
}

export default Shop;