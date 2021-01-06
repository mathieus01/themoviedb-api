package br.com.ilia.moviedb.factory.domain;

import br.com.ilia.moviedb.demo.domain.Movie;
import br.com.ilia.moviedb.demo.domain.Rating;

import java.util.Arrays;
import java.util.List;

public class RatingFactory {

    public static Rating umRating(){
        return Rating.builder()
                .id(1)
                .value(10d)
                .movie(MovieFactory.umMovie())
                .build();
    }

    public static List<Rating> umaListaDeRatings(){
        return Arrays.asList(umRating());
    }
}
