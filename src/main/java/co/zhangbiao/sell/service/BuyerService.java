package co.zhangbiao.sell.service;

import co.zhangbiao.sell.dto.OrderDTO;

/**
 * Create By ZhangBiao
 * 2020-01-19
 */
public interface BuyerService {

    /**
     * 订单详情
     *
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO findOrderOne(String openid,String orderId);

    /**
     * 取消订单
     *
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO cancelOrder(String openid,String orderId);

}
