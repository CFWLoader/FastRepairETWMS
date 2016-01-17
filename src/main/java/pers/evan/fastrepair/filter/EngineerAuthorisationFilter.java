package pers.evan.fastrepair.filter;

import team.unnamed.fastrepair.model.Employee;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by cfwloader on 4/11/15.
 */
public class EngineerAuthorisationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpSession session = request.getSession();

        Employee employee = (Employee) session.getAttribute("employee");

        if (employee.getDepartment().getDepartmentType().equals("Specialist") ||
                employee.getDepartment().getDepartmentType().equals("Construction Device") ||
                employee.getDepartment().getDepartmentType().equals("Computer") ||
                employee.getDepartment().getDepartmentType().equals("Appliance") ||
                employee.getDepartment().getDepartmentType().equals("Automobile")) filterChain.doFilter(servletRequest, servletResponse);
        else ((HttpServletResponse) servletResponse).sendRedirect("/index.jsp");
    }

    @Override
    public void destroy() {

    }
}
