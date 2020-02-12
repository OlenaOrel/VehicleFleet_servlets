package ua.training.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.web.conctant.WebConstants;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LogOutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        CommandUtility.logOutUser(request);
        LOGGER.info("Logged users: {}", CommandUtility.getLoggedUsersFromContext(request));
        LOGGER.info("Role: {}", request.getSession().getAttribute(WebConstants.ROLE_ATTRIBUTE));
        return WebConstants.MAIN_PAGE;
    }
}
