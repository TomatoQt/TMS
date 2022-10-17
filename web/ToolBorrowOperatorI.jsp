<%--
  Created by IntelliJ IDEA.
  User: TomatoMan
  Date: 2021/1/6
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>借工夹具</title>
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
            <form class="layui-form" action="BorrowApply" method="post" onsubmit="return checkPassword()">
                <input type="hidden" id="real_password" value="${sessionScope.user.password}">
                <div class="layui-form-item">
                    <label class="layui-form-label">工夹具名称</label>
                    <div class="layui-input-block">
                        <s:select name="ToolTypeId" list="list" listKey="id" listValue="name"
                                  headerKey="选择借用的工夹具"></s:select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">借用数量</label>
                    <div class="layui-input-inline">
                        <select name="quantity" lay-verify="">
                            <option value="1" selected>1个</option>
                            <option value="2">2个</option>
                            <option value="3">3个</option>
                        </select>
                    </div>
                    <div class="layui-form-mid layui-word-aux">请选择借用数量</div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">确认密码</label>
                    <div class="layui-input-inline">

                        <%--                        <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">--%>
                        <s:password name="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input"/>
                        <s:fielderror/>
                    </div>
                    <div class="layui-form-mid layui-word-aux">输入密码以验证身份</div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                        <%--                            <s:submit  name="立即提交"  lay-filter="formDemo" class="layui-btn"  />--%>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        <%--                            <s:reset class="layui-btn layui-btn-primary" name="重置" />--%>
                    </div>
                    <%--                    <s:actionerror/>--%>
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
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });

    layui.use('table', function () {
        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#demo'
            , height: 315
            , url: '/demo/table/user/' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left'}
                , {field: 'username', title: '用户名', width: 80}
                , {field: 'sex', title: '性别', width: 80, sort: true}
                , {field: 'city', title: '城市', width: 80}
                , {field: 'sign', title: '签名', width: 177}
                , {field: 'experience', title: '积分', width: 80, sort: true}
                , {field: 'score', title: '评分', width: 80, sort: true}
                , {field: 'classify', title: '职业', width: 80}
                , {field: 'wealth', title: '财富', width: 135, sort: true}
            ]]
        });

    });
</script>
</body>
</html>
