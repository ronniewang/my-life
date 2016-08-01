package wang.ronnie.filter;

import wang.ronnie.db.entity.UserEntity;
import wang.ronnie.global.TokenHolder;

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

        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        if (requestURI.contains("login") || requestURI.contains("register")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            UserEntity userEntity = TokenHolder.check(getToken((HttpServletRequest) servletRequest));
        }
    }

    private String getToken(HttpServletRequest servletRequest) {

        return servletRequest.getParameter("token");// TODO: 16/7/13 cookie
    }

    @Override
    public void destroy() {

    }
}
