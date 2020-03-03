package co.zhangbiao.sell.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Create By ZhangBiao
 * 2020/3/2
 */
@ConfigurationProperties(prefix = "wechat")
public class WechatProperties {

    private String appid;

    private String secret;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
