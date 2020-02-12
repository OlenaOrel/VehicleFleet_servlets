package ua.training.web.command.appoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.entity.Bus;
import ua.training.service.BusService;
import ua.training.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddBusCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddBusCommand.class);
    BusService busService = new BusService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String busId = request.getParameter("busId");
        if (busId != null) {
            LOGGER.info("BusId: {}", busId);
            session.setAttribute("busId", busId);
            return "redirect:/VF/admin/appoint/driver";
        }
        List<Bus> buses = busService.getAllBuses();
        LOGGER.info("Count of buses: {}", buses.size());
        session.setAttribute("buses", buses);
        return "/WEB-INF/admin/appoint/add_bus.jsp";
    }
}
