package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.dto.UserDto;
import ua.training.model.entity.UserRole;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        boolean isInputNotPresent = (email == null || email.equals("")
                || pass == null || pass.equals(""));

        if (isInputNotPresent) {
            return "/login.jsp";
        }

        //TODO work with DAO
        Optional<UserDto> user = userService.getUserByEmailAndPassword(email, pass);
        if (CommandUtility.checkUserIsLogged(request, email)) {
            return "/WEB-INF/error.jsp";
        }

        if (!user.isPresent()) {
            return "/WEB-INF/error.jsp";
        }

        UserRole role = user.get().getRole();
        LOGGER.info("User role: '" + role + "'");
        if (role.equals(UserRole.ROLE_ADMIN)) {
            CommandUtility.setUserRole(request, role, email);
            return "redirect:/VF/admin";
        }
        if (role.equals(UserRole.ROLE_DRIVER)) {
            CommandUtility.setUserRole(request, role, email);
            return "redirect:/VF/driver";
        } else {
            CommandUtility.setUserRole(request, UserRole.GUEST, email);
            return "/login";
        }


//        Optional<User> user = userService.login(name);
//        if( user.isPresent() && user.get().getPassHash()
//                == pass.hashCode()){
//            request.getSession().setAttribute("teacher" , teacher.get());
//            logger.info("Teacher "+ name+" logged successfully.");
//            return "/WEB-INF/studentlist.jsp";
//
//        }

    }


//    private String encodePassword( String password ) {
//        return new BCryptPasswordEncoder().encode( password );
//    }
}
