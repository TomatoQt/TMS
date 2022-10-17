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
    <title>添加工夹具类别</title>
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

            <s:form action="CreateToolType" method="post" theme="simple" onsubmit="return checkPassword()">
                <input type="hidden" id="real_password" value="${sessionScope.user.password}">
                <div class="layui-form-item">
                    <label class="layui-form-label">工夹具名字</label>
                    <div class="layui-input-block">
<%--                        <input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">--%>
                        <s:textfield name="title" lay-verify="required" placeholder="请输入工夹具名字" class="layui-input"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">密码</label>
                    <div class="layui-input-inline">
<%--                        <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">--%>
                        <s:password name="password" lay-verify="required" class="layui-input"/>
                    </div>
                    <div class="layui-form-mid layui-word-aux">输入密码以验证身份</div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">工夹具描述</label>
                    <div class="layui-input-block">
<%--                        <textarea name="desc" placeholder="请输入对该工夹具的描述信息" class="layui-textarea"></textarea>--%>
                        <s:textarea name="desc" class="layui-textarea"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </s:form>

        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="layui.all.js"></script>
<script src="myjs.js"></script>
</body>
</html>
