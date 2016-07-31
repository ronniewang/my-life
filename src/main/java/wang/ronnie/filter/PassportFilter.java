package wang.ronnie.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by ronniewang on 16/7/12.
 */
public class PassportFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String username = servletRequest.getParameter("username");
        String password = servletRequest.getParameter("password");
    }

    @Override
    public void destroy() {

    }
}
