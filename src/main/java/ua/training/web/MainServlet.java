package ua.training.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.web.command.*;
import ua.training.web.command.appoint.AddBusCommand;
import ua.training.web.command.appoint.AddDriverCommand;
import ua.training.web.command.appoint.AddRouteCommand;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MainServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(MainServlet.class);
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
        commands.put("/login", new LoginCommand());
        commands.put("/logout", new LogOutCommand());
        commands.put("/driver", new DriverCommand());
        commands.put("/denied", new AccessDeniedCommand());
        commands.put("/admin", new AdminCommand());
        commands.put("/admin/appoint/route", new AddRouteCommand());
        commands.put("/admin/appoint/bus", new AddBusCommand());
        commands.put("/admin/appoint/driver", new AddDriverCommand());
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
        Command command = commands.getOrDefault(path, (requestDefault) -> "/index.jsp");
        String page = command.execute(request);
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", ""));
            return;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}
