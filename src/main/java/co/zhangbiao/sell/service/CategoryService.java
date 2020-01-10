package co.zhangbiao.sell.service;

import co.zhangbiao.sell.entity.ProductCategory;

import java.util.List;

/**
 * 产品类型服务
 * <p>
 * Create By ZhangBiao
 * 2020-01-09
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);

    ProductCategory save(ProductCategory category);

}
