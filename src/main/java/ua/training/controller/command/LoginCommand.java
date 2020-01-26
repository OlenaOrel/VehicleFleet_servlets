package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private UserService userService;

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        System.out.println(name + " " + pass);
        if (isNotInputPresent(name) || isNotInputPresent(pass)) {
            return "/login.jsp";
        }
//        Optional<User> user = userService.login(name);
//        if( user.isPresent() && user.get().getPassHash()
//                == pass.hashCode()){
//            request.getSession().setAttribute("teacher" , teacher.get());
//            logger.info("Teacher "+ name+" logged successfully.");
//            return "/WEB-INF/studentlist.jsp";
//
//        }
        logger.info("Invalid attempt of login user:'" + name + "'");
        return "/login.jsp";
    }

    public boolean isNotInputPresent(String input) {
        return input == null || input.equals("");
    }

//    private String encodePassword( String password ) {
//        return new BCryptPasswordEncoder().encode( password );
//    }
}
