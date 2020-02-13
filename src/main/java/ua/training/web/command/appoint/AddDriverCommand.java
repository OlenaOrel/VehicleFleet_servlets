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
        session.setAttribute(EMPTY_DRIVER_LIST_ATTRIBUTE, true);
        String driverId = request.getParameter(DRIVER_ID_ATTRIBUTE);
        String busId = (String) session.getAttribute(BUS_ID_ATTRIBUTE);
        boolean isDriverListEmpty = (Boolean) session.getAttribute(EMPTY_DRIVER_LIST_ATTRIBUTE);
        if (driverId != null) {
            LOGGER.info("DriverId: {}", driverId);
            session.setAttribute(DRIVER_ID_ATTRIBUTE, driverId);
            return REDIRECT + ROOT_PATH + CONFIRM_APPOINT_PATH;
        }
        if (isDriverListEmpty) {
            int idBus = Integer.parseInt(busId);
            List<User> driverList = userService.getNotAppointDriverForBus(idBus);
            LOGGER.info("Count of drivers: {}", driverList.size());
            if (!driverList.isEmpty()) {
                session.setAttribute(EMPTY_DRIVER_LIST_ATTRIBUTE, false);
                session.setAttribute(DRIVER_LIST_ATTRIBUTE, driverList);
            }
        }
        return APPOINT_DRIVER_PAGE;
    }
}
