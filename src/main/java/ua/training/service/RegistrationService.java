package ua.training.service;

import org.mindrot.jbcrypt.BCrypt;
import ua.training.dto.UserRegisterDto;
import ua.training.entity.User;
import ua.training.entity.UserRole;

import javax.servlet.http.HttpServletRequest;

import static ua.training.web.conctant.RegexConstants.*;

public class RegistrationService {

    public UserRegisterDto createUserRegisterDto(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String originFirstName = request.getParameter("originFirstName");
        String originLastName = request.getParameter("originLastName");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String confirmPass = request.getParameter("confirmPass");
        return new UserRegisterDto(firstName, lastName,
                originFirstName, originLastName, email, pass, confirmPass);
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

    public boolean isPassNotConfirm(String pass, String confirmPass) {
        return !pass.equals(confirmPass);
    }

    public boolean isInputValid(UserRegisterDto userDto) {
        return userDto.getFirstName().matches(NAME_EN)
                && userDto.getLastName().matches(NAME_EN)
                && userDto.getOriginFirstName().matches(NAME_UK)
                && userDto.getOriginLastName().matches(NAME_UK)
                && userDto.getEmail().matches(EMAIL);
    }

    public User getUserFromUserRegisterDto(UserRegisterDto userDto) {
        return new User.Builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .originFirstName(userDto.getOriginFirstName())
                .originLastName(userDto.getOriginLastName())
                .email(userDto.getEmail())
                .password(getSecurePass(userDto.getPassword()))
                .role(UserRole.ROLE_DRIVER)
                .build();
    }

    private String getSecurePass(String pass) {
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    }
}
