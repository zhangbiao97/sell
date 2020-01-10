package co.zhangbiao.sell.service.impl;

import co.zhangbiao.sell.SellApplicationTests;
import co.zhangbiao.sell.entity.ProductInfo;
import co.zhangbiao.sell.enums.ProductStatusEnum;
import co.zhangbiao.sell.service.ProductInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


public class ProductInfoServiceImplTest extends SellApplicationTests {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void findOne() {
        ProductInfo result = productInfoService.findOne("f7d8ee29-5270-4");
        Assert.assertNotNull(result);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> result = productInfoService.findUpAll();
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(1,2);
        Page<ProductInfo> result = productInfoService.findAll(pageRequest);
        Assert.assertNotEquals(0,result.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductStock(100);
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setProductPrice(new BigDecimal(30));
        productInfo.setProductName("皮皮虾");
        productInfo.setProductId(UUID.randomUUID().toString().substring(0,15));
        productInfo.setProductIcon("http://xxxxx.png");
        productInfo.setProductDescription("皮皮虾好吃！");
        productInfo.setCategoryType(1);
        ProductInfo result = productInfoService.save(productInfo);
        Assert.assertNotNull(result);
    }
}
