package co.zhangbiao.sell.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By ZhangBiao
 * 2020-01-20
 */
@RestController
@RequestMapping("/weixin")
public class WeixinController {

    private static final Logger logger = LoggerFactory.getLogger(WeixinController.class);

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        logger.info("进入auth方法.....");
        logger.info("code={}", code);
    }

}
