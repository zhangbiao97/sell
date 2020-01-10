package co.zhangbiao.sell.utils;

import java.util.Random;

/**
 * 订单ID生成器
 * <p>
 * Create By ZhangBiao
 * 2020-01-10
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     *
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

}
