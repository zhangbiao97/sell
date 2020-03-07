package co.zhangbiao.sell.service.impl;

import co.zhangbiao.sell.entity.SellerInfo;
import co.zhangbiao.sell.repository.SellerInfoRepository;
import co.zhangbiao.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create By ZhangBiao
 * 2020/3/6
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }

}
