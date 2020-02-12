package ua.training.web.command.appoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.entity.User;
import ua.training.service.UserService;
import ua.training.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddDriverCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AddDriverCommand.class);
    UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String busId = (String) session.getAttribute("busId");
        String driverId = request.getParameter("driverId");
        if (driverId != null) {
            LOGGER.info("DriverId: {}", driverId);
            session.setAttribute("driverId", driverId);
            return "redirect:/VF/admin/appoint/confirm";
        }
        int idBus = Integer.parseInt(busId);
        List<User> drivers = userService.getAllByBusId(idBus);
        LOGGER.info("Count of drivers: {}", drivers.size());
        if (drivers.isEmpty()) {
            //TODO handle
        }
        session.setAttribute("driverList", drivers);
        return "/WEB-INF/admin/appoint/add_driver.jsp";
    }
}
