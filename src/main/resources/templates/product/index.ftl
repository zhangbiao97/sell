<html>
<#include "../common/header.ftl"/>
<body>
<div id="wrapper" class="toggled">

    <#-- 边栏 sidebar -->
    <#include "../common/nav.ftl"/>

    <#-- 主要内容 content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <input type="hidden" name="productId" value="${(productInfo.getProductId
                        ())!''}">
                        <div class="form-group">
                            <label for="exampleInputEmail1">名称</label><input type="text"
                                                                             value="${(productInfo
                                                                             .getProductName())!''}"
                                                                             name="productName"
                                                                             class="form-control"
                                                                             id="exampleInputEmail1"/>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">价格</label>
                            <input type="text"
                                   value="${(productInfo.getProductPrice())!''}"
                                   name="productPrice"
                                   class="form-control"
                                   id="exampleInputEmail1"/>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">库存</label>
                            <input type="text" value="${(productInfo.getProductStock())!''}"
                                   name="productStock"
                                   class="form-control"
                                   id="exampleInputEmail1"/>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">描述</label>
                            <input type="text" value="${(productInfo.getProductDescription())!''}"
                                   name="productDescription"
                                   class="form-control"
                                   id="exampleInputEmail1"/>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">图片</label>
                            <img src="${(productInfo.getProductIcon())!''}" alt=""
                                 width="100" height="100">
                            <input type="text" value="${(productInfo.getProductIcon())!''}"
                                   name="productIcon"
                                   class="form-control" id="exampleInputEmail1"/>
                        </div>
                        <div class="form-group">
                            <label>类目</label>
                            <select name="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.getCategoryType()
                                    }" <#if (productInfo.getCategoryType())?? && productInfo
                                    .getCategoryType() == category.getCategoryType()
                                    >selected</#if>>${category.getCategoryName()
                                        }</option>
                                </#list>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>