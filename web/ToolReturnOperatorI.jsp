<%--
  Created by IntelliJ IDEA.
  User: TomatoMan
  Date: 2021/1/6
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>归还工夹具</title>
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
                            <a href="CheckRepairOperatorI.jsp">查看已提交的申请</a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <blockquote class="layui-elem-quote">
                当前有
                <s:property value="UnReturnToolList.size()"/>
                个工夹具未归还
            </blockquote>
            <form class="layui-form" action="ReturnApply">
                <div class="layui-form-item">
                    <label class="layui-form-label">选择归还的工夹具</label>
                    <div class="layui-input-block">
                        <s:select name="optionId" list="UnReturnToolList" listKey="id" listValue="id + ' | ' + name"></s:select>
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block">
<%--                        TODO 无备注相关属性--%>
                        <textarea name="desc" placeholder="请输入备注" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">归还</button>
<%--                        <s:submit  name="立即提交"  lay-filter="formDemo" class="layui-btn"  />--%>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
<%--                        <s:reset class="layui-btn layui-btn-primary" name="重置" />--%>
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
<script>
    //JavaScript代码区域
    layui.use('element', function() {
        var element = layui.element;

    });

    layui.use('table', function(){
        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 315
            ,url: '/demo/table/user/' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                ,{field: 'username', title: '用户名', width:80}
                ,{field: 'sex', title: '性别', width:80, sort: true}
                ,{field: 'city', title: '城市', width:80}
                ,{field: 'sign', title: '签名', width: 177}
                ,{field: 'experience', title: '积分', width: 80, sort: true}
                ,{field: 'score', title: '评分', width: 80, sort: true}
                ,{field: 'classify', title: '职业', width: 80}
                ,{field: 'wealth', title: '财富', width: 135, sort: true}
            ]]
        });

    });
</script>
</body>
</html>
