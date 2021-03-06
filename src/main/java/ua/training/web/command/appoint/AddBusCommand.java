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

    private BusService busService;

    public AddBusCommand() {
        busService = new BusService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(EMPTY_BUS_LIST_ATTRIBUTE, true);
        String busId = request.getParameter(BUS_ID_ATTRIBUTE);
        boolean isBusListEmpty = (Boolean) session.getAttribute(EMPTY_BUS_LIST_ATTRIBUTE);
        if (busId != null) {
            LOGGER.info("BusId: {}", busId);
            session.setAttribute(BUS_ID_ATTRIBUTE, busId);
            return REDIRECT + ROOT_PATH + APPOINT_DRIVER_PATH;
        }
        if (isBusListEmpty) {
            List<Bus> busList = busService.getNotAppointBus();
            LOGGER.info("Count of buses: {}", busList.size());
            if (!busList.isEmpty()) {
                session.setAttribute(EMPTY_BUS_LIST_ATTRIBUTE, false);
                session.setAttribute(BUS_LIST_ATTRIBUTE, busList);
                return APPOINT_BUS_PAGE;
            }
        }
        return APPOINT_BUS_PAGE;
    }
}
