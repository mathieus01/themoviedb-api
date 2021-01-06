package br.com.ilia.moviedb.demo.repository;

import br.com.ilia.moviedb.demo.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("select m from Movie m ORDER BY m.popularity desc")
    Page<Movie> getMovieTopRated(Pageable pageable);
}
