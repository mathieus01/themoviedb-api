package br.com.ilia.moviedb.demo.service;

import br.com.ilia.moviedb.demo.domain.Movie;
import br.com.ilia.moviedb.demo.repository.MovieRepository;
import br.com.ilia.moviedb.demo.service.dto.MovieDTO;
import br.com.ilia.moviedb.demo.service.exception.InvalidParamException;
import br.com.ilia.moviedb.demo.service.exception.MovieNotFoundException;
import br.com.ilia.moviedb.demo.service.mapper.MovieMapper;
import br.com.ilia.moviedb.factory.domain.MovieFactory;
import br.com.ilia.moviedb.factory.dto.MovieDTOFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class MovieServiceTest {

    private MovieService movieService;

    @MockBean
    private MovieRepository movieRepository;

    @MockBean
    private MovieMapper movieMapper;

    @Before
    public void setup() {
        movieService = new MovieService(movieRepository, movieMapper);
    }


    @Test
    public void deveBuscarTopRatedMovies(){
        when(movieRepository.getMovieTopRated(any(Pageable.class))).thenReturn(new PageImpl<>(MovieFactory.umaListaDeMovies()));
        when(movieMapper.pageEntidadeParaPageDto(any())).thenReturn(new PageImpl<>(MovieDTOFactory.umaListaDeMoviesDTOs()));
        Page<MovieDTO> movies = movieService.getMovieTopRated(PageRequest.of(0, 10));
        assertThat(movies).isNotNull();
        assertThat(movies.getTotalElements()).isEqualTo(1);
        verify(movieRepository, times(1)).getMovieTopRated(any(Pageable.class));
    }

    @Test
    public void deveBuscarMovieById(){
        when(movieRepository.findById(anyInt())).thenReturn(Optional.of(MovieFactory.umMovie()));
        when(movieMapper.entidadeParaDTO(any(Movie.class))).thenReturn(MovieDTOFactory.umMovieDTO());
        MovieDTO movieDTO = movieService.getMovieById(1);
        assertThat(movieDTO).isNotNull();
        assertThat(movieDTO.getId()).isEqualTo(MovieFactory.umMovie().getId());
        verify(movieRepository, times(1)).findById(anyInt());
    }

    @Test(expected = MovieNotFoundException.class)
    public void deveBuscarMovieByIdButMovieNotFound(){
        MovieDTO movieDTO = movieService.getMovieById(1);
        assertThat(movieDTO).isNotNull();
        assertThat(movieDTO.getId()).isEqualTo(MovieFactory.umMovie().getId());
        verify(movieRepository, times(1)).findById(anyInt());
    }

    @Test
    public void deveBuscarMovieByIdModel(){
        when(movieRepository.findById(anyInt())).thenReturn(Optional.of(MovieFactory.umMovie()));
        Movie movie = movieService.getMovieByIdModel(1);
        assertThat(movie).isNotNull();
        assertThat(movie.getId()).isEqualTo(MovieFactory.umMovie().getId());
        verify(movieRepository, times(1)).findById(anyInt());
    }

    @Test(expected = MovieNotFoundException.class)
    public void deveBuscarMovieByIdModelButMovieNotFound(){
        Movie movie = movieService.getMovieByIdModel(1);
        assertThat(movie).isNotNull();
        assertThat(movie.getId()).isEqualTo(MovieFactory.umMovie().getId());
        verify(movieRepository, times(1)).findById(anyInt());
    }
}
