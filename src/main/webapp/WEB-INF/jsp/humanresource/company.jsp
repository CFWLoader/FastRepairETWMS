<%@ page import="pers.evan.fastrepair.service.ToolService" %>
<%@ page import="pers.evan.fastrepair.service.impl.ToolServiceImpl" %>
<%@ page import="pers.evan.fastrepair.model.Tool" %>
<%@ page import="pers.evan.fastrepair.model.Employee" %>
<%@ page import="pers.evan.fastrepair.service.CompanyService" %>
<%@ page import="pers.evan.fastrepair.service.impl.CompanyServiceImpl" %>
<%@ page import="pers.evan.fastrepair.model.Company" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--@ include file="_seesionCheck.jsp" --%>

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
        <a class="" href="index.html"><span class="navbar-brand"><span
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

        <li><a href="#" data-target=".employee-menu" class="nav-header collapsed" data-toggle="collapse"><i
                class="fa fa-fw fa-legal"></i> Employee Management<i class="fa fa-collapse"></i></a></li>
        <li>
            <ul class="employee-menu nav nav-list collapse">
                <li><a href="employees.jsp"><span class="fa fa-caret-right"></span> Employee List</a></li>
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

        <h1 class="page-title">Company</h1>
        <ul class="breadcrumb">
            <li><a href="index.jsp">Home</a></li>
            <li><a href="company.jsp">Company</a></li>
            <li class="active"><%=employee.getCompany().getCompanyName()%>
            </li>
        </ul>

    </div>
    <div class="main-content">

        <ul class="nav nav-tabs">
            <li class="active"><a href="#home" data-toggle="tab">Profile</a></li>
        </ul>


        <div class="row">
            <div class="col-md-4">
                <br>

                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active in" id="home">
                        <form id="tab">
                            <div class="form-group">
                                <label>Company ID</label>
                                <input type="text" value="<%=employee.getCompany().getId()%>" class="form-control" readonly>
                            </div>
                            <div class="form-group">
                                <label>Company Name</label>
                                <input type="text" value="<%=employee.getCompany().getCompanyName()%>"
                                       class="form-control" readonly>
                            </div>
                            <div class="form-group">
                                <label>Company Location</label>
                                <input type="text" value="<%=employee.getCompany().getLocation()%>"
                                       class="form-control" readonly>
                            </div>
                            <!--
                            <div class="form-group">
                                <label>Address</label>
                                <textarea value="Smith" rows="3" class="form-control">2817 S 49th
                                    Apt 314
                                    San Jose, CA 95101</textarea>
                            </div>
                            -->

                            <!--
                            <div class="form-group">
                                <label>Time Zone</label>
                                <select name="DropDownTimezone" id="DropDownTimezone" class="form-control">
                                    <option value="-12.0">(GMT -12:00) Eniwetok, Kwajalein</option>
                                    <option value="-11.0">(GMT -11:00) Midway Island, Samoa</option>
                                    <option value="-10.0">(GMT -10:00) Hawaii</option>
                                    <option value="-9.0">(GMT -9:00) Alaska</option>
                                    <option selected="selected" value="-8.0">(GMT -8:00) Pacific Time (US &amp; Canada)</option>
                                    <option value="-7.0">(GMT -7:00) Mountain Time (US &amp; Canada)</option>
                                    <option value="-6.0">(GMT -6:00) Central Time (US &amp; Canada), Mexico City</option>
                                    <option value="-5.0">(GMT -5:00) Eastern Time (US &amp; Canada), Bogota, Lima</option>
                                    <option value="-4.0">(GMT -4:00) Atlantic Time (Canada), Caracas, La Paz</option>
                                    <option value="-3.5">(GMT -3:30) Newfoundland</option>
                                    <option value="-3.0">(GMT -3:00) Brazil, Buenos Aires, Georgetown</option>
                                    <option value="-2.0">(GMT -2:00) Mid-Atlantic</option>
                                    <option value="-1.0">(GMT -1:00 hour) Azores, Cape Verde Islands</option>
                                    <option value="0.0">(GMT) Western Europe Time, London, Lisbon, Casablanca</option>
                                    <option value="1.0">(GMT +1:00 hour) Brussels, Copenhagen, Madrid, Paris</option>
                                    <option value="2.0">(GMT +2:00) Kaliningrad, South Africa</option>
                                    <option value="3.0">(GMT +3:00) Baghdad, Riyadh, Moscow, St. Petersburg</option>
                                    <option value="3.5">(GMT +3:30) Tehran</option>
                                    <option value="4.0">(GMT +4:00) Abu Dhabi, Muscat, Baku, Tbilisi</option>
                                    <option value="4.5">(GMT +4:30) Kabul</option>
                                    <option value="5.0">(GMT +5:00) Ekaterinburg, Islamabad, Karachi, Tashkent</option>
                                    <option value="5.5">(GMT +5:30) Bombay, Calcutta, Madras, New Delhi</option>
                                    <option value="5.75">(GMT +5:45) Kathmandu</option>
                                    <option value="6.0">(GMT +6:00) Almaty, Dhaka, Colombo</option>
                                    <option value="7.0">(GMT +7:00) Bangkok, Hanoi, Jakarta</option>
                                    <option value="8.0">(GMT +8:00) Beijing, Perth, Singapore, Hong Kong</option>
                                    <option value="9.0">(GMT +9:00) Tokyo, Seoul, Osaka, Sapporo, Yakutsk</option>
                                    <option value="9.5">(GMT +9:30) Adelaide, Darwin</option>
                                    <option value="10.0">(GMT +10:00) Eastern Australia, Guam, Vladivostok</option>
                                    <option value="11.0">(GMT +11:00) Magadan, Solomon Islands, New Caledonia</option>
                                    <option value="12.0">(GMT +12:00) Auckland, Wellington, Fiji, Kamchatka</option>
                                </select>
                            </div>
                            -->
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

                <div class="btn-toolbar list-toolbar">
                    <button class="btn btn-primary"><i class="fa fa-save"></i> Save</button>
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
