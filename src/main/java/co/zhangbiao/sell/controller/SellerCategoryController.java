package co.zhangbiao.sell.controller;

import co.zhangbiao.sell.entity.ProductCategory;
import co.zhangbiao.sell.exception.SellException;
import co.zhangbiao.sell.form.CategoryForm;
import co.zhangbiao.sell.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Create By ZhangBiao
 * 2020/3/6
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    private static final Logger logger = LoggerFactory.getLogger(SellerCategoryController.class);

    @Autowired
    private CategoryService categoryService;

    /**
     * 类目列表
     *
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> productCategories = categoryService.findAll();
        map.put("categorys", productCategories);
        return new ModelAndView("category/list", map);
    }

    /**
     * 类目新增/修改页面
     *
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId, Map<String, Object> map) {
        if (categoryId != null) {
            ProductCategory category = categoryService.findOne(categoryId);
            map.put("category", category);
        }
        return new ModelAndView("category/index", map);
    }

    /**
     * 类目新增/修改
     *
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm, BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }
        ProductCategory category = new ProductCategory();
        try {
            if (categoryForm.getCategoryId() != null) {
                category = categoryService.findOne(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm, category);
            categoryService.save(category);
        } catch (SellException ex) {
            logger.error("【商品类目】发生异常={}", ex.getMessage());
            map.put("msg", ex.getMessage());
            map.put("url", "/sell/seller/category/index");
        }
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }

}
