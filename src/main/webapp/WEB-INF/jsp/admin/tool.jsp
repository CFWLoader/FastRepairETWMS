<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@ include file="_seesionCheck.jsp" --%>
<%@ include file="nav.jsp"%>

<%--
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
--%>

<!doctype html>
<html lang="en">
<head>
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

<div class="content">
    <div class="header">

        <h1 class="page-title">Edit Tool</h1>
        <ul class="breadcrumb">
            <li><a href="index.jsp">Home</a></li>
            <li><a href="tools.jsp">Tools</a></li>
            <li class="active">${tool.toolName}
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
                        <form id="tab" name="toolInfo" action="${target}" method="post">

                            <div class="form-group">
                                <label>Tool ID</label>
                                <input type="text" name="id" value="${tool.id}" class="form-control" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label>Tool Name</label>
                                <input type="text" name="toolName" value="${tool.toolName}" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Is Expensive Tool</label>
                                <select name="isExpensive" id="expensiveselection" class="form-control">
                                    <option value="true" <c:if test="${tool.isExpensive()}">selected="selected"</c:if>>True</option>
                                    <option value="false" <c:if test="${!tool.isExpensive()}">selected="selected"</c:if>>False</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Number of available</label>
                                <input type="text" name="numberOfAvailable" value="${tool.numberOfAvailable}" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Company</label>
                                <input type="text" value="${tool.company.companyName}" readonly="readonly" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Company location</label>
                                <input type="text" value="${tool.company.location}" readonly="readonly" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Department</label>
                                <input type="text" value="${tool.department.departmentType}" readonly="readonly"
                                       class="form-control">
                            </div>

                            <div class="form-group">
                                <label>Change Company</label>
                                <select name="companySelection" id="companySelection" class="form-control">
                                    <c:forEach var="company" items="${companies}">
                                        <option value="${company.id}"
                                                <c:if test="${tool.company.id == company.id}">
                                                    selected="selected"
                                                </c:if>
                                                >${company.companyName}</option>
                                    </c:forEach>
                                    <%--
                                        for(Company company : companyList){
                                    %>
                                    <option value="<%=company.getId()%>" <%= company.getId() == tool.getCompanyId() ? "selected=\"selected\"" : ""%>><%=company.getCompanyName()%></option>
                                    <%
                                        }
                                    --%>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Change Department</label>
                                <select name="departmentSelection" id="departmentSelection" class="form-control">
                                    <c:forEach var="department" items="${departments}">
                                        <option value="${department.id}"
                                                <c:if test="${tool.department.id == department.id}">
                                                    selected="selected"
                                                </c:if>
                                                >${department.departmentType}</option>
                                    </c:forEach>
                                </select>
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
                    <button class="btn btn-primary" onclick="submitForm(document.forms['toolInfo'])"><i class="fa fa-save"></i> Save</button>
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
