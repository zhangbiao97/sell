package co.zhangbiao.sell.service;

import co.zhangbiao.sell.dto.OrderDTO;

/**
 * 推送消息
 *
 * Create By ZhangBiao
 * 2020/3/6
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     *
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);

}
