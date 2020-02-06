package ua.training.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entity.UserRole;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

public class CommandUtility {
    private static Logger LOGGER = LogManager.getLogger(CommandUtility.class);

    static void setUserRole(HttpServletRequest request,
                            UserRole role, String email) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("email", email);
        session.setAttribute("role", role);
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String userName) {
        Set<String> loggedUsers = getLoggedUsersFromContext(request);

        if (loggedUsers.stream().anyMatch(userName::equals)) {
            return true;
        }
        loggedUsers.add(userName);
        setLoggedUsersToContext(request, loggedUsers);
        return false;
    }

    static void logOutUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = (String) request.getSession().getServletContext().getAttribute("email");
        Set<String> loggedUsers = getLoggedUsersFromContext(request);
        loggedUsers.remove(email);
        request.getServletContext().removeAttribute("message.email");
        setLoggedUsersToContext(request, loggedUsers);
        session.removeAttribute("role");
        session.invalidate();
        CommandUtility.setUserRole(request, UserRole.ROLE_GUEST, "Guest");
    }

    static Set<String> getLoggedUsersFromContext(HttpServletRequest request) {
        return (HashSet<String>) request.getSession().getServletContext().getAttribute("loggedUsers");
    }

    private static void setLoggedUsersToContext(HttpServletRequest request, Set<String> loggedUsers) {
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
    }
}
