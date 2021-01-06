package br.com.ilia.moviedb.factory.domain;

import br.com.ilia.moviedb.demo.domain.User;

public class UserFactory {

    public static User umUsuario(){
        return User.builder()
                .id(1)
                .name("any_name")
                .email("any_email@mail.com")
                .password("any_password")
                .build();
    }
}
