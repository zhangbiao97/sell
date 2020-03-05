package co.zhangbiao.sell.utils;

import co.zhangbiao.sell.enums.CodeEnum;

/**
 * Create By ZhangBiao
 * 2020/3/4
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }

}
