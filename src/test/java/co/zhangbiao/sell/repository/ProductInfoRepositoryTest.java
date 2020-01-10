package co.zhangbiao.sell.repository;

import co.zhangbiao.sell.SellApplicationTests;
import co.zhangbiao.sell.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProductInfoRepositoryTest extends SellApplicationTests {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setCategoryType(1);
        productInfo.setProductDescription("美味！");
        productInfo.setProductIcon("http://xxxxx.png");
        productInfo.setProductId(UUID.randomUUID().toString().substring(0,15));
        productInfo.setProductName("红烧茄子");
        productInfo.setProductPrice(new BigDecimal(20));
        productInfo.setProductStatus(0);
        productInfo.setProductStock(100);
        ProductInfo result = productInfoRepository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> result = productInfoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0, result.size());
    }
}
