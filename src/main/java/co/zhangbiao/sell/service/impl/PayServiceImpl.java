package co.zhangbiao.sell.service.impl;

import co.zhangbiao.sell.dto.OrderDTO;
import co.zhangbiao.sell.enums.ResultEnum;
import co.zhangbiao.sell.exception.SellException;
import co.zhangbiao.sell.service.OrderService;
import co.zhangbiao.sell.service.PayService;
import co.zhangbiao.sell.utils.JsonUtil;
import co.zhangbiao.sell.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.BestPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create By ZhangBiao
 * 2020/3/3
 */
@Service
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "Spring Boot微信点餐系统订单";

    private static final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);

    @Autowired
    private BestPayService bestPayService;

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        logger.info("【微信支付】request={}", JsonUtil.toJson(payRequest));
        PayResponse response = bestPayService.pay(payRequest);
        logger.info("【微信支付】response={}", JsonUtil.toJson(response));
        return response;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //1、验证签名
        //2、支付的状态
        //3、支付金额
        //4、支付人（下单人==支付人）
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        logger.info("【微信支付】异步通知,payResponse={}", JsonUtil.toJson(payResponse));
        //查询订单
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        //判断订单是否存在
        if (orderDTO == null) {
            logger.error("【微信支付】异步通知,订单不存在,orderId={}", payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //判断金额是否一致
        if (!MathUtil.equals(payResponse.getOrderAmount(), orderDTO.getOrderAmount().doubleValue())) {
            logger.error("【微信支付】异步通知,订单金额不一致,orderId={},微信通知金额={},系统金额={}", payResponse.getOrderId(), payResponse.getOrderAmount(), orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }
        //修改订单的支付状态
        orderService.paid(orderDTO);
        return payResponse;
    }

    /**
     * 微信退款
     *
     * @param orderDTO
     * @return
     */
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        logger.info("【微信退款】request={}", JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        logger.info("【微信退款】response={}", JsonUtil.toJson(refundResponse));
        return refundResponse;
    }
}
