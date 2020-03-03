package co.zhangbiao.sell.config;

import co.zhangbiao.sell.properties.WechatProperties;
import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create By ZhangBiao
 * 2020/3/3
 */
@Configuration
public class PayConfiguration {

    @Autowired
    private WechatProperties wechatProperties;

    @Bean
    public BestPayService bestPayService(){
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config(){
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(wechatProperties.getAppid());
        wxPayH5Config.setAppSecret(wechatProperties.getSecret());
        wxPayH5Config.setMchId(wechatProperties.getMchId());
        wxPayH5Config.setMchKey(wechatProperties.getMchKey());
        wxPayH5Config.setKeyPath(wechatProperties.getKeyPath());
        return wxPayH5Config;
    }

}
