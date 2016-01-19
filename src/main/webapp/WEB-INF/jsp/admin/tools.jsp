<%@ page import="pers.evan.fastrepair.model.Employee" %>
<%@ page import="pers.evan.fastrepair.model.Tool" %>
<%@ page import="pers.evan.fastrepair.util.AppContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@ include file="_seesionCheck.jsp" --%>
<%@ include file="nav.jsp"%>

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

        <h1 class="page-title">Tools</h1>
        <ul class="breadcrumb">
            <li><a href="index.jsp">Home</a></li>
            <li class="active">Tools</li>
        </ul>

    </div>
    <div class="main-content">

        <div class="btn-toolbar list-toolbar">
            <button class="btn btn-primary"><i class="fa fa-plus"></i> New Tools</button>
            <button class="btn btn-default">Import</button>
            <button class="btn btn-default">Export</button>
            <div class="btn-group">
            </div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th>Tool ID</th>
                <th>Tool Name</th>
                <th>Is Expensive Tool</th>
                <th>Available Number</th>
                <th>Company</th>
                <th>Department</th>

                <th style="width: 3.5em;"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="tool" items="${tools}">
            <tr>
                <td>${tool.id}
                </td>
                <td>${tool.toolName()}
                </td>
                <td>${tool.isExpensive}
                </td>
                <td>${tool.numberOfAvailable()}
                </td>
                <td>${tool.company.companyName()}
                </td>
                <td>${tool.department.departmentType()}
                </td>
                <td>
                    <a href="<%=AppContext.getBaseUrl()%>/admin/tool?action=peektool&id=${tool.id()}"><i class="fa fa-pencil"></i></a>
                    <!--
                    <a href="#myModal" role="button" data-toggle="modal"><i class="fa fa-trash-o"></i></a>
                    -->
                    <c:choose>
                        <c:when test="${tool.isExpensive}">
                            <a href="<%=AppContext.getBaseUrl()%>/admin/expensivetoollog?action=buildlog&id=${tool.id}" role="button" data-toggle="modal"><i class="fa fa-check-circle"></i></a>
                        </c:when>
                        <c:otherwise>
                            <a href="<%=AppContext.getBaseUrl()%>/admin/inexpensivetoollog?action=buildlog&id=${tool.id}" role="button" data-toggle="modal"><i class="fa fa-check-circle"></i></a>
                        </c:otherwise>
                    </c:choose>
                    </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>

        <ul class="pagination">
            <li><a href="<%=AppContext.getBaseUrl()%>/admin/tools?pageIndex=1&pageSize=10">&laquo;</a></li>
            <c:forEach begin="1" end="${totalPages}" var="i" step="1">
            <li><a href="<%=AppContext.getBaseUrl()%>/admin/tools?pageIndex=${i}&pageSize=10">${i}
            </a></li>
            </c:forEach>
            <li><a href="<%=AppContext.getBaseUrl()%>/admin/tools?pageIndex=${totalPages}&pageSize=10">&raquo;</a></li>
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
