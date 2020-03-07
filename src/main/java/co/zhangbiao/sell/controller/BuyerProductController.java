package co.zhangbiao.sell.controller;

import co.zhangbiao.sell.entity.ProductCategory;
import co.zhangbiao.sell.entity.ProductInfo;
import co.zhangbiao.sell.service.CategoryService;
import co.zhangbiao.sell.service.ProductInfoService;
import co.zhangbiao.sell.utils.ResultVOUtil;
import co.zhangbiao.sell.vo.ProductInfoVO;
import co.zhangbiao.sell.vo.ProductVO;
import co.zhangbiao.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create By ZhangBiao
 * 2020-01-09
 */
@RequestMapping("/buyer/product")
@RestController
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private CategoryService categoryService;

    @Cacheable(cacheNames = "product", key = "123")
    @GetMapping("/list")
    public ResultVO list() {
        // 查询所有的上架商品
        List<ProductInfo> productInfos = productInfoService.findUpAll();
        // 获取商品类型ID
        List<Integer> categoryTypes = productInfos.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategorys = categoryService.findByCategoryTypeIn(categoryTypes);
        // 数据拼装
        ArrayList<ProductVO> productVOs = new ArrayList<>();
        for (ProductCategory productCategory : productCategorys) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOs = new ArrayList<>();
            for (ProductInfo productInfo : productInfos) {
                ProductInfoVO productInfoVO = new ProductInfoVO();
                BeanUtils.copyProperties(productInfo, productInfoVO);
                productInfoVOs.add(productInfoVO);
            }
            productVO.setProductInfos(productInfoVOs);
            productVOs.add(productVO);
        }
        return ResultVOUtil.success(productVOs);
    }

}
