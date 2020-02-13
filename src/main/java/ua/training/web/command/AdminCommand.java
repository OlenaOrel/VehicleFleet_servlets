package ua.training.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.web.conctant.WebConstants;

import javax.servlet.http.HttpServletRequest;

public class AdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AdminCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        return WebConstants.MAIN_ADMIN_PAGE;
    }
}
