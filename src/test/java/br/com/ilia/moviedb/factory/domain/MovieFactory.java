package br.com.ilia.moviedb.factory.domain;

import br.com.ilia.moviedb.demo.domain.Movie;
import br.com.ilia.moviedb.demo.repository.MovieRepository;

import java.util.Arrays;
import java.util.List;

public class MovieFactory {

    public static Movie umMovie(){
        return Movie.builder()
                .adult(false)
                .backdrop_path("any_backdrop_path")
                .id(1)
                .original_language("any_original_language")
                .original_title("any_original_title")
                .overview("any_overview")
                .popularity(10d)
                .build();
    }

    public static List<Movie> umaListaDeMovies(){
        return Arrays.asList(umMovie());
    }
}
