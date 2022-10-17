<%--
  Created by IntelliJ IDEA.
  User: TomatoMan
  Date: 2021/1/7
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>查看报废申请</title>
    <link rel="stylesheet" href="css/layui.css">
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">工夹具管理系统 后台</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img"> 监管员 <s:property value="#session.user.name"/>
                </a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="StaffProfileSupervisor.jsp">基本资料</a>
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
                    <a class="" href="#">工夹具</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="AddToolTypeSupervisor.jsp">创建工夹具类别</a>
                        </dd>
                        <dd>
                            <a href="./editToolTypePage">修改工夹具类别</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="#">工夹具报废</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="./lookScrap">查看已提交的报废申请</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="#">工夹具采购</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="./lookBuy">查看已提交的采购申请</a>
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
                    <col width="100">
                    <col width="100">
                    <col width="150">
                    <col>
                    <col width="200">
                </colgroup>
                <thead>
                    <tr>
                        <th>报废记录id</th>
                        <th>工具名</th>
                        <th>申请人</th>
                        <th>剩余寿命</th>
                        <th>申请时间</th>
                        <th>申请理由</th>
                        <th>同意/驳回</th>
                    </tr>
                </thead>
                <tbody>
                <s:iterator value="list">
                    <tr>
                        <td>
                            <s:property value="id"/>
                        </td>
                        <td>
                            <s:property value="toolId"/>
                        </td>
                        <td>
                            <s:property value="applicantId"/>
                        </td>
                        <td>
                            <s:property value="restLife"/>
                        </td>
                        <td>
                            <s:property value="submitTime"/>
                        </td>
                        <td>
                            <s:property value="reason"/>
                        </td>
                        <td>
                            <s:a class="layui-btn" href="PermitScrapSuper_examineOK?scrapid=%{id}">同意</s:a>
                            <s:a class="layui-btn layui-btn-danger" href="PermitScrapSuper_examineNO?scrapid=%{id}">驳回</s:a>
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
