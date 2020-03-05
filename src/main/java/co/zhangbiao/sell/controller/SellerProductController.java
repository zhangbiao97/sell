package co.zhangbiao.sell.controller;

import co.zhangbiao.sell.entity.ProductInfo;
import co.zhangbiao.sell.exception.SellException;
import co.zhangbiao.sell.service.ProductInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Create By ZhangBiao
 * 2020/3/5
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    private static final Logger logger = LoggerFactory.getLogger(SellerProductController.class);

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 商品列表
     *
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "1") Integer size,
                             Map<String, Object> map) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    /**
     * 商品上架
     *
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId, Map<String, Object> map) {
        try {
            productInfoService.onSale(productId);
        } catch (SellException ex) {
            logger.error("【卖家商品】商品上架异常={}", ex.getMessage());
            map.put("msg", ex.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "商品上架成功");
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 商品下架
     *
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId, Map<String, Object> map) {
        try {
            productInfoService.offSale(productId);
        } catch (SellException ex) {
            logger.error("【卖家商品】商品下架异常={}", ex.getMessage());
            map.put("msg", ex.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "商品下架成功");
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

}
