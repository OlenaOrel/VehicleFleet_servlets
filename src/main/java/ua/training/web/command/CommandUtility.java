package ua.training.web.command;

import ua.training.dto.UserDto;
import ua.training.entity.UserRole;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

import static ua.training.web.conctant.WebConstants.*;

public class CommandUtility {
    private static final String GUEST = "guest";
    private static final int GUEST_ID = 0;

    static void setUserRole(HttpServletRequest request, UserDto userDto) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute(EMAIL_ATTRIBUTE, userDto.getEmail());
        session.setAttribute(USER_DTO_ATTRIBUTE, userDto);
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String email) {
        Set<String> loggedUsers = getLoggedUsersFromContext(request);
        return loggedUsers.stream().anyMatch(email::equals);
    }

    static void addUserToLoggedUsers(HttpServletRequest request, String email) {
        Set<String> loggedUsers = getLoggedUsersFromContext(request);
        loggedUsers.add(email);
        setLoggedUsersToContext(request, loggedUsers);
    }

    static void logOutUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        String email = (String) context.getAttribute(EMAIL_ATTRIBUTE);
        Set<String> loggedUsers = getLoggedUsersFromContext(request);
        loggedUsers.remove(email);
        context.removeAttribute(EMAIL_ATTRIBUTE);
        setLoggedUsersToContext(request, loggedUsers);
        session.removeAttribute(USER_DTO_ATTRIBUTE);
        session.invalidate();
        CommandUtility.setUserRole(request, new UserDto(GUEST_ID, GUEST, UserRole.ROLE_GUEST));
    }

    static Set<String> getLoggedUsersFromContext(HttpServletRequest request) {
        return (HashSet<String>) request.getServletContext()
                .getAttribute(LOGGED_USERS_ATTRIBUTE);
    }

    private static void setLoggedUsersToContext(HttpServletRequest request,
                                                Set<String> loggedUsers) {
        request.getServletContext()
                .setAttribute(LOGGED_USERS_ATTRIBUTE, loggedUsers);
    }
}
