package br.com.ilia.moviedb.demo.service;

import br.com.ilia.moviedb.demo.domain.Movie;
import br.com.ilia.moviedb.demo.repository.MovieRepository;
import br.com.ilia.moviedb.demo.service.dto.MovieDTO;
import br.com.ilia.moviedb.demo.service.exception.MovieNotFoundException;
import br.com.ilia.moviedb.demo.service.mapper.MovieMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MovieService {

    private MovieRepository movieRepository;
    private MovieMapper movieMapper;


    public Page<MovieDTO> getMovieTopRated(Pageable pageable){
        Page<Movie> movies = movieRepository.getMovieTopRated(pageable);
        return movieMapper.pageEntidadeParaPageDto(movies);
    }

    public MovieDTO getMovieById(Integer id){
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("Nenhum filme encontrado com esse id"));
        return movieMapper.entidadeParaDTO(movie);
    }

    public Movie getMovieByIdModel(Integer id){
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("Nenhum filme encontrado com esse id"));
        return movie;
    }

}
