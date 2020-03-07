package co.zhangbiao.sell.service.impl;

import co.zhangbiao.sell.SellApplicationTests;
import co.zhangbiao.sell.entity.SellerInfo;
import co.zhangbiao.sell.service.SellerService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SellerServiceImplTest extends SellApplicationTests {

    private static final String OPENID = "abcd";

    @Autowired
    private SellerService sellerService;

    @Test
    public void findSellerInfoByOpenid() {
        SellerInfo result = sellerService.findSellerInfoByOpenid(OPENID);
        Assert.assertEquals(OPENID, result.getOpenid());
    }


}
