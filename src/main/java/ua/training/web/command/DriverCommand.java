package ua.training.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.web.conctant.WebConstants;

import javax.servlet.http.HttpServletRequest;

public class DriverCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DriverCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        return WebConstants.DRIVER_PAGE;
    }
}
