package ua.training.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.web.conctant.WebConstants;

import javax.servlet.http.HttpServletRequest;

public class AccessDeniedCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AccessDeniedCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter(WebConstants.EMAIL_ATTRIBUTE);
        LOGGER.warn("User tried to go to forbidden page");
        CommandUtility.logOutUser(request);
        LOGGER.info("Role: {}", request.getSession().getAttribute(WebConstants.ROLE_ATTRIBUTE));
        return WebConstants.DENIED_PAGE;
    }
}
