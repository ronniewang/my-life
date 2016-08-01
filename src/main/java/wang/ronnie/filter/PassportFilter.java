package wang.ronnie.filter;

import org.apache.commons.lang3.StringUtils;
import wang.ronnie.global.ErrorCode;
import wang.ronnie.global.FilterUriHolder;
import wang.ronnie.service.PassportService;
import wang.ronnie.utils.TokenUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by ronniewang on 16/7/12.
 */
public class PassportFilter implements Filter {

    private PassportService passportService;

    public PassportFilter(PassportService passportService) {

        this.passportService = passportService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        if (uri.equals("login")) {
            String username = servletRequest.getParameter("username");
            String password = servletRequest.getParameter("password");
        } else if (FilterUriHolder.needToken(uri)) {
            String token = getToken(request);
            Long uid = TokenUtils.getUid(token);
            if (!passportService.checkToken(uid, token)) {
                request.getRequestDispatcher("/dealError?errorCode=" + ErrorCode.Login.TOKEN_INVALID).forward(servletRequest, servletResponse);
                return;
            }
        } else {
            //do nothing
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    public String getToken(HttpServletRequest request) {

        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("token")) {
                        return cookie.getValue();
                    }
                }
            }
        }
        return token;
    }
}
