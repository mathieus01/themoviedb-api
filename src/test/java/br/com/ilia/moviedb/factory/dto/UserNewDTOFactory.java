package br.com.ilia.moviedb.factory.dto;

import br.com.ilia.moviedb.demo.service.dto.UserNewDTO;

public class UserNewDTOFactory {

    public static UserNewDTO umUserNewDTO(){
        return UserNewDTO.builder()
                .password("any_password")
                .passwordConfirmation("any_password")
                .email("any_email@mail.com")
                .name("any_name").build();
    }
}
