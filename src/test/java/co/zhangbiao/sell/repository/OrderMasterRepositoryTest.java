package co.zhangbiao.sell.repository;

import co.zhangbiao.sell.SellApplicationTests;
import co.zhangbiao.sell.entity.OrderMaster;
import co.zhangbiao.sell.enums.OrderStatusEnum;
import co.zhangbiao.sell.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderMasterRepositoryTest extends SellApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerAddress("河南省郑州市铭功路");
        orderMaster.setBuyerName("张三");
        orderMaster.setBuyerOpenid("123456789");
        orderMaster.setBuyerPhone("10010");
        orderMaster.setOrderAmount(new BigDecimal(100));
        orderMaster.setOrderId(UUID.randomUUID().toString().substring(0, 15));
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderMaster> result = orderMasterRepository.findByBuyerOpenid("123456789", pageRequest);
        Assert.assertNotEquals(0, result.getContent().size());
    }
}
