package co.zhangbiao.sell.handler;

import co.zhangbiao.sell.exception.SellException;
import co.zhangbiao.sell.exception.SellerAuthorizeException;
import co.zhangbiao.sell.properties.ProjectProperties;
import co.zhangbiao.sell.utils.ResultVOUtil;
import co.zhangbiao.sell.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Create By ZhangBiao
 * 2020/3/6
 */
@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectProperties projectProperties;

    /**
     * 拦截登录异常
     *
     * @return
     */
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:".concat(projectProperties.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectProperties.getSell())
                .concat("/sell/seller/login"));
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(value = SellException.class)
    public ResultVO handlerGlobalException(SellException ex) {
        ResultVO result = ResultVOUtil.error(ex.getCode(), ex.getMessage());
        return result;
    }

}
