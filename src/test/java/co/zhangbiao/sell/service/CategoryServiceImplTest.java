package co.zhangbiao.sell.service;

import co.zhangbiao.sell.SellApplicationTests;
import co.zhangbiao.sell.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class CategoryServiceImplTest extends SellApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findOne() {
        ProductCategory category = categoryService.findOne(1);
        Assert.assertNotNull(category);
    }

    @Test
    public void findAll() {
        List<ProductCategory> categorys = categoryService.findAll();
        Assert.assertNotEquals(0,categorys.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> categoryTypes = Arrays.asList(1);
        List<ProductCategory> categorys = categoryService.findByCategoryTypeIn(categoryTypes);
        Assert.assertNotEquals(0,categorys);
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("服装");
        productCategory.setCategoryType(2);
        ProductCategory result = categoryService.save(productCategory);
        Assert.assertNotNull(result);
    }
}
