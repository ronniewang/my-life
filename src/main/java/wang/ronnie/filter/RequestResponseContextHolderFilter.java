package wang.ronnie.filter;

import wang.ronnie.global.Log;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public class RequestResponseContextHolderFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        Map param = req.getParameterMap();
        StringBuilder sb = new StringBuilder();
        sb.append(req.getRequestURI()).append("(");
        for (Object key : param.keySet()) {
            sb.append(key).append(":").append(req.getParameter(key + "")).append("|");
        }
        sb.append(")");
        Log.info(sb.toString());

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}