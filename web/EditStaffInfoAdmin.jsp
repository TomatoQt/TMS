<%--
  Created by IntelliJ IDEA.
  User: TomatoMan
  Date: 2021/1/7
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>修改员工信息</title>
    <link rel="stylesheet" href="css/layui.css">
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">工夹具管理系统 后台</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img"> 管理员 <s:property value="#session.user.name"/>
                </a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="StaffProfileAdmin.jsp">基本资料</a>
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
                    <a class="" href="javascript:;">人事管理</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="./AddStaffPage">添加员工</a>
                        </dd>
                        <dd>
                            <a href="./fireStaffPage">开除员工</a>
                        </dd>
                        <dd>
                            <a href="./editStaffPage">维护员工信息</a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <form class="layui-form" action="editStaff">
                <div class="layui-form-item">
                    <label class="layui-form-label">员工</label>
                    <div class="layui-input-block">
<%--                        <input type="text" name="title" required  lay-verify="required" placeholder="请输入员工ID(必填)" autocomplete="off" class="layui-input">--%>
                        <s:select list="users" name="staff.id" listKey="id" listValue="id+'|'+name" lay-verify="required"></s:select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">员工姓名</label>
                    <div class="layui-input-block">
                        <input type="text" name="staff.name" required  lay-verify="required" placeholder="请输入员工新姓名(必填)" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">密码</label>
                    <div class="layui-input-block">
                        <input type="text" name="staff.password" required  lay-verify="required"  placeholder="请输入新密码(必填)" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">员工电话</label>
                    <div class="layui-input-block">
                        <input type="text" name="staff.phone" required  lay-verify="required" placeholder="请输入员工新电话(必填)" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">邮件地址</label>
                    <div class="layui-input-block">
                        <input type="text" name="staff.email" required  lay-verify="required" placeholder="请输入员工新邮件地址(必填)" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">选择权限</label>
                    <div class="layui-input-block">
                        <s:select name="staff.authority" lay-verify="required" list="#{'':'选择权限(必选)',0:'初级员工', 1:'高级员工',2:'监管员',3:'经理'}"/>
<%--                        <select name="city" lay-verify="required">--%>
<%--                            <option value=""></option>--%>
<%--                            <option value="0" selected>初级员工 Operator I</option>--%>
<%--                            <option value="1">高级员工 Operator II</option>--%>
<%--                            <option value="2">监管员 Supervisor</option>--%>
<%--                            <option value="3">经理 Manager</option>--%>
<%--                            <option value="4">系统管理员 Admin</option>--%>
<%--                        </select>--%>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">选择部门</label>
                    <div class="layui-input-block">
                        <s:select list="deptList" name="staff.deptId" lay-verify="required" listKey="id" listValue="id+'|'+name"></s:select>
<%--                        <select name="city" lay-verify="required">--%>
<%--                            <option value=""></option>--%>
<%--                            <option value="0" selected>部门0</option>--%>
<%--                            <option value="1">部门1</option>--%>
<%--                            <option value="2">部门2</option>--%>
<%--                            <option value="3">部门3</option>--%>
<%--                            <option value="4">部门4</option>--%>
<%--                        </select>--%>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">确定修改</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>

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