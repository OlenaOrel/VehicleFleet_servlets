package ua.training.web.command.appoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.entity.Bus;
import ua.training.service.BusService;
import ua.training.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static ua.training.web.conctant.WebConstants.*;

public class AddBusCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddBusCommand.class);
    BusService busService = new BusService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String busId = request.getParameter(BUS_ID_ATTRIBUTE);
        if (busId != null) {
            LOGGER.info("BusId: {}", busId);
            session.setAttribute(BUS_ID_ATTRIBUTE, busId);
            return REDIRECT + ROOT_PATH + APPOINT_DRIVER_PATH;
        }
        List<Bus> busList = busService.getAllBuses();
        LOGGER.info("Count of buses: {}", busList.size());
        session.setAttribute(BUS_LIST_ATTRIBUTE, busList);
        return APPOINT_BUS_PAGE;
    }
}
