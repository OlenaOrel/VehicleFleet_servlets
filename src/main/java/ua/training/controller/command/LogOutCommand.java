package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entity.UserRole;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LogOutCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        String email = (String) request.getSession().getServletContext().getAttribute("email");
        CommandUtility.logOutUser(request, email);
        CommandUtility.setUserRole(request, UserRole.GUEST, "Guest");
        LOGGER.info(CommandUtility.getLoggedUsersFromContext(request));
        return "/index.jsp";
    }
}
