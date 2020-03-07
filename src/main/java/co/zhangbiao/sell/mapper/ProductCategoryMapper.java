package co.zhangbiao.sell.mapper;

import co.zhangbiao.sell.entity.ProductCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * Create By ZhangBiao
 * 2020/3/7
 */
public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name,category_type) values(#{categoryName" +
            "},#{categoryType})")
    int insert(ProductCategory productCategory);

    @Select("select * from product_category where category_type = #{categoryType}")
    ProductCategory findByCategoryType(Integer categoryType);

    ProductCategory selectByCategoryType(Integer categoryType);

}
