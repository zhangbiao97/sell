<html>
<#include "../common/header.ftl"/>
<body>
<div id="wrapper" class="toggled">
    <#-- 边栏 sidebar -->
    <#include "../common/nav.ftl"/>

    <#-- 主要内容content -->
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-4 column">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>
                                订单编号
                            </th>
                            <th>
                                订单总金额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                ${order.getOrderId()}
                            </td>
                            <td>
                                ${order.getOrderAmount()}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-8 column">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>
                                商品id
                            </th>
                            <th>
                                商品名称
                            </th>
                            <th>
                                价格
                            </th>
                            <th>
                                数量
                            </th>
                            <th>总额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list order.getOrderDetails() as detail>
                            <tr>
                                <td>
                                    ${detail.getProductId()}
                                </td>
                                <td>
                                    ${detail.getProductName()}
                                </td>
                                <td>
                                    ${detail.getProductPrice()}
                                </td>
                                <td>
                                    ${detail.getProductQuantity()}
                                </td>
                                <td>${detail.getProductPrice() * detail.getProductQuantity()}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <#if order.getOrderStatusEnum().getMessage() == "新订单">
                    <div class="col-md-12 column">
                        <a href="/sell/seller/order/finish?orderId=${order.getOrderId()}">
                            <button type="button" class="btn btn-primary btn-default">完结订单</button>
                        </a>
                        <a href="/sell/seller/order/cancel?orderId=${order.getOrderId()}">
                            <button type="button"
                                    class="btn
            btn-primary
            btn-danger">取消订单
                            </button>
                        </a>
                    </div>
                </#if>
            </div>
        </div>
    </div>
</div>
</body>
</html>