<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="nav.jsp" %>

<%--@ include file="_seesionCheck.jsp" --%>

<%--
    Employee employee = (Employee) session.getAttribute("employee");

    String action = request.getParameter("action");

    EmployeeService employeeService = new EmployeeServiceImpl();

    Employee modifyingEmployee;

    String idStr = request.getParameter("id");

    if (action != null && action.trim().equals("peekemployee")) {
        modifyingEmployee = employeeService.getEmployeeById(idStr);
    } else if (action != null && action.trim().equals("modifyemployee")) {
        modifyingEmployee = employeeService.employeeObjectAssembler(idStr,
                request.getParameter("firstname"),
                request.getParameter("lastname"),
                request.getParameter("gender"),
                request.getParameter("phone"),
                request.getParameter("address"),
                request.getParameter("companyselect"),
                request.getParameter("departmentselect"));

        employeeService.updateEmployee(modifyingEmployee, null);
    } else if (action != null && action.trim().equals("addemployee")){
        modifyingEmployee = employeeService.employeeObjectAssembler(null,
                request.getParameter("firstname"),
                request.getParameter("lastname"),
                request.getParameter("gender"),
                request.getParameter("phone"),
                request.getParameter("address"),
                request.getParameter("companyselect"),
                request.getParameter("departmentselect"));

        modifyingEmployee = employeeService.getEmployeeById(String.valueOf(employeeService.addEmployee(modifyingEmployee, request.getParameter("password").trim())));
    } else{
        modifyingEmployee = new Employee();

        modifyingEmployee.setId(-1);
        modifyingEmployee.setFirstName("");
        modifyingEmployee.setLastName("");
        modifyingEmployee.setGender("");
        modifyingEmployee.setPhone("");
        modifyingEmployee.setAddress("");
        modifyingEmployee.setCompanyId(0);
        modifyingEmployee.setDepartmentId(0);
        modifyingEmployee.setCompany(new Company());
        modifyingEmployee.setDepartment(new Department());

        modifyingEmployee.getCompany().setId(0);
        modifyingEmployee.getCompany().setCompanyName("");
        modifyingEmployee.getCompany().setLocation("");

        modifyingEmployee.getDepartment().setId(0);
        modifyingEmployee.getDepartment().setDepartmentType("");
    }

    CompanyService companyService = new CompanyServiceImpl();

    DepartmentService departmentService = new DepartmentServiceImpl();

    List<Company> companies = companyService.getCompanies();

    List<Department> departments = departmentService.getDepartments();

    companyService.close();

    departmentService.close();

    employeeService.close();
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

        <h1 class="page-title">Edit Employee</h1>
        <ul class="breadcrumb">
            <li><a href="<%=AppContext.getBaseUrl()%>/admin/index">Home</a></li>
            <li><a href="<%=AppContext.getBaseUrl()%>/admin/employees">Employees</a></li>
            <li class="active">${employee.firstName}&nbsp;${employee.lastName}
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
                        <form id="tab" name="employeeinfo" action="${target}" method="post">

                            <div class="form-group">
                                <label>Employee ID</label>
                                <input type="text" name="id" value="${employee.id}" class="form-control"
                                       readonly="readonly">
                            </div>

                            <c:if test="${false}">
                                <div class="form-group">
                                    <label>New Employee Password</label>
                                    <input type="password" name="password" value="" class="form-control">
                                </div>
                            </c:if>

                            <div class="form-group">
                                <label>First Name</label>
                                <input type="text" name="firstname" value="${employee.firstName}"
                                       class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Last Name</label>
                                <input type="text" name="lastname" value="${employee.lastName}"
                                       class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Gender</label>
                                <input type="text" name="gender" value="${employee.gender}"
                                       class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Phone</label>
                                <input type="text" name="phone" value="${employee.phone}"
                                       class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Address</label>
                                <input type="text" name="address" value="${employee.address}"
                                       class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Company</label>
                                <input type="text" value="${employee.company.companyName}"
                                       class="form-control" readonly="readonly">
                            </div>
                            <div class="form-group">
                                <label>Company location</label>
                                <input type="text" value="${employee.company.location}"
                                       class="form-control" readonly>
                            </div>
                            <div class="form-group">
                                <label>Department</label>
                                <input type="text" value="${employee.department.departmentType}"
                                       class="form-control" readonly>
                            </div>

                            <div class="form-group">
                                <label>Company Selection</label>
                                <select name="companyselect" id="companyselection" class="form-control">
                                    <c:forEach items="${companies}" var="company">
                                        <option value="${company.id}"
                                                <c:if test="${company.id == employee.company.id}">selected="selected"</c:if>>${company.companyName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Department Selection</label>
                                <select name="departmentselect" id="departmentselectioin" class="form-control">
                                    <c:forEach items="${departments}" var="department">
                                        <option value="${department.id}" <c:if test="${department.id == employee.department.id}">selected="selected"</c:if>>
                                            ${department.departmentType}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </form>
                    </div>

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

                <script type="text/javascript">
                    <!--
                    function submitForm(obj) {
                        obj.submit();
                    }
                    -->
                </script>

                <div class="btn-toolbar list-toolbar">
                    <button class="btn btn-primary" onclick="submitForm(document.employeeinfo);"><i
                            class="fa fa-save"></i> Save
                    </button>
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
