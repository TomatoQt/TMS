<%--
  Created by IntelliJ IDEA.
  User: TomatoMan
  Date: 2021/1/7
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>采购申请批准</title>
    <link rel="stylesheet" href="css/layui.css">
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">工夹具管理系统 后台</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img"> 经理 <s:property value="#session.user.name"/>
                </a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="StaffProfileManager.jsp">基本资料</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="./logout">退出</a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">采购入库</a>
                    <dl class="layui-nav-child">
                        <dd>
<%--                            <a href="BuyPermitManager.jsp">查看未处理的申请</a>--%>
                            <a href="./lookBuy">查看未处理的申请</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">工夹具报废</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="./lookScrap">查看已提交的申请</a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">

            <table class="layui-table">
                <colgroup>
                    <col width="100">
                    <col width="100">
                    <col width="150">
                    <col width="150">
                    <col>
                    <col width="200">
                </colgroup>
                <thead>
                <tr>
                    <th>申请员工</th>
                    <th>工夹具</th>
                    <th>数量</th>
                    <th>申请时间</th>
                    <th>申请理由</th>
                    <th>同意/驳回</th>
                </tr>
                </thead>
                <tbody>
                    <s:iterator value="list">
                        <tr>
                            <td>
                                <s:property value="staffId"/>
                            </td>
                            <td>
                                <s:property value="toolTypeId"/>
                            </td>
                            <td>
                                <s:property value="quantity"/>
                            </td>
                            <td>
                                <s:property value="submitTime"/>
                            </td>
                            <td>
                                不够用
                            </td>
                            <td>
                                <s:a class="layui-btn" href="PermitBuyManager_finalExamineOK?buyid=%{id}">同意</s:a>
                                <s:a class="layui-btn layui-btn-danger" href="PermitBuyManager_finalExamineNO?buyid=%{id}">驳回</s:a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>

        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="layui.all.js"></script>
</body>
</html>