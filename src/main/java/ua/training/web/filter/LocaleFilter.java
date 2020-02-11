package ua.training.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String locale = servletRequest.getParameter("lang");
        String currentLocale = (String) request.getSession().getAttribute("lang");
        if (currentLocale == null) {
            request.getSession().setAttribute("lang", "en");
            filterChain.doFilter(request, response);
            return;
        }
        if (locale != null && !locale.equals(currentLocale)) {
            request.getSession().setAttribute("lang", locale);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
