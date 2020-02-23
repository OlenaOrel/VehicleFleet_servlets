package ua.training.service;

import ua.training.dto.UserRegisterDto;

import static ua.training.web.conctant.RegexConstants.*;

public class UserRegisterValidationService {

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
