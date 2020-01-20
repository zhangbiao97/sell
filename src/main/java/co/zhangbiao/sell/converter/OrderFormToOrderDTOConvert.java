package co.zhangbiao.sell.converter;

import co.zhangbiao.sell.dto.OrderDTO;
import co.zhangbiao.sell.entity.OrderDetail;
import co.zhangbiao.sell.enums.ResultEnum;
import co.zhangbiao.sell.exception.SellException;
import co.zhangbiao.sell.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By ZhangBiao
 * 2020-01-19
 */
public class OrderFormToOrderDTOConvert {

    private static final Logger logger = LoggerFactory.getLogger(OrderFormToOrderDTOConvert.class);

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        List<OrderDetail> orderDetails = new ArrayList<>();
        try {
            orderDetails = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception ex) {
            logger.error("【对象转换】转换错误, string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetails(orderDetails);
        return orderDTO;
    }
}
