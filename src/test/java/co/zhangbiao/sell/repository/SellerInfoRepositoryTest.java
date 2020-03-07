package co.zhangbiao.sell.repository;

import co.zhangbiao.sell.SellApplicationTests;
import co.zhangbiao.sell.entity.SellerInfo;
import co.zhangbiao.sell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SellerInfoRepositoryTest extends SellApplicationTests {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abcd");
        SellerInfo result = sellerInfoRepository.save(sellerInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOpenid() {
        SellerInfo result = sellerInfoRepository.findByOpenid("abcd");
        Assert.assertNotNull(result);
    }

}
