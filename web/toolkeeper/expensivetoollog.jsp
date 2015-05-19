<%@ page import="java.util.List" %>
<%@ page import="team.unnamed.fastrepair.model.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="team.unnamed.fastrepair.exception.BadRequestParameterException" %>
<%@ page import="team.unnamed.fastrepair.service.*" %>
<%@ page import="team.unnamed.fastrepair.service.impl.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--@ include file="_seesionCheck.jsp" --%>

<%
    Employee employee = (Employee) session.getAttribute("employee");

    String idStr = request.getParameter("id");

    ToolService toolService = new ToolServiceImpl();

    ToolLogService toolLogService = new ToolLogServiceImpl();

    Tool tool = null;

    ExpensiveToolLog expensiveToolLog = null;

    EmployeeService employeeService = new EmployeeServiceImpl();

    Employee toolBorrower = null;

    String action = request.getParameter("action");

    if(action != null && action.trim().equals("buildlog")){
        tool = toolService.getToolById(idStr);
        expensiveToolLog = new ExpensiveToolLog();

        expensiveToolLog.setId(-1);
        expensiveToolLog.setEmployeeId(employee.getId());
        expensiveToolLog.setToolId(tool.getId());
        expensiveToolLog.setQuantity(0);
        expensiveToolLog.setStatus("Waiting for deal");
        expensiveToolLog.setLendDate(new Date(System.currentTimeMillis()));
        expensiveToolLog.setBackDate(new Date(0));

    } else if(action != null && action.trim().equals("createlog")) {

        //System.out.println(request.getParameter("toolid"));

        expensiveToolLog = toolLogService.expensiveLogAssembler(
                request.getParameter("id"),
                request.getParameter("employeeid"),
                request.getParameter("toolid"),
                request.getParameter("quantity"),
                request.getParameter("status"),
                request.getParameter("lentdate"),
                request.getParameter("backdate")
        );

        tool = toolService.getToolById(request.getParameter("toolid"));

        int newId = toolLogService.addExpensiveToolLog(expensiveToolLog);

        expensiveToolLog = toolLogService.getExpensiveToolLogById(newId);

        /*
        System.out.println(inexpensiveToolLog);
        System.out.println(tool);
        */
        /*
        tool = toolService.toolObjectAssembler(request.getParameter("id"),
                request.getParameter("toolname"),
                request.getParameter("isexpensive"),
                request.getParameter("numberofavailable"),
                request.getParameter("companyselection"),
                request.getParameter("departmentselection"));

        toolService.updateTool(tool);

        tool = toolService.getToolById(idStr);
        */
    } else if(action != null && action.trim().equals("modifylog")) {

        //System.out.println(request.getParameter("toolid"));

        expensiveToolLog = toolLogService.expensiveLogAssembler(
                request.getParameter("id"),
                request.getParameter("employeeid"),
                request.getParameter("toolid"),
                request.getParameter("quantity"),
                request.getParameter("status"),
                request.getParameter("lentdate"),
                request.getParameter("backdate")
        );

        tool = toolService.getToolById(String.valueOf(expensiveToolLog.getToolId()));

        toolLogService.updateExpensiveToolLog(expensiveToolLog);

    } else if(action != null && action.trim().equals("peek")) {

        //System.out.println(request.getParameter("toolid"));

        expensiveToolLog = toolLogService.getExpensiveToolLogById(Integer.parseInt(request.getParameter("id").trim()));

        tool = toolService.getToolById(String.valueOf(expensiveToolLog.getToolId()));

        //toolLogService.updateInexpensiveToolLog(inexpensiveToolLog);

        //inexpensiveToolLog = toolLogService.getInexpensiveToolLogById(inexpensiveToolLog.getToolId());

    }/* else if(action != null && action.trim().equals("addtool")){
        tool = toolService.toolObjectAssembler(null,
                request.getParameter("toolname"),
                request.getParameter("isexpensive"),
                request.getParameter("numberofavailable"),
                request.getParameter("companyselection"),
                request.getParameter("departmentselection"));

        tool = toolService.getToolById(String.valueOf(toolService.addTool(tool)));
    }*/ else {
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

        expensiveToolLog = new ExpensiveToolLog();

        expensiveToolLog.setId(-1);
        expensiveToolLog.setEmployeeId(employee.getId());
        expensiveToolLog.setToolId(tool.getId());
        expensiveToolLog.setQuantity(0);
        expensiveToolLog.setStatus("Waiting for deal");
        expensiveToolLog.setLendDate(new Date(System.currentTimeMillis()));
        expensiveToolLog.setBackDate(new Date(System.currentTimeMillis()));
    }

    toolBorrower = employeeService.getEmployeeById(String.valueOf(expensiveToolLog.getEmployeeId()));

    employeeService.close();

    toolService.close();

    toolLogService.close();

    /*
    CompanyService companyService = new CompanyServiceImpl();

    List<Company> companyList = companyService.getCompanies();

    companyService.close();

    DepartmentService departmentService = new DepartmentServiceImpl();

    List<Department> departments = departmentService.getDepartments();

    departmentService.close();
    */
    if(tool == null || expensiveToolLog == null)throw new NullPointerException();
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

        <h1 class="page-title">Edit Tool Log</h1>
        <ul class="breadcrumb">
            <li><a href="index.jsp">Home</a></li>
            <li><a href="inexpensivetoollogs.jsp">Inexpensive Tool Logs</a></li>
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
                        <form id="tab" name="toolloginfo" action="expensivetoollog.jsp" method="post">

                            <input type="hidden" name="action" value="<%=action.equals("buildlog") ? "createlog" : "modifylog"%>"/>

                            <div class="form-group">
                                <label>Tool Log Id</label>
                                <input type="text" name="id" value="<%=expensiveToolLog.getId()%>" class="form-control" readonly>
                            </div>
                            <div class="form-group">
                                <label>Employee Name</label>
                                <input type="hidden" name="employeeid" value="<%=toolBorrower.getId()%>"/>
                                <input type="text" value="<%=toolBorrower.getFirstName() + " " + toolBorrower.getLastName()%>" class="form-control" readonly>
                            </div>

                            <div class="form-group">
                                <label>Tool Name</label>
                                <input type="hidden" name="toolid" value="<%=tool.getId()%>"/>
                                <input type="text" value="<%=tool.getToolName()%>" class="form-control" readonly>
                            </div>
                            <div class="form-group">
                                <label>Quantity</label>
                                <input type="text" name="quantity" value="<%=expensiveToolLog.getQuantity()%>" class="form-control" class="form-control" <%=action.equals("buildlog") ? "" : "readonly"%> />
                            </div>
                            <!--
                            <div class="form-group">
                                <label>Status</label>
                                <input type="text" name="status" value="<--%=inexpensiveToolLog.getStatus()%" class="form-control">
                            </div>
                            -->
                            <div class="form-group">
                                <label>Status</label>
                                <select name="status" class="form-control" <%=expensiveToolLog.getStatus().equals("Back") ? "disabled=\"disabled\"" : ""%>>
                                    <option value="Waiting for deal" <%= expensiveToolLog.getStatus().equals("Waiting for deal")? "selected=\"selected\"" : ""%>>Waiting for deal
                                    </option>
                                    <option value="Lent" <%= expensiveToolLog.getStatus().equals("Lent")? "selected=\"selected\"" : ""%>>Lent
                                    </option>
                                    <option value="Broken" <%= expensiveToolLog.getStatus().equals("Broken")? "selected=\"selected\"" : ""%>>Broken
                                    </option>
                                    <option value="Back" <%= expensiveToolLog.getStatus().equals("Back")? "selected=\"selected\"" : ""%>>Back
                                    </option>
                                </select>

                                <%=expensiveToolLog.getStatus().equals("Back")? "<input type=\"hidden\" name=\"status\" value=\"" + expensiveToolLog.getStatus() + "\" />" : ""%>
                            </div>
                            <div class="form-group">
                                <label>Lend Date</label>
                                <input type="hidden" name="lentdate" value="<%=expensiveToolLog.getLendDate().getTime()%>"/>
                                <input type="text" value="<%=expensiveToolLog.getLendDate()%>" class="form-control" readonly>
                            </div>
                            <div class="form-group">
                                <label>Back Date</label>
                                <input type="hidden" name="backdate" value="<%=expensiveToolLog.getBackDate().getTime()%>"/>
                                <input type="text" value="<%=expensiveToolLog.getBackDate()%>" class="form-control" readonly>
                            </div>
                            <div class="form-group">
                                <label>Company of Tool</label>
                                <input type="text" value="<%=tool.getCompany().getCompanyName()%>" disabled="disabled" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Company location</label>
                                <input type="text" value="<%=tool.getCompany().getLocation()%>" disabled="disabled" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Department of Tool</label>
                                <input type="text" value="<%=tool.getDepartment().getDepartmentType()%>" disabled="disabled"
                                       class="form-control">
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
                    <button class="btn btn-primary" onclick="submitForm(document.forms['toolloginfo'])"><i class="fa fa-save"></i> Save</button>
                    <a href="resultofgettingtools.jsp" class="btn btn-danger">Get Tools</a>
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
