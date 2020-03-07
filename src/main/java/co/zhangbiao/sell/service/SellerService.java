package co.zhangbiao.sell.service;

import co.zhangbiao.sell.entity.SellerInfo;

/**
 * 卖家端服务
 *
 * Create By ZhangBiao
 * 2020/3/6
 */
public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     *
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);

}
