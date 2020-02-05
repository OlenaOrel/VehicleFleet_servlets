package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entity.User;
import ua.training.model.entity.UserRole;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        boolean isInputNotPresent = (email == null || email.equals("")
                || pass == null || pass.equals(""));

        if (isInputNotPresent) {
            return "/login.jsp";
        }

        Optional<User> loginUser = userService.getUserByEmailAndPassword(email, pass);

        if (CommandUtility.checkUserIsLogged(request, email)
                || !loginUser.isPresent()) {
            return "/WEB-INF/error.jsp";
        }

        LOGGER.info(CommandUtility.getLoggedUsersFromContext(request));

        User user = loginUser.get();
        if (userService.isPassCorrect(pass, user.getPassword())) {
            UserRole role = user.getRole();
            LOGGER.info("User role: '" + role + "'");

            if (role.equals(UserRole.ROLE_ADMIN)) {
                CommandUtility.setUserRole(request, role, email);
                return "redirect:/VF/admin";
            }
            if (role.equals(UserRole.ROLE_DRIVER)) {
                CommandUtility.setUserRole(request, role, email);
                return "redirect:/VF/driver";
            }

        } else {
            CommandUtility.setUserRole(request, UserRole.GUEST, email);
            return "/login?error=true";
        }
        return "/login.jsp";
    }
}
