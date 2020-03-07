package co.zhangbiao.sell.controller;

import co.zhangbiao.sell.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By ZhangBiao
 * 2020/3/7
 */
@RestController
@RequestMapping("/skill")
public class SecKillController {

    private static final Logger logger = LoggerFactory.getLogger(SecKillController.class);

    @Autowired
    private SecKillService secKillService;

    /**
     * 查询秒杀活动特价商品的信息
     *
     * @param productId
     * @return
     */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId) {
        return secKillService.querySecKillProductInfo(productId);
    }

    /**
     * 秒杀，没有抢到获得"哎呦喂，xxxxxx"，抢到了会返回剩下的库存量
     *
     * @param productId
     * @return
     */
    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId) {
        logger.info("@skill request, productId：" + productId);
        secKillService.orderProductMockDiffUser(productId);
        return secKillService.querySecKillProductInfo(productId);
    }

}
