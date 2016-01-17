<%@ page import="pers.evan.fastrepair.exception.BadRequestParameterException" %>
<%@ page import="pers.evan.fastrepair.exception.BadUpdateQueryException" %>
<%@ page import="pers.evan.fastrepair.exception.UserNotFoundException" %>
<%@ page import="pers.evan.fastrepair.model.Employee" %>
<%@ page import="pers.evan.fastrepair.service.EmployeeService" %>
<%@ page import="pers.evan.fastrepair.service.impl.EmployeeServiceImpl" %>
<%@ page import="pers.evan.fastrepair.util.AppContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@ page errorPage="error.jsp"--%>

<%
    //String action = request.getParameter("action");

    /*
    if (action != null && action.trim().equals("logout")) {
        session.setAttribute("employee", null);
    }

    if (action != null && action.trim().equals("login")) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        EmployeeService employeeService = new EmployeeServiceImpl();

        Employee employee = null;

        try {
            employee = employeeService.employeeLogin(username, password);

            employeeService.close();
        } catch (BadUpdateQueryException e) {
            //e.printStackTrace();
            response.getWriter().write("<h3 class=\"text-center\" style=\"color:red;\">数据库错误，请联系客服。</h3>");
            employeeService.close();
        } catch (UserNotFoundException e) {
            //e.printStackTrace();
            response.getWriter().write("<h3 class=\"text-center\" style=\"color:red;\">用户不存在，请重新登陆。</h3>");
            employeeService.close();
        } catch (BadRequestParameterException e) {
            response.getWriter().write("<h3 class=\"text-center\" style=\"color:red;\">不规范请求。</h3>");
            //e.printStackTrace();
            employeeService.close();
        }

        if (employee != null) {
            session.setAttribute("employee", employee);
            response.sendRedirect("index.jsp");
        }
    }
    */
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Fast Repair Employee,Tool and Warehouse Management System</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!--
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
    -->
    <link rel="stylesheet" type="text/css" href="<%=AppContext.getBaseUrl()%>/resource/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="<%=AppContext.getBaseUrl()%>/resource/lib/font-awesome/css/font-awesome.css">

    <script src="<%=AppContext.getBaseUrl()%>/resource/lib/jquery-1.11.1.min.js" type="text/javascript"></script>


    <link rel="stylesheet" type="text/css" href="<%=AppContext.getBaseUrl()%>/resource/stylesheets/theme.css">
    <link rel="stylesheet" type="text/css" href="<%=AppContext.getBaseUrl()%>/resource/stylesheets/premium.css">

</head>
<body class=" theme-blue">

<!-- Demo page code -->

<script type="text/javascript">
    $(function () {
        var match = document.cookie.match(new RegExp('color=([^;]+)'));
        if (match) var color = match[1];
        if (color) {
            $('body').removeClass(function (index, css) {
                return (css.match(/\btheme-\S+/g) || []).join(' ')
            })
            $('body').addClass('theme-' + color);
        }

        $('[data-popover="true"]').popover({html: true});

    });
</script>
<style type="text/css">
    #line-chart {
        height: 300px;
        width: 800px;
        margin: 0px auto;
        margin-top: 1em;
    }

    .navbar-default .navbar-brand, .navbar-default .navbar-brand:hover {
        color: #fff;
    }
</style>

<script type="text/javascript">
    $(function () {
        var uls = $('.sidebar-nav > ul > *').clone();
        uls.addClass('visible-xs');
        $('#main-menu').append(uls.clone());
    });
</script>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]
  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<!-- Le fav and touch icons
<link rel="shortcut icon" href="../assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
-->


<!--[if lt IE 7 ]>
<body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]>
<body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]>
<body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]>
<body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->

<!--<![endif]-->

<div class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <a class="" href="index.jsp"><span class="navbar-brand"><span
                class="fa fa-paper-plane"></span> Fast Repair</span></a></div>

    <div class="navbar-collapse collapse" style="height: 1px;">

    </div>
</div>


<div class="dialog">
    <div class="panel panel-default">
        <p class="panel-heading no-collapse">Sign In</p>

        <div class="panel-body">
            <form action="<%=AppContext.getBaseUrl()%>/home/doSignIn" method="post">

                <!-- <input type="hidden" name="action" value="login"/> -->

                <div class="form-group">
                    <label>Username</label>
                    <input type="text" class="form-control span12" name="username">
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-controlspan12 form-control" name="password">
                </div>
                <input type="submit" value="Sign In" class="btn btn-primary pull-right"/>
                <label class="remember-me"><input type="checkbox"> Remember me</label>

                <div class="clearfix"></div>
            </form>
        </div>
    </div>
    <p class="pull-right" style=""><a href="http://www.portnine.com" target="blank"
                                      style="font-size: .75em; margin-top: .25em;">Design by Portnine</a></p>

    <p><a href="<%=AppContext.getBaseUrl()%>/home/reset-password">Forgot your password?</a></p>
</div>


<script src="<%=AppContext.getBaseUrl()%>/resource/lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
    $("[rel=tooltip]").tooltip();
    $(function () {
        $('.demo-cancel-click').click(function () {
            return false;
        });
    });
</script>


</body>
</html>
