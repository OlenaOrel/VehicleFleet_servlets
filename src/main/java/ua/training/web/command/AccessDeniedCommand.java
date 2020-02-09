package ua.training.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AccessDeniedCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AccessDeniedCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        LOGGER.warn("User with email: '" + email + "'try to go to forbidden page");
        CommandUtility.logOutUser(request);
        LOGGER.info("Role: {}", request.getSession().getAttribute("role"));
        return "/access_denied.jsp";
    }
}
