package ua.training.controller;

import ua.training.controller.command.Command;
import ua.training.controller.command.LoginCommand;
import ua.training.model.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {
    //TODO
    private UserService userService = new UserService();
    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
        commands.put("login",
                new LoginCommand());
//        commands.put("add-student" , new AddStudent());
//        commands.put("teacher-login",
//                new LoginTeacherCommand(new TeacherService()));
//        commands.put("exception" , new ExceptionCommand());
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
        System.out.println(path);
        path = path.replace("/VF/", "");
        System.out.println(path);
        Command command = commands.getOrDefault(path, (request1) -> "index.jsp");
        System.out.println(command.getClass().getName());
        String page = command.execute(request);
        request.getRequestDispatcher(page).forward(request, response);
    }
}
