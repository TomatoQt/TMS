<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>登录</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> <!-- IE8兼容 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="./node_modules/normalize.css/normalize.css">
    <link rel="stylesheet" type="text/css" href="./node_modules/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./css/Login.css">
    <link rel="stylesheet" type="text/css" href="./node_modules/font-awesome/css/font-awesome.min.css">
    <link rel="shortcut icon" href="./images/favicon.ico">
    <%--    <script>--%>
    <%--        function check(){--%>
    <%--            var username = document.forms["myForm"]["username"].value;--%>
    <%--            var password = document.forms["myForm"]["password"].value;--%>
    <%--            if(username.length==0 || username.length>6){--%>
    <%--                alert("账号请输入1~6位的字符");--%>
    <%--                return false;--%>
    <%--            }--%>
    <%--            if(password.length==0 || password.length>6){--%>
    <%--                alert("密码请输入1~6位的字符");--%>
    <%--                return false;--%>
    <%--            }--%>
    <%--        }--%>
    <%--    </script>--%>
    <style type="text/css">
        .errorMessage li {
            list-style-type: none;
        }
        .errorMessage {
            padding: 0;
        }
    </style>
</head>
<body>
<s:fielderror fieldName="myMess" theme="simple"/>
<div class="login-bg" style="background-image: url(./images/login_bg.jpg);"></div>
<!-- 主体 -->
<div class="login-box">
    <img src="./images/company_logo.png" class="login-top-logo">
    <img src="./images/login_box_bg3.jpg" class="login-left-logo">
    <div class="login-form">
        <h2 id="title">工夹具智能管理系统</h2>
        <form name="myForm" method="post" action="loginAction">
            <div class="col-md-10 col-md-offset-1 login-input-box">
                <div class="form-group" class="login-tip-box">
                    <span style="color: red" ><s:property value="#session.message"/></span>
                </div>
                <div class="form-group has-feedback">
                    <label class="sr-only control-label" for="username">工号</label>
                    <div class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                        <%--                            <input type="text" class="form-control" id="username" placeholder="工号" maxlength="7">--%>
                        <s:textfield class="form-control" placeholder="工号" maxlength="7" name="user.id" id="username"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="sr-only control-label" for="password">密码</label>
                    <div class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                        <%--                            <input type="password" class="form-control" id="password" placeholder="密码" maxlength="12">--%>
                        <s:password name="user.password" class="form-control" id="password" placeholder="密码" maxlength="12"/>
                    </div>
                </div>
                <div class="form-group checkbox">
                    <label class="control-label checkbox-label"><input id="rememberCheckbox" type="checkbox">记住我</label>
                    <a id="resetPassword">申请重置密码</a>
                </div>
                <div id="login_btn">
                    <button class="btn btn-primary col-md-12"  name="submit" value="登录" id="loginBtn">登录</button>
                    <%--                    <s:submit class="btn btn-primary col-md-12" name="submit" value="登录"/>--%>

                    <!--                    <button class="btn btn-info col-md-12" id="visitorLoginBtn">游客登陆</button>-->
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 首次登录用户需要设置密码 -->
<div class="modal fade bs-example-modal-sm" id="setPwModal" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">更改初始密码</h4>
            </div>
            <div class="modal-body form-horizontal">
                <div class="form-group">
                    <label class="control-label col-sm-3" for="newPassword">新密码</label>
                    <div class="col-sm-8">
                        <input class="form-control" type="password" id="newPassword" placeholder="6-12位并且必须包含字母与数字" maxlength="12">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3" for="rePassword">确认密码</label>
                    <div class="col-sm-8">
                        <input class="form-control" type="password" id="rePassword" maxlength="12">
                    </div>
                </div>
                <button class="btn btn-primary center-block" id="newPwBtn">提交</button>
            </div>
        </div>
    </div>
