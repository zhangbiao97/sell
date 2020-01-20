package co.zhangbiao.sell.service.impl;

import co.zhangbiao.sell.dto.OrderDTO;
import co.zhangbiao.sell.enums.ResultEnum;
import co.zhangbiao.sell.exception.SellException;
import co.zhangbiao.sell.service.BuyerService;
import co.zhangbiao.sell.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create By ZhangBiao
 * 2020-01-19
 */
@Service
public class BuyerServiceImpl implements BuyerService {

    private static final Logger logger = LoggerFactory.getLogger(BuyerService.class);

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            logger.error("【取消订单】查不到该订单, orderId={}", orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        // 判断是否是自己的订单
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            logger.error("【查询订单】订单的openid不一致, openid={},orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
