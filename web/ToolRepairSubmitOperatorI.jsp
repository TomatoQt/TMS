<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: TomatoMan
  Date: 2021/1/6
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>维修工夹具</title>
    <link rel="stylesheet" href="css/layui.css">
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">工夹具管理系统 后台</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img"> I级员工 <s:property value="#session.user.name"/>
                </a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="StaffProfileOperatorI.jsp">基本资料</a>
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
                    <a class="" href="javascript:;">工夹具</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="./lookReturnNumber">归还工夹具</a>
                        </dd>
                        <dd>
<%--                            <a href="ToolBorrowOperatorI.jsp">借出工夹具</a>--%>
                            <a href="./outTool">借出工夹具</a>
                        </dd>
                        <dd>
                            <a href="./lookBorrow">查看借出工夹具是否批准</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">工夹具报修</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="./repairTool">申请提交</a>
                        </dd>
                        <dd>
                            <a href="./checkRepair">查看已提交的申请</a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <form class="layui-form" action="RepairApply" method="post" enctype="multipart/form-data" onsubmit="return checkPassword()">
                <input type="hidden" id="real_password" value="${sessionScope.user.password}">
                <div class="layui-form-item">
                    <%--未还工夹具--%>
                    <label class="layui-form-label">选择需要维修的工夹具</label>
                    <div class="layui-input-block">
                          <s:select name="entity.toolId" list="toolList" listKey="id" listValue="name" headerKey="选择归还的工夹具"></s:select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">输入密码</label>
                    <div class="layui-input-inline">
<%--                        <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">--%>
                        <s:password name="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input"/>
                        <s:fielderror/>
                    </div>
                    <div class="layui-form-mid layui-word-aux">输入密码以确认身份</div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">上传图片</label>
                    <div class="layui-input-inline">
<%--                        <button type="button" class="layui-btn" id="test1">--%>
<%--                            <i class="layui-icon">&#xe67c;</i>上传--%>
<%--                        </button>--%>
                            <input type="file" name="upload" value="上传" />
                    </div>
                    <div class="layui-form-mid layui-word-aux">上传工夹具的图片</div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">
                        输入对工夹具损坏状况的描述
                    </label>
                    <div class="layui-input-block">
                        <s:textfield name="entity.description" placeholder="请输入内容" class="layui-textarea"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="formDemo">立即提交</button>
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
<script src="myjs.js"></script>
<script src="layui.all.js"></script>
<script src="layui.js"></script>
<script>
    layui.use('upload', function(){
        var upload = layui.upload;

        //执行实例
        var uploadInst = upload.render({
            elem: '#test1' //绑定元素
            ,url: '/upload/' //上传接口
            ,done: function(res){
                //上传完毕回调
            }
            ,error: function(){
                //请求异常回调
            }
        });
    });
</script>
</body>
</html>
