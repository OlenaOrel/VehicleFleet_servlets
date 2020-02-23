package ua.training.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dto.UserRegisterDto;
import ua.training.entity.User;
import ua.training.exception.UserExistException;
import ua.training.service.UserRegisterValidationService;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.training.web.conctant.WebConstants.*;

public class RegisterCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);

    private UserService userService;
    private UserRegisterValidationService userRegisterValidationService;

    public RegisterCommand() {
        userService = new UserService();
        userRegisterValidationService = new UserRegisterValidationService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(PASS_NOT_CONFIRM_ATTRIBUTE, false);
        session.setAttribute(INVALID_INPUT_ATTRIBUTE, false);
        UserRegisterDto userDto = createUserRegisterDto(request);
        if (userRegisterValidationService.isInputNotPresent(userDto)) {
            LOGGER.info("empty parameters");
            return REGISTER_PAGE;
        }
        if (userService.isPassNotConfirm(userDto.getPassword(), userDto.getConfirmPassword())) {
            session.setAttribute(PASS_NOT_CONFIRM_ATTRIBUTE, true);
            LOGGER.info("Password is not confirm");
            return REGISTER_PAGE;
        }
        if (userRegisterValidationService.isInputInvalid(userDto)) {
            session.setAttribute(INVALID_INPUT_ATTRIBUTE, true);
            LOGGER.info("Invalid input");
            return REGISTER_PAGE;
        }

        User user = userService.createUserFromUserRegisterDto(userDto);
        try {
            userService.saveUser(user);
        } catch (UserExistException e) {
            LOGGER.info("User with email {} exist", userDto.getEmail());
            session.setAttribute(ERROR_ATTRIBUTE, true);
            return REGISTER_PAGE;
        }
        return REDIRECT + ROOT_PATH + LOGIN_PATH;
    }

    private UserRegisterDto createUserRegisterDto(HttpServletRequest request) {
        String firstName = request.getParameter(FIRST_NAME_ATTRIBUTE);
        String lastName = request.getParameter(LAST_NAME_ATTRIBUTE);
        String originFirstName = request.getParameter(ORIGIN_FIRST_NAME_ATTRIBUTE);
        String originLastName = request.getParameter(ORIGIN_LAST_NAME_ATTRIBUTE);
        String email = request.getParameter(EMAIL_ATTRIBUTE);
        String pass = request.getParameter(PASS_ATTRIBUTE);
        String confirmPass = request.getParameter(PASS_CONFIRM_ATTRIBUTE);
        return new UserRegisterDto(firstName, lastName,
                originFirstName, originLastName, email, pass, confirmPass);
    }

}
