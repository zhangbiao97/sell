package co.zhangbiao.sell.exception;

import co.zhangbiao.sell.enums.ResultEnum;

/**
 * Create By ZhangBiao
 * 2020-01-10
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
