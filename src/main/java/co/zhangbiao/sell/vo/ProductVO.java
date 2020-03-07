package co.zhangbiao.sell.vo;

import co.zhangbiao.sell.entity.ProductInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 商品（包含商品类型）
 * <p>
 * Create By ZhangBiao
 * 2020-01-09
 */
public class ProductVO implements Serializable {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfos;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public List<ProductInfoVO> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(List<ProductInfoVO> productInfos) {
        this.productInfos = productInfos;
    }
}
