<%@ page import="pers.evan.fastrepair.service.ToolService" %>
<%@ page import="pers.evan.fastrepair.service.impl.ToolServiceImpl" %>
<%@ page import="pers.evan.fastrepair.model.Tool" %>
<%@ page import="pers.evan.fastrepair.model.Employee" %>
<%@ page import="pers.evan.fastrepair.model.Company" %>
<%@ page import="pers.evan.fastrepair.service.CompanyService" %>
<%@ page import="pers.evan.fastrepair.service.impl.CompanyServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="pers.evan.fastrepair.model.Department" %>
<%@ page import="pers.evan.fastrepair.service.DepartmentService" %>
<%@ page import="pers.evan.fastrepair.service.impl.DepartmentServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--@ include file="_seesionCheck.jsp" --%>

<%
    Employee employee = (Employee) session.getAttribute("employee");

    String idStr = request.getParameter("id");

    ToolService toolService = new ToolServiceImpl();

    Tool tool;

    String action = request.getParameter("action");

    if(action != null && action.trim().equals("peektool")){
        tool = toolService.getToolById(idStr);
    }else if(action != null && action.trim().equals("modifytool")) {
        tool = toolService.toolObjectAssembler(request.getParameter("id"),
                request.getParameter("toolname"),
                request.getParameter("isexpensive"),
                request.getParameter("numberofavailable"),
                request.getParameter("companyselection"),
                request.getParameter("departmentselection"));

        toolService.updateTool(tool);

        tool = toolService.getToolById(idStr);
    } else if(action != null && action.trim().equals("addtool")){
        tool = toolService.toolObjectAssembler(null,
                request.getParameter("toolname"),
                request.getParameter("isexpensive"),
                request.getParameter("numberofavailable"),
                request.getParameter("companyselection"),
                request.getParameter("departmentselection"));

        tool = toolService.getToolById(String.valueOf(toolService.addTool(tool)));
    } else {
        tool = new Tool();
        tool.setId(-1);
        tool.setToolName("");
        tool.setIsExpensive(false);
        tool.setNumberOfAvailable(0);
        tool.setCompany(new Company());
        tool.getCompany().setCompanyName("");
        tool.getCompany().setLocation("");
        tool.setDepartment(new Department());
        tool.getDepartment().setId(0);
        tool.getDepartment().setDepartmentType("");
    }

    toolService.close();

    CompanyService companyService = new CompanyServiceImpl();

    List<Company> companyList = companyService.getCompanies();

    companyService.close();

    DepartmentService departmentService = new DepartmentServiceImpl();

    List<Department> departments = departmentService.getDepartments();

    departmentService.close();
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

    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="../lib/font-awesome/css/font-awesome.css">

    <script src="../lib/jquery-1.11.1.min.js" type="text/javascript"></script>


    <link rel="stylesheet" type="text/css" href="../stylesheets/theme.css">
    <link rel="stylesheet" type="text/css" href="../stylesheets/premium.css">

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
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="" href="index.jsp"><span class="navbar-brand"><span
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
                    <li><a tabindex="-1" href="/sign-in.jsp?action=logout">Logout</a></li>
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
                <li><a href="company.jsp"><span class="fa fa-caret-right"></span> Company Information</a></li>
            </ul>
        </li>

        <li><a href="#" data-target=".department-menu" class="nav-header collapsed" data-toggle="collapse"><i
                class="fa fa-fw fa-legal"></i> Department<i class="fa fa-collapse"></i></a></li>
        <li>
            <ul class="department-menu nav nav-list collapse">
                <li><a href="department.jsp"><span class="fa fa-caret-right"></span> Department Information</a></li>
            </ul>
        </li>

        <li><a href="#" data-target=".tool-menu" class="nav-header collapsed" data-toggle="collapse"><i
                class="fa fa-fw fa-legal"></i> Tool Management<i class="fa fa-collapse"></i></a></li>
        <li>
            <ul class="tool-menu nav nav-list collapse">
                <li><a href="tools.jsp"><span class="fa fa-caret-right"></span> Tool List</a></li>
                <li><a href="tool.jsp"><span class="fa fa-caret-right"></span> Tool Profile</a>
                </li>
                <li><a href="inexpensivetoollogs.jsp"><span class="fa fa-caret-right"></span> Inexpensive tool logs</a>
                </li>
                <li><a href="expensivetoollogs.jsp"><span class="fa fa-caret-right"></span> Expensive tool logs</a></li>
                <li><a href="inexpensivetoollog.jsp"><span class="fa fa-caret-right"></span> Inexpensive tool log</a>
                </li>
                <li><a href="expensivetoollog.jsp"><span class="fa fa-caret-right"></span> Expensive tool logs</a></li>
            </ul>
        </li>

        <li><a href="#" data-target=".employee-menu" class="nav-header collapsed" data-toggle="collapse"><i
                class="fa fa-fw fa-legal"></i> Employee Management<i class="fa fa-collapse"></i></a></li>
        <li>
            <ul class="employee-menu nav nav-list collapse">
                <li><a href="employee.jsp"><span class="fa fa-caret-right"></span> Employee Profile</a>
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

        <h1 class="page-title">Edit Tool</h1>
        <ul class="breadcrumb">
            <li><a href="index.jsp">Home</a></li>
            <li><a href="tools.jsp">Tools</a></li>
            <li class="active"><%=tool.getToolName()%>
            </li>
        </ul>

    </div>
    <div class="main-content">

        <ul class="nav nav-tabs">
            <li class="active"><a href="#home" data-toggle="tab">Profile</a></li>
            <li><a href="#profile" data-toggle="tab">Password</a></li>
        </ul>


        <div class="row">
            <div class="col-md-4">
                <br>

                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active in" id="home">
                        <form id="tab" name="toolinfo" action="tool.jsp" method="post">

                            <input type="hidden" name="action" value="<%=tool.getId() > 0 ? "modifytool" : "addtool"%>"/>

                            <div class="form-group">
                                <label>Tool ID</label>
                                <input type="text" name="id" value="<%=tool.getId()%>" class="form-control" readonly>
                            </div>
                            <div class="form-group">
                                <label>Tool Name</label>
                                <input type="text" name="toolname" value="<%=tool.getToolName()%>" class="form-control" readonly>
                            </div>
                            <div class="form-group">
                                <label>Is Expensive Tool</label>
                                <select name="isexpensive" id="expensiveselection" class="form-control">
                                    <option value="true" <%= tool.isExpensive() ? "selected=\"selected\"" : ""%>>True</option>
                                    <option value="false" <%= !tool.isExpensive() ? "selected=\"selected\"" : ""%>>False</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Number of available</label>
                                <input type="text" name="numberofavailable" value="<%=tool.getNumberOfAvailable()%>" class="form-control" readonly>
                            </div>
                            <div class="form-group">
                                <label>Company</label>
                                <input type="text" value="<%=tool.getCompany().getCompanyName()%>" disabled="disabled" class="form-control" readonly>
                            </div>
                            <div class="form-group">
                                <label>Company location</label>
                                <input type="text" value="<%=tool.getCompany().getLocation()%>" disabled="disabled" class="form-control" readonly>
                            </div>
                            <div class="form-group">
                                <label>Department</label>
                                <input type="text" value="<%=tool.getDepartment().getDepartmentType()%>" disabled="disabled"
                                       class="form-control" readonly>
                            </div>
                        </form>
                    </div>

                    <script type="text/javascript">
                        <!--
                        function submitForm(obj){
                            obj.submit();
                        }
                                -->
                    </script>

                    <div class="tab-pane fade" id="profile">

                        <form id="tab2">
                            <div class="form-group">
                                <label>New Password</label>
                                <input type="password" class="form-control">
                            </div>
                            <div>
                                <button class="btn btn-primary">Update</button>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="btn-toolbar list-toolbar">
                    <a href="<%=tool.isExpensive()? "expensivetoollog.jsp?action=buildlog&id=" + tool.getId() : "expensivetoollog.jsp?action=buildlog&id=" + tool.getId() %>" ><button class="btn btn-primary"><i class="fa fa-save"></i> Get Tool</button></a>
                    <a href="#myModal" data-toggle="modal" class="btn btn-danger">Delete</a>
                </div>
            </div>
        </div>


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
                            the user?</p>
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


<script src="../lib/bootstrap/js/bootstrap.js"></script>
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
