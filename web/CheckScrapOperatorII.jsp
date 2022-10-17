<%--
  Created by IntelliJ IDEA.
  User: TomatoMan
  Date: 2021/1/7
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>查看报废申请进度</title>
    <link rel="stylesheet" href="css/layui.css">
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">工夹具管理系统 后台</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img"> II级员工 <s:property value="#session.user.name"/>
                </a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="StaffProfileOperatorII.jsp">基本资料</a>
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
                            <a href="./lookBorrow">批准借出工夹具</a>
                        </dd>
                        <dd>
                            <a href="EditToolInfoOperatorII.jsp">修改工夹具信息</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="#">工夹具报修</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="./checkRepair">查看报修申请</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="#">工夹具报废</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="./scrapToolPage">申请提交</a>
                        </dd>
                        <dd>
                            <a href="./lookScrap">查看已提交的报废申请</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="#">工夹具采购</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="./buyToolPage">申请提交</a>
                        </dd>
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
                                <col width="150">
                                <col width="150">
                                <col width="150">
                                <col width="200">
                            </colgroup>
                            <thead>
                            <tr>
                                <th>报废记录ID</th>
                                <th>工夹具ID</th>
                                <th>剩余寿命</th>
                                <th>报废原因</th>
                                <th>申请人ID</th>
                                <th>申请时间</th>
                                <th>初审人ID</th>
                                <th>初审时间</th>
                                <th>终审人ID</th>
                                <th>终审时间</th>
                                <th>实时审核状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="waitList">
                                <tr>
                                    <td>
                                        <s:property value="id"/>
                                    </td>
                                    <td>
                                        <s:property value="toolId"/>
                                    </td>
                                    <td>
                                        <s:property value="restLife"/>
                                    </td>
                                    <td>
                                        <s:property value="reason"/>
                                    </td>
                                    <td>
                                        <s:property value="applicantId"/>
                                    </td>
                                    <td>
                                        <s:property value="submitTime"/>
                                    </td>
                                    <td>
                                        <s:property value="handlerId"/>
                                    </td>
                                    <td>
                                        <s:property value="handleTime"/>
                                    </td>
                                    <td>
                                        <s:property value="finalId"/>
                                    </td>
                                    <td>
                                        <s:property value="overTime"/>
                                    </td>
                                    <td>
                                        <s:if test='%{result=="wait"}'>
                                        <span style="color: #FFB800"> 等待审核中
                                        </s:if>
                                        <s:elseif test='%{result=="pass1"}'>
                                            <span style="color: #FFB800">通过初审，终审中
                                        </s:elseif>
                                        <s:elseif test='%{result=="pass2"}'>
                                           <span style="color: #5FB878"> 审批成功
                                        </s:elseif>
                                        <s:else>
                                           <span style="color: #FD482C"> 不通过
                                        </s:else>
                                    </span>
                                    </td>
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
                                <col width="150">
                                <col width="150">
                                <col width="150">
                                <col width="200">
                            </colgroup>
                            <thead>
                            <tr>
                                <th>报废记录ID</th>
                                <th>工夹具ID</th>
                                <th>剩余寿命</th>
                                <th>报废原因</th>
                                <th>申请人ID</th>
                                <th>申请时间</th>
                                <th>初审人ID</th>
                                <th>初审时间</th>
                                <th>终审人ID</th>
                                <th>终审时间</th>
                                <th>实时审核状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="finishList">
                                <tr>
                                    <td>
                                        <s:property value="id"/>
                                    </td>
                                    <td>
                                        <s:property value="toolId"/>
                                    </td>
                                    <td>
                                        <s:property value="restLife"/>
                                    </td>
                                    <td>
                                        <s:property value="reason"/>
                                    </td>
                                    <td>
                                        <s:property value="applicantId"/>
                                    </td>

                                    <td>
                                        <s:property value="submitTime"/>
                                    </td>
                                    <td>
                                        <s:property value="handlerId"/>
                                    </td>
                                    <td>
                                        <s:property value="handleTime"/>
                                    </td>
                                    <td>
                                        <s:property value="finalId"/>
                                    </td>
                                    <td>
                                        <s:property value="overTime"/>
                                    </td>
                                    <td>

                                            <s:if test='%{result=="wait"}'>
                                                <span style="color: #FFB800"> 等待审核中
                                            </s:if>
                                            <s:elseif test='%{result=="pass1"}'>
                                                <span style="color: #FFB800">通过初审，终审中
                                            </s:elseif>
                                            <s:elseif test='%{result=="pass2"}'>
                                               <span style="color: #5FB878"> 审批成功
                                            </s:elseif>
                                            <s:else>
                                               <span style="color: #FD482C"> 不通过
                                            </s:else>
                                        </span>
                                    </td>
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
