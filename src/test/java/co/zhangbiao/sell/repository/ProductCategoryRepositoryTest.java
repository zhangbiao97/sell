package co.zhangbiao.sell.repository;

import co.zhangbiao.sell.SellApplicationTests;
import co.zhangbiao.sell.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;


public class ProductCategoryRepositoryTest extends SellApplicationTests {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findByIdTest() {
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("电器");
        productCategory.setCategoryType(1);
        ProductCategory category = productCategoryRepository.save(productCategory);
        Assert.assertNotNull(category);
    }

    @Test
    public void saveTest2() {
        ProductCategory category = productCategoryRepository.findOne(1);
        Assert.assertNotNull(category);
        category.setCategoryName("电器5");
        ProductCategory updateCategory = productCategoryRepository.save(category);
        Assert.assertNotNull(updateCategory);
    }

    @Test
    public void findByCategoryIdInTest() {
        List<Integer> categoryIds = Arrays.asList(1);
        List<ProductCategory> result = productCategoryRepository.findByCategoryTypeIn(categoryIds);
        Assert.assertNotEquals(0, result.size());
    }

}