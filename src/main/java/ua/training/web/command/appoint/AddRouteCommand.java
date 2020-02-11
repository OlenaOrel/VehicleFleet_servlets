package ua.training.web.command.appoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.entity.Route;
import ua.training.service.RouteService;
import ua.training.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddRouteCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddRouteCommand.class);
    RouteService routeService = new RouteService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String routeId = request.getParameter("routeId");
        if (routeId != null) {
            LOGGER.info("RouteId: {}", routeId);
            session.setAttribute("routeId", routeId);
            return "redirect:admin/appoint/bus";
        }
        List<Route> routes = routeService.getAllRouts();
        LOGGER.info("Count of route: {}", routes.size());
        session.setAttribute("routes", routes);
        return "/WEB-INF/admin/appoint/add_route.jsp";
    }
}
