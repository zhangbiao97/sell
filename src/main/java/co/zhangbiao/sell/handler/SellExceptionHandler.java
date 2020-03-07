package co.zhangbiao.sell.handler;

import co.zhangbiao.sell.exception.SellerAuthorizeException;
import co.zhangbiao.sell.properties.ProjectProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

}
