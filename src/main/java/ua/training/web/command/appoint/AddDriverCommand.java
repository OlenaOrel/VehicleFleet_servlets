package ua.training.web.command.appoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.entity.User;
import ua.training.service.UserService;
import ua.training.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static ua.training.web.conctant.WebConstants.*;

public class AddDriverCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AddDriverCommand.class);
    UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String busId = (String) session.getAttribute(BUS_ID_ATTRIBUTE);
        String driverId = request.getParameter(DRIVER_ID_ATTRIBUTE);
        if (driverId != null) {
            LOGGER.info("DriverId: {}", driverId);
            session.setAttribute(DRIVER_ID_ATTRIBUTE, driverId);
            return REDIRECT + ROOT_PATH + CONFIRM_APPOINT_PATH;
        }
        int idBus = Integer.parseInt(busId);
        List<User> drivers = userService.getAllByBusId(idBus);
        LOGGER.info("Count of drivers: {}", drivers.size());
        if (drivers.isEmpty()) {
            //TODO handle
        }
        session.setAttribute(DRIVER_LIST_ATTRIBUTE, drivers);
        return APPOINT_DRIVER_PAGE;
    }
}
