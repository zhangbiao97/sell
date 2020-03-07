package co.zhangbiao.sell.service;

/**
 * Create By ZhangBiao
 * 2020/3/7
 */
public interface SecKillService {

    /**
     * 查询秒杀活动特价商品的信息
     *
     * @param productId
     * @return
     */
    String querySecKillProductInfo(String productId);

    /**
     * 模拟不同用户秒杀同一商品的请求
     *
     * @param productId
     */
    void orderProductMockDiffUser(String productId);

}
