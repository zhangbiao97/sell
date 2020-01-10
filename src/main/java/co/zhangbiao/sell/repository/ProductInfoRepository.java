package co.zhangbiao.sell.repository;

import co.zhangbiao.sell.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Create By ZhangBiao
 * 2020-01-09
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer status);

}
