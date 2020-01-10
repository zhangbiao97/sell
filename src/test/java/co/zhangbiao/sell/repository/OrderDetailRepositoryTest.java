package co.zhangbiao.sell.repository;

import co.zhangbiao.sell.SellApplicationTests;
import co.zhangbiao.sell.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OrderDetailRepositoryTest extends SellApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(UUID.randomUUID().toString().substring(0, 15));
        orderDetail.setOrderId("2dc0a0a9-bf02-4");
        orderDetail.setProductIcon("http://xxxxxx.png");
        orderDetail.setProductId("8b7e945f-2c68-4");
        orderDetail.setProductName("皮皮虾");
        orderDetail.setProductPrice(new BigDecimal(20));
        orderDetail.setProductQuantity(2);
        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrOrderId() {
        List<OrderDetail> result = orderDetailRepository.findByOrderId("2dc0a0a9-bf02-4");
        Assert.assertNotEquals(0, result.size());
    }
}
