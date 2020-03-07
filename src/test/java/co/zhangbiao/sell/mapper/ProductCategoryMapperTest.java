package co.zhangbiao.sell.mapper;

import co.zhangbiao.sell.SellApplicationTests;
import co.zhangbiao.sell.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductCategoryMapperTest extends SellApplicationTests {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Test
    public void insert() {
        ProductCategory category = new ProductCategory();
        category.setCategoryName("张彪最不爱");
        category.setCategoryType(100);
        int result = productCategoryMapper.insert(category);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryType() {
        ProductCategory productCategory = productCategoryMapper.findByCategoryType(100);
        Assert.assertEquals(100, productCategory.getCategoryType().longValue());
    }

    @Test
    public void selectByCategoryType() {
        ProductCategory category = productCategoryMapper.selectByCategoryType(100);
        Assert.assertNotNull(category);
    }

}
