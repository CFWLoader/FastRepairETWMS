<%@ page import="pers.evan.fastrepair.util.AppContext" %>
<%@ page import="pers.evan.fastrepair.model.Employee" %>
<%--
  Created by IntelliJ IDEA.
  User: cfwloader
  Date: 1/19/16
  Time: 9:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Employee employee = (Employee) session.getAttribute("employee");

    if(employee == null)
    {
        response.sendRedirect(AppContext.getBaseUrl() + "/home/sign-in");

        return;
    }
%>

<html>
<head>
    <meta charset="utf-8">
    <title>Fast Repair Employee,Tool and Warehouse Management System</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="<%=AppContext.getBaseUrl()%>/resource/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="<%=AppContext.getBaseUrl()%>/resource/lib/font-awesome/css/font-awesome.css">

    <script src="<%=AppContext.getBaseUrl()%>/resource/lib/jquery-1.11.1.min.js" type="text/javascript"></script>

    <script src="<%=AppContext.getBaseUrl()%>/resource/lib/jQuery-Knob/js/jquery.knob.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            $(".knob").knob();
        });
    </script>


    <link rel="stylesheet" type="text/css" href="<%=AppContext.getBaseUrl()%>/resource/stylesheets/theme.css">
    <link rel="stylesheet" type="text/css" href="<%=AppContext.getBaseUrl()%>/resource/stylesheets/premium.css">
</head>
<body>
<div class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="" href="<%=AppContext.getBaseUrl()%>/admin/index"><span class="navbar-brand"><span
                class="fa fa-paper-plane"></span> Fast Repair</span></a>
    </div>

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
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/index"><span class="fa fa-caret-right"></span> Main</a>
                </li>
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
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/company"><span class="fa fa-caret-right"></span> Company
                    Information</a></li>
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/companies?pageIndex=0&pageSize=10"><span class="fa fa-caret-right"></span> Company
                    List</a></li>
            </ul>
        </li>

        <li><a href="#" data-target=".department-menu" class="nav-header collapsed" data-toggle="collapse"><i
                class="fa fa-fw fa-legal"></i> Department<i class="fa fa-collapse"></i></a></li>
        <li>
            <ul class="department-menu nav nav-list collapse">
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/department"><span class="fa fa-caret-right"></span>
                    Department Information</a></li>
                <li><a href="<%=AppContext.getBaseUrl()%>/home/departments?pageIndex=0&pageSize=10"><span class="fa fa-caret-right"></span>
                    Department List</a></li>
            </ul>
        </li>

        <li><a href="#" data-target=".tool-menu" class="nav-header collapsed" data-toggle="collapse"><i
                class="fa fa-fw fa-legal"></i> Tool Management<i class="fa fa-collapse"></i></a></li>
        <li>
            <ul class="tool-menu nav nav-list collapse">
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/tools?pageIndex=0&pageSize=10"><span
                        class="fa fa-caret-right"></span> Tool List</a></li>
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/addTool"><span class="fa fa-caret-right"></span> Tool
                    Profile</a>
                </li>
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/inexpensiveToolLogs?pageIndex=0&pageSize=10"><span
                        class="fa fa-caret-right"></span> Inexpensive tool logs</a>
                </li>
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/expensiveToolLogs?pageIndex=0&pageSize=10"><span
                        class="fa fa-caret-right"></span> Expensive tool logs</a></li>
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/inexpensiveToolLog"><span
                        class="fa fa-caret-right"></span> Inexpensive tool log</a>
                </li>
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/expensiveToolLog"><span
                        class="fa fa-caret-right"></span> Expensive tool logs</a></li>
            </ul>
        </li>

        <li><a href="#" data-target=".employee-menu" class="nav-header collapsed" data-toggle="collapse"><i
                class="fa fa-fw fa-legal"></i> Employee Management<i class="fa fa-collapse"></i></a></li>
        <li>
            <ul class="employee-menu nav nav-list collapse">
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/employees"><span class="fa fa-caret-right"></span>
                    Employee List</a></li>
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/employee"><span class="fa fa-caret-right"></span>
                    Employee Profile</a>
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

<script src="<%=AppContext.getBaseUrl()%>/resource/lib/bootstrap/js/bootstrap.js"></script>

</body>
</html>
