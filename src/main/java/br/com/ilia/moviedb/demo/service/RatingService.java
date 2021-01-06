package br.com.ilia.moviedb.demo.service;

import br.com.ilia.moviedb.demo.domain.Movie;
import br.com.ilia.moviedb.demo.domain.Rating;
import br.com.ilia.moviedb.demo.repository.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class RatingService {

    private RatingRepository ratingRepository;
    private MovieService movieService;

    @Transactional
    public void saveRating(Integer movieId, Double value){
        Movie movie = movieService.getMovieByIdModel(movieId);
        if(Objects.nonNull(movie)){
            Rating rating = Rating.builder().movie(movie).value(value).build();
            ratingRepository.save(rating);
        }
    }


    @Transactional
    public void deleteRatingMovie(Integer movieId){
        Movie movie = movieService.getMovieByIdModel(movieId);
        if(Objects.nonNull(movie)){
            List<Rating> rating = ratingRepository.getRatingByUserIdAndMovieId(1, movieId);
            if(Objects.nonNull(rating) && !rating.isEmpty()){
                ratingRepository.delete(rating.get(0));
            }
        }
    }

}
