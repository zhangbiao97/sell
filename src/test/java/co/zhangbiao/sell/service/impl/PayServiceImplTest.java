package co.zhangbiao.sell.service.impl;

import co.zhangbiao.sell.SellApplicationTests;
import co.zhangbiao.sell.dto.OrderDTO;
import co.zhangbiao.sell.service.OrderService;
import co.zhangbiao.sell.service.PayService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PayServiceImplTest extends SellApplicationTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @Test
    public void createTest(){
        OrderDTO orderDTO = orderService.findOne("1578665151263147506");
        payService.create(orderDTO);
    }

}
