package br.com.ilia.moviedb.demo.service;

import br.com.ilia.moviedb.demo.domain.User;
import br.com.ilia.moviedb.demo.repository.UserRepository;
import br.com.ilia.moviedb.demo.service.mapper.UserMapper;
import br.com.ilia.moviedb.factory.domain.UserFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.anyString;


@RunWith(SpringRunner.class)
public class UserDetailsServiceImplTest {

    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Before
    public void setup() {
        userDetailsService = new UserDetailsServiceImpl(userRepository);
    }

    @Test
    public void deveLoadUserByUsername(){
        when(userRepository.findByEmail(anyString())).thenReturn(UserFactory.umUsuario());
        UserDetails userDetails = userDetailsService.loadUserByUsername("any_email@mail.com");
        verify(userRepository, times(1)).findByEmail(anyString());
        assertThat(userDetails).isNotNull();

    }

    @Test(expected = UsernameNotFoundException.class)
    public void deveLoadUserByUsernameAndReturnNull(){
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        UserDetails userDetails = userDetailsService.loadUserByUsername("any_email@mail.com");
        verify(userRepository, times(1)).findByEmail(anyString());
    }
}
