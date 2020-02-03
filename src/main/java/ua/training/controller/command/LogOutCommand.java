package ua.training.controller.command;

import ua.training.model.entity.UserRole;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String email = (String) request.getSession().getServletContext().getAttribute("email");
        CommandUtility.logOutUser(request, email);
        CommandUtility.setUserRole(request, UserRole.GUEST, "Guest");
        return "/index.jsp";
    }
}
