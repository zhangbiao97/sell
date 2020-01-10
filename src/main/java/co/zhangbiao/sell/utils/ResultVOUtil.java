package co.zhangbiao.sell.utils;

import co.zhangbiao.sell.vo.ResultVO;

/**
 * Create By ZhangBiao
 * 2020-01-10
 */
public class ResultVOUtil {

    public static ResultVO success(Object data) {
        ResultVO result = new ResultVO();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO result = new ResultVO();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
