package br.com.ilia.moviedb.demo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserNewDTO {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private String passwordConfirmation;
}
