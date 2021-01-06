package br.com.ilia.moviedb.demo.service;

import br.com.ilia.moviedb.demo.domain.Rating;
import br.com.ilia.moviedb.demo.repository.RatingRepository;
import br.com.ilia.moviedb.factory.domain.MovieFactory;
import br.com.ilia.moviedb.factory.domain.RatingFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RatingServiceTest {

    private RatingService ratingService;

    @MockBean
    private RatingRepository ratingRepository;

    @MockBean
    private MovieService movieService;

    @Before
    public void setup() {
        ratingService = new RatingService(ratingRepository, movieService);
    }

    @Test
    public void deveSalvarRating(){
        when(movieService.getMovieByIdModel(anyInt())).thenReturn(MovieFactory.umMovie());
        when(ratingRepository.save(any(Rating.class))).thenReturn(null);
        ratingService.saveRating(1, 10d);
        verify(movieService, times(1)).getMovieByIdModel(anyInt());
        verify(ratingRepository, times(1)).save(any(Rating.class));
    }

    @Test
    public void deveDeletarRatingMovie(){
        when(movieService.getMovieByIdModel(anyInt())).thenReturn(MovieFactory.umMovie());
        when(ratingRepository.getRatingByUserIdAndMovieId(anyInt(), anyInt()))
                .thenReturn(RatingFactory.umaListaDeRatings());
        doNothing().when(ratingRepository).delete(any(Rating.class));

        ratingService.deleteRatingMovie(1);

        verify(movieService, times(1)).getMovieByIdModel(anyInt());
        verify(ratingRepository, times(1))
                .getRatingByUserIdAndMovieId(anyInt(), anyInt());
        verify(ratingRepository, times(1)).delete(any(Rating.class));
    }

}
