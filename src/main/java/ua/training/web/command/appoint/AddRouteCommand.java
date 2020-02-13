package ua.training.web.command.appoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.entity.Route;
import ua.training.service.RouteService;
import ua.training.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static ua.training.web.conctant.WebConstants.*;

public class AddRouteCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddRouteCommand.class);
    RouteService routeService = new RouteService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String routeId = request.getParameter(ROUTE_ID_ATTRIBUTE);
        if (routeId != null) {
            LOGGER.info("RouteId: {}", routeId);
            session.setAttribute(ROUTE_ID_ATTRIBUTE, routeId);
            return REDIRECT + ROOT_PATH + APPOINT_BUS_PATH;
        }
        List<Route> routeList = routeService.getAllRouts();
        LOGGER.info("Count of route: {}", routeList.size());
        session.setAttribute(ROUTE_LIST_ATTRIBUTE, routeList);
        return APPOINT_ROUTE_PAGE;
    }
}
