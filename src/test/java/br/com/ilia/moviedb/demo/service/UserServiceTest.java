package br.com.ilia.moviedb.demo.service;

import br.com.ilia.moviedb.demo.domain.User;
import br.com.ilia.moviedb.demo.repository.UserRepository;
import br.com.ilia.moviedb.demo.service.dto.UserDTO;
import br.com.ilia.moviedb.demo.service.dto.UserNewDTO;
import br.com.ilia.moviedb.demo.service.exception.EmailAlreadyInUseException;
import br.com.ilia.moviedb.demo.service.exception.InvalidParamException;
import br.com.ilia.moviedb.demo.service.mapper.UserMapper;
import br.com.ilia.moviedb.factory.domain.UserFactory;
import br.com.ilia.moviedb.factory.dto.UserDTOFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Before
    public void setup() {
        userService = new UserService(userRepository, userMapper, bCryptPasswordEncoder);
    }

    @Test
    public void deveGetUserByEmail(){
        when(userRepository.findByEmail(anyString())).thenReturn(UserFactory.umUsuario());
        User user = userService.getUserByEmail("any_email@mail.com");
        verify(userRepository, times(1)).findByEmail(anyString());
        assertThat(user).isNotNull();
    }

    @Test
    public void deveSaveUser(){
        UserNewDTO userNewDTO = UserNewDTO.builder()
                .password("any_password")
                .passwordConfirmation("any_password")
                .email("any_email@mail.com")
                .name("any_name").build();
        when(userMapper.dtoParaEntidade(any(UserNewDTO.class))).thenReturn(UserFactory.umUsuario());
        when(userRepository.save(any(User.class))).thenReturn(UserFactory.umUsuario());
        User user = userService.saveUser(userNewDTO);
        verify(userRepository, times(1)).findByEmail(anyString());
        assertThat(user).isNotNull();
    }

    @Test(expected = EmailAlreadyInUseException.class)
    public void deveSaveUserButEmailIsAlreadyInUse(){
        UserNewDTO userNewDTO = UserNewDTO.builder()
                .password("any_password")
                .passwordConfirmation("any_password")
                .email("any_email@mail.com")
                .name("any_name").build();
        when(userRepository.findByEmail(anyString())).thenReturn(UserFactory.umUsuario());
        userService.saveUser(userNewDTO);
        verify(userRepository, times(1)).findByEmail(anyString());
    }
    @Test(expected = InvalidParamException.class)
    public void deveSaveUserButPasswordConfirmationIsDifferent(){
        UserNewDTO userNewDTO = UserNewDTO.builder()
                .password("any_password")
                .passwordConfirmation("other_password")
                .email("any_email@mail.com")
                .name("any_name").build();
        userService.saveUser(userNewDTO);
        verify(userRepository, times(1)).findByEmail(anyString());
    }
}
