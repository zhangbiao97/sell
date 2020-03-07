package co.zhangbiao.sell.repository;

import co.zhangbiao.sell.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create By ZhangBiao
 * 2020/3/6
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {

    SellerInfo findByOpenid(String openid);

}
