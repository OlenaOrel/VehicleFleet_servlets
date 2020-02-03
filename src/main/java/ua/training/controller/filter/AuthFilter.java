package ua.training.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entity.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String path = request.getRequestURI();
        UserRole role = (UserRole) session.getAttribute("role");

        LOGGER.info("Path: {}", path);
        LOGGER.info("Role: {}", role);

        if ((isInputParameterNotPresent(email, pass) && role == null)
                || path.contains("logout")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (role.equals(UserRole.GUEST) && !path.contains("login")) {
            response.sendRedirect("login");
            return;
        }

        if (isUserLogged(session)) {
            if ((isPathCorrectForUser(role, path))) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                response.sendRedirect("logout");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isInputParameterNotPresent(String email, String pass) {
        return email == null || email.equals("")
                || pass == null || pass.equals("");
    }

    private boolean isUserLogged(HttpSession session) {
        return session != null
                && session.getAttribute("role") != null
                && !session.getAttribute("role").equals(UserRole.GUEST);
    }

    private boolean isPathCorrectForUser(UserRole role, String path) {
        return (role.equals(UserRole.ROLE_DRIVER) && path.contains("driver"))
                || (role.equals(UserRole.ROLE_ADMIN) && path.contains("admin"));
    }


    @Override
    public void destroy() {

    }
}
