package ua.training.web.filter;

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
        String mainPath = request.getContextPath() + "/";
        UserRole role = (UserRole) session.getAttribute("role");

        LOGGER.info("Path: {}", path);
        LOGGER.info("Role: {}", role);

        boolean isGuestPath = path.equals(mainPath) || path.contains("login")
                || path.contains("register") || path.contains("denied");

        if (isInputParameterNotPresent(email, pass) && isUserGuest(role)) {
            if (isGuestPath) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            response.sendRedirect(mainPath);
            return;
        }

        if (path.contains("login") && isUserGuest(role)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (isUserLogged(session, role)) {
            if (isPathAccessDenied(role, path, mainPath)) {
                response.sendRedirect("denied");
                return;
            }
            if (isGuestPath) {
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

    private boolean isUserGuest(UserRole role) {
        return role == null || role.equals(UserRole.ROLE_GUEST);
    }


    private boolean isUserLogged(HttpSession session, UserRole role) {
        return session != null
                && !isUserGuest(role);
    }

    private boolean isPathAccessDenied(UserRole role, String path, String mainPath) {
        path = path.replace(mainPath, "");
        return (role.equals(UserRole.ROLE_DRIVER) && path.contains("admin"))
                || (role.equals(UserRole.ROLE_ADMIN) && path.startsWith("driver"));
    }


    @Override
    public void destroy() {

    }
}