</div>
<!-- 忘记密码申请重置 -->
<div class="modal fade" id="resetPwModal" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">密码重置申请</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <span class="col-md-offset-4  col-xs-offset-4" id="_tip"></span>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2" for="_UserID">工号</label>
                        <div class="col-md-10">
                            <input class="form-control" type="text" id="_UserID" maxlength="7">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2" for="Email">电子邮箱</label>
                        <div class="col-md-7">
                            <input class="form-control" type="text" id="Email" placeholder="请输入您已绑定的邮箱">
                        </div>
                        <button class="btn" id="getNum" type="button">获取验证码</button>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2" for="valiNum">验证码</label>
                        <div class="col-md-10">
                            <input class="form-control" type="text" id="valiNum" maxlength="4">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary col-md-offset-9 col-md-2" id="valiBtn">提交申请</button>
            </div>
        </div>
    </div>
</div>
<!-- 个别用户需要选择workcell -->
<div class="modal fade bs-example-modal-sm" id="chooseWorkcellModal" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">工作部门选择</h4>
            </div>
            <div class="modal-body form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-3" for="Workcell">工作部门</label>
                    <div class="col-md-7">
                        <select class="form-control" id="Workcell">
                            <option value='0'>请选择工作部门</option>
                        </select>
                    </div>
                </div>
                <button class="btn btn-primary center-block" id="workcellSubmitBtn">登录</button>
            </div>
        </div>
    </div>
</div>
<!-- 系统初始化弹窗 -->
<div class="modal fade" id="systemInitModal" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">系统初始化</h4>
            </div>
            <div class="modal-body form-horizontal">
                <!-- 验证身份 -->
                <div id="systemInitValiBox">
                    <div class="form-group">
                        <label class="control-label col-md-3" for="privateKeyInput">私钥</label>
                        <div class="col-md-7">
                            <input class="form-control" id="privateKeyInput" type="text" placeholder="请填写系统私钥">
                        </div>
                        <button class="btn" id="systemInitValiBtn">验证</button>
                    </div>
                </div>
                <!-- 实现操作 -->
                <div id="systemInitOperBox" style="display: none;">
                    <div class="add-workcell-box">
                        <p class="systeminit-title">添加工作部门并绑定账户</p>
                        <div class="form-group">
                            <label class="control-label col-md-3" for="systemInitAddWorkcellInput">工作部门名称</label>
                            <div class="col-md-8">
                                <input class="form-control" id="systemInitAddWorkcellInput" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3" for="hasAccountInput">是否已有账户</label>
                            <input id="hasAccountInput" type="checkbox" style="zoom: 1.3;margin: 0.7rem 1.2rem;">
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3" for="systemInitUserIDInput">工号</label>
                            <div class="col-md-8">
                                <input class="form-control" id="systemInitUserIDInput" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3" for="systemInitPswInput">密码</label>
                            <div class="col-md-8">
                                <input class="form-control" id="systemInitPswInput" type="password">
                            </div>
                        </div>
                        <div class="form-group" id="systemInitRepswBox">
                            <label class="control-label col-md-3" for="systemInitRepswInput">确认密码</label>
                            <div class="col-md-8">
                                <input class="form-control" id="systemInitRepswInput" type="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-primary center-block" id="addWorkcellBtn" type="button" style="width: 15%;">添加</button>
                        </div>
                    </div>
                    <div class="del-workcell-box">
                        <p class="systeminit-title">删除工作部门</p>
                        <div class="form-group">
                            <label class="control-label col-md-3" for="systemInitDelWorkcellInput">工作部门名称</label>
                            <div class="col-md-8">
                                <select class="form-control" id="systemInitDelWorkcellInput">
                                    <option value='0'>请选择工作部门</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-primary center-block" id="delWorkcellBtn" type="button" style="width: 15%;">删除</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 网站备案信息 -->
<div id="Copyright">
    Copyright © 湘ICP备19020319号-1
</div>
</body>

<script type="text/javascript" src="./node_modules/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="./node_modules/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./node_modules/jquery-cookie-v1.4.1/jquery.cookie.js"></script>
<script type="text/javascript" src="./Login.js"></script>
</html>