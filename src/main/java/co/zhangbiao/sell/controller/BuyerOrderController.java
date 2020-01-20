package co.zhangbiao.sell.controller;

import co.zhangbiao.sell.converter.OrderFormToOrderDTOConvert;
import co.zhangbiao.sell.dto.OrderDTO;
import co.zhangbiao.sell.enums.ResultEnum;
import co.zhangbiao.sell.exception.SellException;
import co.zhangbiao.sell.form.OrderForm;
import co.zhangbiao.sell.service.BuyerService;
import co.zhangbiao.sell.service.OrderService;
import co.zhangbiao.sell.utils.ResultVOUtil;
import co.zhangbiao.sell.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By ZhangBiao
 * 2020-01-19
 */
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    private static final Logger logger = LoggerFactory.getLogger(BuyerOrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     *
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderFormToOrderDTOConvert.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetails())) {
            logger.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        HashMap<String, String> map = new HashMap<>();
        map.put("orderId", orderDTO.getOrderId());
        return ResultVOUtil.success(map);
    }

    /**
     * 订单列表
     *
     * @param openid
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            logger.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> result = orderService.findList(openid, request);
        return ResultVOUtil.success(result.getContent());
    }

    /**
     * 订单详情
     *
     * @param openid
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        OrderDTO result = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(result);
    }

    /**
     * 取消订单
     *
     * @param openid
     * @param orderId
     * @return
     */
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid, orderId);
        return ResultVOUtil.success();
    }

}
