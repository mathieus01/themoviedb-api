package br.com.ilia.moviedb.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    @Column
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;
}
