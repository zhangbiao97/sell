<html>
<#include "../common/header.ftl"/>
<body>
<div id="wrapper" class="toggled">

    <#-- 边栏 sidebar -->
    <#include "../common/nav.ftl"/>

    <#-- 主要内容 content -->
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>
                                类目id
                            </th>
                            <th>
                                名字
                            </th>
                            <th>
                                type
                            </th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list categorys as category>
                            <tr>
                                <td>
                                    ${category.getCategoryId()}
                                </td>
                                <td>
                                    ${category.getCategoryName()}
                                </td>
                                <td>
                                    ${category.getCategoryType()}
                                </td>
                                <td>${category.getCreateTime()}</td>
                                <td>${category.getUpdateTime()}</td>
                                <td>
                                    <a href="/sell/seller/category/index?categoryId=${category
                                    .getCategoryId()
                                    }">修改</a></td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>