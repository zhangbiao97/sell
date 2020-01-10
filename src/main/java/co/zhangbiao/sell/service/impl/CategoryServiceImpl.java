package co.zhangbiao.sell.service.impl;

import co.zhangbiao.sell.entity.ProductCategory;
import co.zhangbiao.sell.repository.ProductCategoryRepository;
import co.zhangbiao.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create By ZhangBiao
 * 2020-01-09
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return categoryRepository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes) {
        return categoryRepository.findByCategoryTypeIn(categoryTypes);
    }

    @Override
    public ProductCategory save(ProductCategory category) {
        return categoryRepository.save(category);
    }
}
