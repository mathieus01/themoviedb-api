package br.com.ilia.moviedb.demo.service.mapper;


import br.com.ilia.moviedb.demo.domain.User;
import br.com.ilia.moviedb.demo.service.dto.UserDTO;
import br.com.ilia.moviedb.demo.service.dto.UserNewDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class UserMapper {

    public User dtoParaEntidade(UserNewDTO userNewDTO){
        return User.builder()
                .id(userNewDTO.getId())
                .email(userNewDTO.getEmail())
                .name(userNewDTO.getName())
                .password(userNewDTO.getPassword())
                .build();
    }

    public UserDTO entidadeParaDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
