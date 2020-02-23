package ua.training.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.training.dao.UserDao;
import ua.training.dto.UserDto;
import ua.training.dto.UserRegisterDto;
import ua.training.entity.User;
import ua.training.entity.UserRole;
import ua.training.exception.UserExistException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private static final String USER_EMAIL = "user email";
    private static final int USER_ID = 0;
    private static final UserRole USER_ROLE = UserRole.ROLE_GUEST;
    private static final String USER_PASS = "user pass";
    private static final String SECURE_PASS = BCrypt.hashpw(USER_PASS, BCrypt.gensalt());
    private static final String USER_CONFIRM_PASS = "confirm pass";
    private static final int BUS_ID = 0;


    @InjectMocks
    private UserService instance;

    @Mock
    private UserDao userDao;

    @Mock
    private User user;

    @Mock
    private UserRegisterDto userRegisterDto;

    @Before
    public void setUp() {

    }

    @Test
    public void shouldReturnUserWhenUserExist() {
        when(userDao.findByEmail(anyString())).thenReturn(Optional.of(user));

        Optional<User> result = instance.getUserByEmail(USER_EMAIL);

        assertThat(result).isNotEmpty();
        assertThat(result).isEqualTo(Optional.of(user));
    }


    @Test
    public void shouldReturnEmptyWhenUserNotExist() {
        Optional<User> result = instance.getUserByEmail(USER_EMAIL);

        assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnTrueWhenPassCorrect() {
        boolean result = instance.isPassCorrect(USER_PASS, SECURE_PASS);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenPassCorrect() {
        boolean result = instance.isPassCorrect(USER_EMAIL, SECURE_PASS);

        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnUserWhenUserFound() {
        when(userDao.findById(anyInt())).thenReturn(Optional.of(user));

        Optional<User> result = instance.getUserById(USER_ID);

        assertThat(result).isNotEmpty();
        assertThat(result).isEqualTo(Optional.of(user));
    }

    @Test
    public void shouldReturnEmptyWhenUserNotFound() {
        Optional<User> result = instance.getUserById(USER_ID);

        assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnUserDtoWhenConvertUser() {
        when(user.getId()).thenReturn(USER_ID);
        when(user.getEmail()).thenReturn(USER_EMAIL);
        when(user.getRole()).thenReturn(USER_ROLE);

        UserDto result = instance.convertUserToDto(user);

        assertThat(result.getId()).isEqualTo(USER_ID);
        assertThat(result.getEmail()).isEqualTo(USER_EMAIL);
        assertThat(result.getRole()).isEqualTo(USER_ROLE);
    }

    @Test
    public void shouldReturnTrueWhenPassNotConfirm() {
        boolean result = instance.isPassNotConfirm(USER_PASS, USER_CONFIRM_PASS);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenPassNotConfirm() {
        boolean result = instance.isPassNotConfirm(USER_PASS, USER_PASS);

        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnUserWhenCreateUserFromUserRegisterDto() {
        User result = instance.createUserFromUserRegisterDto(userRegisterDto);

        assertThat(result).isNotNull();
    }

    @Test
    public void shouldReturnUserListWhenDriversForBusExist() {
        when(userDao.findNotAppointDriverForBusByDate(any(LocalDate.class), anyInt())).thenReturn(Collections.singletonList(user));

        List<User> result = instance.getNotAppointDriverForBus(BUS_ID);

        assertThat(result).contains(user);
    }

    @Test
    public void shouldSuccessfulSaveUserWhenUserNew() throws UserExistException {
        instance.saveUser(user);

        verify(userDao).saveUser(user);
    }

    @Test(expected = UserExistException.class)
    public void shouldTrowUserExistExceptionWhenUserAlreadyExist() throws UserExistException {
        doThrow(new UserExistException("")).when(userDao).saveUser(user);

        instance.saveUser(user);
    }
}
