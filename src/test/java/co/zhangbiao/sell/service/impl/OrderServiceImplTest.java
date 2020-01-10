package co.zhangbiao.sell.service.impl;

import co.zhangbiao.sell.SellApplicationTests;
import co.zhangbiao.sell.dto.OrderDTO;
import co.zhangbiao.sell.entity.OrderDetail;
import co.zhangbiao.sell.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;

public class OrderServiceImplTest extends SellApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("北京市朝阳区");
        orderDTO.setBuyerName("李四");
        orderDTO.setBuyerOpenid("12345678910");
        orderDTO.setBuyerPhone("138001380000");
        // 购物车
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail order1 = new OrderDetail();
        order1.setProductId("8b7e945f-2c68-4");
        order1.setProductQuantity(2);
        orderDetails.add(order1);
        orderDTO.setOrderDetails(orderDetails);
        // 创建订单
        orderService.create(orderDTO);
    }

    @Test
    public void findOne() {
        OrderDTO result = orderService.findOne("1578665151263147506");
        Assert.assertEquals("1578665151263147506", result.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0, 5);
        Page<OrderDTO> result = orderService.findList("12345678910", pageRequest);
        Assert.assertNotEquals(0, result.getTotalElements());
    }

    @Test
    public void cancel() {
        String orderId = "1578665151263147506";
        OrderDTO orderDTO = orderService.findOne(orderId);
        Assert.assertEquals(orderId, orderDTO.getOrderId());
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(new Integer(2), result.getOrderStatus());
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}
