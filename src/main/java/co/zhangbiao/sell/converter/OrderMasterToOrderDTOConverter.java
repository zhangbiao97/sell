package co.zhangbiao.sell.converter;

import co.zhangbiao.sell.dto.OrderDTO;
import co.zhangbiao.sell.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Create By ZhangBiao
 * 2020-01-10
 */
public class OrderMasterToOrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasters){
        List<OrderDTO> orderDTOS = orderMasters.stream()
                .map(e -> convert(e))
                .collect(Collectors.toList());
        return orderDTOS;
    }

}
