package br.com.ilia.moviedb.demo.repository;

import br.com.ilia.moviedb.demo.domain.Movie;
import br.com.ilia.moviedb.demo.domain.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    @Query(value = "select r from Rating r where r.user.id = :userId and r.movie.id = :movieId")
    List<Rating> getRatingByUserIdAndMovieId(@Param("userId") Integer userId, @Param("movieId") Integer movieId);

}
