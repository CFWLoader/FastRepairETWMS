package fastrepair.filter;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.util.SessionProperties;
import team.unnamed.fastrepair.model.Employee;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by cfwloader on 4/11/15.
 */
public class LoginFilter implements Filter {

    private String checkParameter;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        checkParameter = filterConfig.getInitParameter("checkParameter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;

        HttpSession session = request.getSession();

        String servletPath = request.getServletPath();

        Pattern pattern = Pattern.compile("/sign-in.jsp");

        if(session.getAttribute(checkParameter) == null && !pattern.matcher(servletPath).matches())((HttpServletResponse)servletResponse).sendRedirect("/sign-in.jsp");
        else filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
