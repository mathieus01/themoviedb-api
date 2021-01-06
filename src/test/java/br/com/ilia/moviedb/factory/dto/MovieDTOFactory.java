package br.com.ilia.moviedb.factory.dto;


import br.com.ilia.moviedb.demo.service.dto.MovieDTO;

import java.util.Arrays;
import java.util.List;

public class MovieDTOFactory {
    public static MovieDTO umMovieDTO(){
        return MovieDTO.builder()
                .adult(false)
                .backdrop_path("any_backdrop_path")
                .id(1)
                .original_language("any_original_language")
                .original_title("any_original_title")
                .overview("any_overview")
                .popularity(10d)
                .build();
    }

    public static List<MovieDTO> umaListaDeMoviesDTOs(){
        return Arrays.asList(umMovieDTO());
    }
}
