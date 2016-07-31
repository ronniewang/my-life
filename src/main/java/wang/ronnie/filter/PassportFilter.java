package wang.ronnie.filter;

import wang.ronnie.global.FilterUriHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
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

        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (uri.equals("login")) {
            String username = servletRequest.getParameter("username");
            String password = servletRequest.getParameter("password");
        } else if (FilterUriHolder.needToken(uri)) {

        } else {
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
