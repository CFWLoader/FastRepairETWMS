<%@ page import="pers.evan.fastrepair.util.AppContext" %>
<%@ page import="pers.evan.fastrepair.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Employee employee = (Employee) session.getAttribute("employee");
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

    <%@include file="/WEB-INF/jsp/common/_resource.jsp"%>

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
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="" href="<%=AppContext.getBaseUrl()%>/admin/index"><span class="navbar-brand"><span
                class="fa fa-paper-plane"></span> Fast Repair</span></a></div>

    <div class="navbar-collapse collapse" style="height: 1px;">
        <ul id="main-menu" class="nav navbar-nav navbar-right">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user padding-right-small"
                          style="position:relative;top: 3px;"></span> <%=employee.getFirstName() + " " + employee.getLastName()%>
                    <i class="fa fa-caret-down"></i>
                </a>

                <ul class="dropdown-menu">
                    <li><a href="./">My Account</a></li>
                    <li class="divider"></li>
                    <li class="dropdown-header">Admin Panel</li>
                    <li><a href="./">Users</a></li>
                    <li><a href="./">Security</a></li>
                    <li><a tabindex="-1" href="./">Payments</a></li>
                    <li class="divider"></li>
                    <li><a tabindex="-1" href="<%=AppContext.getBaseUrl()%>/home/logout">Logout</a></li>
                </ul>
            </li>
        </ul>

    </div>
</div>

<div class="sidebar-nav">
    <ul>
        <li><a href="#" data-target=".dashboard-menu" class="nav-header" data-toggle="collapse"><i
                class="fa fa-fw fa-dashboard"></i> Dashboard<i class="fa fa-collapse"></i></a></li>
        <li>
            <ul class="dashboard-menu nav nav-list collapse in">
                <li><a href="index.jsp"><span class="fa fa-caret-right"></span> Main</a></li>
            </ul>
        </li>

        <li data-popover="true"
            data-content="Items in this group require a <strong><a href='http://portnine.com/bootstrap-themes/aircraft' target='blank'>premium license</a><strong>."
            rel="popover" data-placement="right"><a href="#" data-target=".company-menu" class="nav-header collapsed"
                                                    data-toggle="collapse"><i class="fa fa-fw fa-fighter-jet"></i>
            Company <i class="fa fa-collapse"></i></a></li>
        <li>
            <ul class="company-menu nav nav-list collapse">
                <span class="visible-xs visible-sm"><a href="#">- Welcome to Fast Repair -</a></span>
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/company"><span class="fa fa-caret-right"></span> Company Information</a></li>
            </ul>
        </li>

        <li><a href="#" data-target=".department-menu" class="nav-header collapsed" data-toggle="collapse"><i
                class="fa fa-fw fa-legal"></i> Department<i class="fa fa-collapse"></i></a></li>
        <li>
            <ul class="department-menu nav nav-list collapse">
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/department"><span class="fa fa-caret-right"></span> Department Information</a></li>
                <li><a href="<%=AppContext.getBaseUrl()%>/home/reset-password"><span class="fa fa-caret-right"></span> Reset Password</a></li>
            </ul>
        </li>

        <li><a href="#" data-target=".tool-menu" class="nav-header collapsed" data-toggle="collapse"><i
                class="fa fa-fw fa-legal"></i> Tool Management<i class="fa fa-collapse"></i></a></li>
        <li>
            <ul class="tool-menu nav nav-list collapse">
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/tools"><span class="fa fa-caret-right"></span> Tool List</a></li>
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/tool"><span class="fa fa-caret-right"></span> Tool Profile</a>
                </li>
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/inexpensivetoollogs"><span class="fa fa-caret-right"></span> Inexpensive tool logs</a>
                </li>
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/expensivetoollogs"><span class="fa fa-caret-right"></span> Expensive tool logs</a></li>
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/inexpensivetoollog"><span class="fa fa-caret-right"></span> Inexpensive tool log</a>
                </li>
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/expensivetoollog"><span class="fa fa-caret-right"></span> Expensive tool logs</a></li>
            </ul>
        </li>

        <li><a href="#" data-target=".employee-menu" class="nav-header collapsed" data-toggle="collapse"><i
                class="fa fa-fw fa-legal"></i> Employee Management<i class="fa fa-collapse"></i></a></li>
        <li>
            <ul class="employee-menu nav nav-list collapse">
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/employees"><span class="fa fa-caret-right"></span> Employee List</a></li>
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/employee"><span class="fa fa-caret-right"></span> Employee Profile</a>
                </li>
            </ul>
        </li>

        <!--
        <li><a href="help.html" class="nav-header"><i class="fa fa-fw fa-question-circle"></i> Help</a></li>
        <li><a href="faq.html" class="nav-header"><i class="fa fa-fw fa-comment"></i> Faq</a></li>
        <li><a href="http://portnine.com/bootstrap-themes/aircraft" class="nav-header" target="blank"><i
                class="fa fa-fw fa-heart"></i> Get Premium</a></li>
                -->
    </ul>
</div>

<div class="content">
    <div class="header">

        <h1 class="page-title">Employees</h1>
        <ul class="breadcrumb">
            <li><a href="index.jsp">Home</a></li>
            <li class="active">Employees</li>
        </ul>

    </div>
    <div class="main-content">

        <div class="btn-toolbar list-toolbar">
            <button class="btn btn-primary"><i class="fa fa-plus"></i> <a href="employee.jsp?action=readytoadd">New
                Employee</a></button>
            <button class="btn btn-default">Import</button>
            <button class="btn btn-default">Export</button>
            <div class="btn-group">
            </div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th>Company ID</th>
                <th>Compandy Name</th>
                <th>Location</th>

                <th style="width: 3.5em;"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${companyList}" var="company">

            <tr>
                <td>${company.id}
                </td>
                <td>${company.companyName}
                </td>
                <td>${company.location}
                </td>
                <td>
                    <a href="company.jsp?action=peekemployee&id=${company.id}"><i class="fa fa-pencil"></i></a>
                    <a href="#myModal" role="button" data-toggle="modal"><i class="fa fa-trash-o"></i></a>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>

        <ul class="pagination">
            <li><a href="employees.jsp?pageNo=1">&laquo;</a></li>
            <li><a href="employees.jsp?pageNo=1">1
            </a></li>
            <li><a href="employees.jsp?pageNo=1">&raquo;</a></li>
        </ul>

        <div class="modal small fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">Delete Confirmation</h3>
                    </div>
                    <div class="modal-body">
                        <p class="error-text"><i class="fa fa-warning modal-icon"></i>Are you sure you want to delete
                            the user?<br>This cannot be undone.</p>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">Cancel</button>
                        <button class="btn btn-danger" data-dismiss="modal">Delete</button>
                    </div>
                </div>
            </div>
        </div>


        <footer>
            <hr>

            <!-- Purchase a site license to remove this link from the footer: http://www.portnine.com/bootstrap-themes -->
            <p class="pull-right">A <a href="http://www.portnine.com/bootstrap-themes" target="_blank">Free Bootstrap
                Theme</a> by <a href="http://www.portnine.com" target="_blank">Portnine</a></p>

            <p>© 2014 <a href="http://www.portnine.com" target="_blank">Portnine</a></p>
        </footer>
    </div>
</div>

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
