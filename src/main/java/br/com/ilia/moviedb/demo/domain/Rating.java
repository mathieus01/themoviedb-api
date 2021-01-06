package br.com.ilia.moviedb.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RATINGS")
public class Rating {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private Double value;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
