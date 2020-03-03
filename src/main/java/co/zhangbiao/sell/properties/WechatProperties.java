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

    private String mchId;

    private String mchKey;

    private String keyPath;

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

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getMchKey() {
        return mchKey;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }
}
