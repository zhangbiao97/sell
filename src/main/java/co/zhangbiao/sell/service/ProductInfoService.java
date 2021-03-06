package co.zhangbiao.sell.service;

import co.zhangbiao.sell.dto.CartDTO;
import co.zhangbiao.sell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品服务
 * <p>
 * Create By ZhangBiao
 * 2020-01-09
 */
public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有上架商品列表
     *
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     *
     * @param cartDTOS
     */
    void increaseStock(List<CartDTO> cartDTOS);

    /**
     * 减库存
     *
     * @param cartDTOS
     */
    void decreaseStock(List<CartDTO> cartDTOS);

    /**
     * 上架
     *
     * @param productId
     * @return
     */
    ProductInfo onSale(String productId);

    /**
     * 下架
     *
     * @param productId
     * @return
     */
    ProductInfo offSale(String productId);

}
