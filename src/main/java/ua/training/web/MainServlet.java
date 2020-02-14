package ua.training.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.web.command.*;
import ua.training.web.command.appoint.AddBusCommand;
import ua.training.web.command.appoint.AddDriverCommand;
import ua.training.web.command.appoint.AddRouteCommand;
import ua.training.web.command.appoint.ConfirmAppointCommand;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static ua.training.web.conctant.WebConstants.*;

public class MainServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(MainServlet.class);
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext()
                .setAttribute(LOGGED_USERS_ATTRIBUTE, new HashSet<String>());
        commands.put(LOGIN_PATH, new LoginCommand());
        commands.put(LOGOUT_PATH, new LogOutCommand());
        commands.put(DRIVER_PATH, new DriverCommand());
        commands.put(DENIED_PATH, new AccessDeniedCommand());
        commands.put(ADMIN_PATH, new AdminCommand());
        commands.put(APPOINT_ROUTE_PATH, new AddRouteCommand());
        commands.put(APPOINT_BUS_PATH, new AddBusCommand());
        commands.put(APPOINT_DRIVER_PATH, new AddDriverCommand());
        commands.put(CONFIRM_APPOINT_PATH, new ConfirmAppointCommand());
        commands.put(REGISTRATION_PATH, new RegisterCommand());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        String contextPath = request.getContextPath();
        LOGGER.info("Path: {}", path);
        path = path.replace(contextPath, "");
        Command command = commands.getOrDefault(path, (requestDefault) -> MAIN_PAGE);
        String page = command.execute(request);
        if (page.contains(REDIRECT)) {
            response.sendRedirect(page.replace(REDIRECT, ""));
            return;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}
