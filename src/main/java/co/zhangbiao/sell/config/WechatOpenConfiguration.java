package co.zhangbiao.sell.config;

import co.zhangbiao.sell.properties.ProjectProperties;
import co.zhangbiao.sell.properties.WechatProperties;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create By ZhangBiao
 * 2020/3/6
 */
@EnableConfigurationProperties(ProjectProperties.class)
@Configuration
public class WechatOpenConfiguration {

    @Autowired
    private WechatProperties wechatProperties;

    @Bean
    public WxMpService wxOpenService() {
        WxMpService wxOpenService = new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(wxOpenConfigStorage());
        return wxOpenService;
    }

    @Bean
    public WxMpConfigStorage wxOpenConfigStorage() {
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId(wechatProperties.getOpenAppId());
        wxMpInMemoryConfigStorage.setSecret(wechatProperties.getOpenAppSecret());
        return wxMpInMemoryConfigStorage;
    }

}
