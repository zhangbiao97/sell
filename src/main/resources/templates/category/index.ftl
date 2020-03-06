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
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <input type="hidden" name="categoryId"
                               value="${(category.getCategoryId())!''}">
                        <div class="form-group">
                            <label for="exampleInputEmail1">名字</label><input type="text"
                                                                             value="${(category.getCategoryName())!''}"
                                                                             name="categoryName"
                                                                             class="form-control"
                                                                             id="exampleInputEmail1"/>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">type</label>
                            <input type="text"
                                   value="${(category.getCategoryType())!''}"
                                   name="categoryType"
                                   class="form-control"
                                   id="exampleInputEmail1"/>
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