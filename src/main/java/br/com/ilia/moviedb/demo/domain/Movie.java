package br.com.ilia.moviedb.demo.domain;

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
@Table(name = "MOVIES")
public class Movie {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private String poster_path;

    @Column
    private Boolean adult;

    @Column
    private String overview;

    @Column
    private String release_date;

    @Column
    private String original_title;

    @Column
    private String original_language;

    @Column
    private String title;

    @Column
    private String backdrop_path;

    @Column
    private Double popularity;

    @Column
    private Integer vote_count;

    @Column
    private Boolean video;

    @Column
    private Double vote_average;

    @OneToMany(mappedBy = "movie")
    private List<Rating> ratings;





}
