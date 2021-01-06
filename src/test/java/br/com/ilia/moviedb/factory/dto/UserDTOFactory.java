package br.com.ilia.moviedb.factory.dto;

import br.com.ilia.moviedb.demo.service.dto.UserDTO;

public class UserDTOFactory {

    public static UserDTO umUsuarioDTO(){
        return UserDTO.builder()
                .id(1)
                .name("any_name")
                .email("any_email@mail.com")
                .build();
    }
}
