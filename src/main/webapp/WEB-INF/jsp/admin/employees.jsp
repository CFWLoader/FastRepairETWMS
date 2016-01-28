<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="nav.jsp"%>

<%--
    Employee employee = (Employee) session.getAttribute("employee");

    EmployeeService employeeService = new EmployeeServiceImpl();

    final int PAGE_SIZE = 10;
    String pageNoStr = request.getParameter("pageNo");
    int pageNo = 1;
    if (pageNoStr != null && pageNoStr.trim() != "") pageNo = Integer.parseInt(pageNoStr);


    int totalRecords = employeeService.getTotalOfEmployeesByDepartment(null);

    int totalPages = (totalRecords + PAGE_SIZE - 1) / PAGE_SIZE;

    if (pageNo > totalPages && totalPages > 0) pageNo = totalPages;

    /*
    List<Category> categories = categoryService.getCategories(pageNo, PAGE_SIZE);
    categoryService.close();
    */
    List<Employee> employees = employeeService.getEmployeesByDepartment(null, pageNo, PAGE_SIZE);

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
                <th>Employee ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Gender</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Company</th>
                <th>Department</th>

                <th style="width: 3.5em;"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${employees}" var="employee">
            <tr>
                <td>${employee.id}
                </td>
                <td>${employee.firstName}
                </td>
                <td>${employee.lastName}
                </td>
                <td>${employee.gender}
                </td>
                <td>${employee.phone}
                </td>
                <td>${employee.address}
                </td>
                <td>${employee.company.companyName}
                </td>
                <td>${employee.department.departmentType}
                </td>
                <td>
                    <a href="<%=AppContext.getBaseUrl()%>/admin/employee?idStr=${employee.id}"><i class="fa fa-pencil"></i></a>
                    <a href="#myModal" role="button" data-toggle="modal"><i class="fa fa-trash-o"></i></a>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>

        <ul class="pagination">
            <li><a href="<%=AppContext.getBaseUrl()%>/admin/employees?pageIndex=1&pageSize=${pageSize}">&laquo;</a></li>
            <c:forEach begin="1" end="${totalPages}" var="i" step="1">
                <li><a href="<%=AppContext.getBaseUrl()%>/admin/employees?pageIndex=${i}&pageSize=${pageSize}">${i}
                </a></li>
            </c:forEach>
            <li><a href="<%=AppContext.getBaseUrl()%>/admin/employees?pageIndex=${totalPages}&pageSize=${pageSize}">&raquo;</a></li>
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
