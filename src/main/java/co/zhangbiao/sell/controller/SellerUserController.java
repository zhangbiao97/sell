package co.zhangbiao.sell.controller;

import co.zhangbiao.sell.constant.CookieConstant;
import co.zhangbiao.sell.constant.RedisConstant;
import co.zhangbiao.sell.entity.SellerInfo;
import co.zhangbiao.sell.enums.ResultEnum;
import co.zhangbiao.sell.properties.ProjectProperties;
import co.zhangbiao.sell.service.SellerService;
import co.zhangbiao.sell.utils.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Create By ZhangBiao
 * 2020/3/6
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    private static final Logger logger = LoggerFactory.getLogger(SellerUserController.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectProperties projectProperties;

    @Autowired
    private SellerService sellerService;

    /**
     * 卖家登录
     *
     * @param openid
     * @param response
     * @param map
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response, Map<String, Object> map) {
        //1、通过openid查询卖家信息
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null) {
            logger.error("【卖家登录】出现异常={}", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        //2、设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid,
                expire,
                TimeUnit.SECONDS);
        //3、设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
        return new ModelAndView("redirect:" + projectProperties.getSell() + "/sell/seller/order/list");
    }

    /**
     * 卖家登出
     *
     * @param request
     * @param response
     * @param map
     * @return
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response,
                               Map<String, Object> map) {
        //1、从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //2、清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            //3、清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }

}
