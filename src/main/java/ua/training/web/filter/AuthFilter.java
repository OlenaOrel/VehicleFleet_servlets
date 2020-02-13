package ua.training.web.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.entity.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.training.web.conctant.WebConstants.*;

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

        String email = request.getParameter(EMAIL_ATTRIBUTE);
        String pass = request.getParameter(PASS_ATTRIBUTE);
        String path = request.getRequestURI();
        String contextPath = request.getContextPath();
        String mainPath = contextPath + "/";
        UserRole role = (UserRole) session.getAttribute(ROLE_ATTRIBUTE);

        LOGGER.info("Path: {}", path);
        LOGGER.info("Role: {}", role);

        boolean isGuestPath = path.equals(mainPath) || path.contains(LOGIN_PATH)
                || path.contains(REGISTRATION_PATH);

        if (isInputParameterNotPresent(email, pass) && isUserGuest(role)) {
            if (isGuestPath || path.contains(DENIED_PATH)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            response.sendRedirect(mainPath);
            return;
        }

        if ((path.contains(LOGIN_PATH) || path.contains(REGISTRATION_PATH))
                && isUserGuest(role)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (isUserLogged(session, role)) {
            if (isPathAccessDenied(role, path, contextPath)) {
                response.sendRedirect(contextPath + DENIED_PATH);
                return;
            }
            if (isGuestPath) {
                response.sendRedirect(contextPath + LOGOUT_PATH);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isInputParameterNotPresent(String email, String pass) {
        return email == null || email.equals("")
                || pass == null || pass.equals("");
    }

    private boolean isUserGuest(UserRole role) {
        return role == null || role.equals(UserRole.ROLE_GUEST);
    }


    private boolean isUserLogged(HttpSession session, UserRole role) {
        return session != null
                && !isUserGuest(role);
    }

    private boolean isPathAccessDenied(UserRole role, String path, String contextPath) {
        path = path.replace(contextPath, "");
        return (role.equals(UserRole.ROLE_DRIVER) && path.contains(ADMIN_PATH))
                || (role.equals(UserRole.ROLE_ADMIN) && path.startsWith(DRIVER_PATH));
    }


    @Override
    public void destroy() {

    }
}
