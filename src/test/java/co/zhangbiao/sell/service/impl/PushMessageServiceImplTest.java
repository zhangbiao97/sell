package co.zhangbiao.sell.service.impl;

import co.zhangbiao.sell.SellApplicationTests;
import co.zhangbiao.sell.dto.OrderDTO;
import co.zhangbiao.sell.service.OrderService;
import co.zhangbiao.sell.service.PushMessageService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PushMessageServiceImplTest extends SellApplicationTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PushMessageService pushMessageService;

    @Test
    public void orderStatus(){
        OrderDTO orderDTO = orderService.findOne("2dc0a0a9-bf02-4");
        pushMessageService.orderStatus(orderDTO);
    }

}
