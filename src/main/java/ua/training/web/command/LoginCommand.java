package ua.training.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.entity.User;
import ua.training.entity.UserRole;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static ua.training.web.conctant.WebConstants.*;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private static final String GUEST = "guest";
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter(EMAIL_ATTRIBUTE);
        String pass = request.getParameter(PASS_ATTRIBUTE);

        boolean isInputNotPresent = (email == null || email.equals("")
                || pass == null || pass.equals(""));

        if (isInputNotPresent) {
            return LOGIN_PAGE;
        }

        Optional<User> loginUser = userService.getUserByEmail(email);

        if (CommandUtility.checkUserIsLogged(request, email)
                || !loginUser.isPresent()) {
            LOGGER.info("User: {} has already logged in", email);
            return ERROR_PAGE;
        }

        User user = loginUser.get();
        if (userService.isPassCorrect(pass, user.getPassword())) {
            CommandUtility.addUserToLoggedUsers(request, user.getEmail());
            UserRole role = user.getRole();
            LOGGER.info("User role: '" + role + "'");
            LOGGER.info(CommandUtility.getLoggedUsersFromContext(request));

            if (role.equals(UserRole.ROLE_ADMIN)) {
                CommandUtility.setUserRole(request, role, email);
                return REDIRECT + ROOT_PATH + ADMIN_PATH;
            }
            if (role.equals(UserRole.ROLE_DRIVER)) {
                CommandUtility.setUserRole(request, role, email);
                return REDIRECT + ROOT_PATH + DRIVER_PATH;
            }

        } else {
            CommandUtility.setUserRole(request, UserRole.ROLE_GUEST, GUEST);
            return LOGIN_PAGE;
        }
        LOGGER.info(CommandUtility.getLoggedUsersFromContext(request));
        return LOGIN_PAGE;
    }
}
