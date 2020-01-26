package ua.training.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private final String ENCODING = "UTF-8";
    private final String CONTENT_TYPE = "text/html";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType(CONTENT_TYPE);
        servletRequest.setCharacterEncoding(ENCODING);
        servletResponse.setCharacterEncoding(ENCODING);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
