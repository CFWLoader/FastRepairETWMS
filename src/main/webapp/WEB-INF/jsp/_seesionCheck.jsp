<%@ page import="pers.evan.fastrepair.model.Employee" %>
<%@ page import="pers.evan.fastrepair.util.AppContext" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: cfwloader
  Date: 4/9/15
  Time: 2:42 PM
  To change this template use File | Settings | File Templates.
--%>

<%
    Employee employee = (Employee) session.getAttribute("employee");

    if(employee == null){
        response.sendRedirect(AppContext.getBaseUrl() + "/home/sign-in");
        return;
    }

%>
