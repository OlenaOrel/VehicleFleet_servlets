package ua.training.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dto.UserRegisterDto;
import ua.training.entity.User;
import ua.training.exception.UserExistException;
import ua.training.service.RegistrationService;
import ua.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.training.web.conctant.RegexConstants.*;
import static ua.training.web.conctant.WebConstants.*;

public class RegisterCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);

    private RegistrationService regService = new RegistrationService();
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(PASS_NOT_CONFIRM_ATTRIBUTE, false);
        session.setAttribute(INVALID_INPUT_ATTRIBUTE, false);
        UserRegisterDto userDto = regService.createUserRegisterDto(request);
        if (isInputNotPresent(userDto)) {
            LOGGER.info("empty parameters");
            return REGISTER_PAGE;
        }
        if (regService.isPassNotConfirm(userDto.getPassword(), userDto.getConfirmPassword())) {
            session.setAttribute(PASS_NOT_CONFIRM_ATTRIBUTE, true);
            LOGGER.info("Password is not confirm");
            return REGISTER_PAGE;
        }
        if (isInputInvalid(userDto)) {
            session.setAttribute(INVALID_INPUT_ATTRIBUTE, true);
            LOGGER.info("Invalid input");
            return REGISTER_PAGE;
        }

        User user = regService.getUserFromUserRegisterDto(userDto);
        try {
            userService.saveUser(user);
        } catch (UserExistException e) {
            LOGGER.info("User with email {} exist", userDto.getEmail());
            session.setAttribute(ERROR_ATTRIBUTE, true);
            return REGISTER_PAGE;
        }
        return REDIRECT + ROOT_PATH + LOGIN_PATH;
    }

    public boolean isInputNotPresent(UserRegisterDto userDto) {
        return userDto.getFirstName() == null || userDto.getFirstName().equals("")
                || userDto.getLastName() == null || userDto.getLastName().equals("")
                || userDto.getOriginFirstName() == null || userDto.getOriginFirstName().equals("")
                || userDto.getOriginLastName() == null || userDto.getOriginLastName().equals("")
                || userDto.getEmail() == null || userDto.getEmail().equals("")
                || userDto.getPassword() == null || userDto.getPassword().equals("")
                || userDto.getConfirmPassword() == null || userDto.getConfirmPassword().equals("");
    }

    public boolean isInputInvalid(UserRegisterDto userDto) {
        return !(userDto.getFirstName().matches(NAME_EN)
                && userDto.getLastName().matches(NAME_EN)
                && userDto.getOriginFirstName().matches(NAME_UK)
                && userDto.getOriginLastName().matches(NAME_UK)
                && userDto.getEmail().matches(EMAIL));
    }

}
