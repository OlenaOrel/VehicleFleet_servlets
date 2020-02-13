package ua.training.web.filter;

import ua.training.web.conctant.WebConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocaleFilter implements Filter {
    private static final String DEFAULT_LANG = "en";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String locale = servletRequest.getParameter(WebConstants.LANG_ATTRIBUTE);
        String currentLocale = (String) request.getSession().getAttribute(WebConstants.LANG_ATTRIBUTE);
        if (currentLocale == null) {
            request.getSession().setAttribute(WebConstants.LANG_ATTRIBUTE, DEFAULT_LANG);
            filterChain.doFilter(request, response);
            return;
        }
        if (locale != null && !locale.equals(currentLocale)) {
            request.getSession().setAttribute(WebConstants.LANG_ATTRIBUTE, locale);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
