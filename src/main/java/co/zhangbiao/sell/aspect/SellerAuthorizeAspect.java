package co.zhangbiao.sell.aspect;

import co.zhangbiao.sell.constant.CookieConstant;
import co.zhangbiao.sell.constant.RedisConstant;
import co.zhangbiao.sell.exception.SellerAuthorizeException;
import co.zhangbiao.sell.utils.CookieUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Create By ZhangBiao
 * 2020/3/6
 */
@Component
@Aspect
public class SellerAuthorizeAspect {

    private static final Logger logger = LoggerFactory.getLogger(SellerAuthorizeAspect.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * co.zhangbiao.sell.controller.Seller*.*(..)) && !execution" +
            "(public * co.zhangbiao.sell.controller.SellerUserController.*(..))")
    public void verify() {
    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            logger.warn("【登录校验】Cookie中查不到token");
            throw new SellerAuthorizeException();
        }
        //在redis中查询
        String token = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,
                cookie.getValue()));
        if (StringUtils.isEmpty(token)) {
            logger.warn("【登录校验】Redis中查询不到token");
            throw new SellerAuthorizeException();
        }
    }

}
