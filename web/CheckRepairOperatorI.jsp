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
    <title>查看提交的维修申请状态</title>
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

            <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                <ul class="layui-tab-title">
                    <li class="layui-this">未通过</li>
                    <li>已通过</li>
                </ul>
                <div class="layui-tab-content" style="height: 100px;">
                    <div class="layui-tab-item layui-show">
                        <table class="layui-table">
                            <colgroup>
                                <col width="150">
                                <col width="150">
                                <col width="150">
                                <col width="150">
                                <col width="150">
                                <col width="150">
                                <col width="150">
                                <col width="200">
                            </colgroup>
                            <thead>
                            <tr>
                                <th>维修记录ID</th>
                                <th>工夹具ID</th>
                                <th>图片</th>
                                <th>申请人ID</th>
                                <th>申请时间</th>
                                <th>审核人ID</th>
                                <th>审核时间</th>
                                <th>实时审核状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="waitList" >
                            <tr>
                                <td><s:property value="id"/></td>
                                <td><s:property value="toolId"/></td>
                                <td><img src="${img}"></td>
                                <td><s:property value="applicantId"/></td>
                                <td><s:property value="submitTime"/></td>
                                <td><s:property value="handler"/></td>
                                <td><s:property value="overTime"/></td>
                                <s:if test='%{result=="wait"}'>
                                    <td>
                                        <span style="color: #FFB800">等待审核中</span>
                                    </td>
                                </s:if>
                                <s:elseif test='%{result=="pass1"}'>
                                    <td>
                                        <span style="color: #5FB878">同意</span>
                                    </td>
                                </s:elseif>
                                <s:else>
                                    <td>
                                        <span style="color: #FD482C">不同意</span>
                                    </td>
                                </s:else>
                            </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                    <div class="layui-tab-item">
                        <table class="layui-table">
                            <colgroup>
                                <col width="150">
                                <col width="150">
                                <col width="150">
                                <col width="150">
                                <col width="150">
                                <col width="150">
                                <col width="150">
                                <col width="200">
                            </colgroup>
                            <thead>
                            <tr>
                                <th>维修记录ID</th>
                                <th>工夹具ID</th>
                                <th>图片</th>
                                <th>申请人ID</th>
                                <th>申请时间</th>
                                <th>审核人ID</th>
                                <th>审核时间</th>
                                <th>实时审核状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="finishList">
                            <tr>
                                <td><s:property value="id"/></td>
                                <td><s:property value="toolId"/></td>
                                <td><img src="${img}"></td>
                                <td><s:property value="applicantId"/></td>
                                <td><s:property value="submitTime"/></td>
                                <td><s:property value="handler"/></td>
                                <td><s:property value="overTime"/></td>
                                <s:if test='%{result=="wait"}'>
                                    <td>
                                        <span style="color: #FFB800">等待审核中</span>
                                    </td>
                                </s:if>
                                <s:elseif test='%{result=="pass1"}'>
                                    <td>
                                        <span style="color: #5FB878">同意</span>
                                    </td>
                                </s:elseif>
                                <s:else>
                                    <td>
                                        <span style="color: #FD482C">不同意</span>
                                    </td>
                                </s:else>
                            </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

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
