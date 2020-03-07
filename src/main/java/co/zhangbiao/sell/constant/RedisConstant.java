package co.zhangbiao.sell.constant;

/**
 * redis 常量
 * <p>
 * Create By ZhangBiao
 * 2020/3/6
 */
public interface RedisConstant {

    /**
     * token前缀
     */
    String TOKEN_PREFIX = "token_%s";

    /**
     * 到期时间，默认为2小时
     */
    Integer EXPIRE = 7200;

}
