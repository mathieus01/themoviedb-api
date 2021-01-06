package br.com.ilia.moviedb.demo.resource;

import br.com.ilia.moviedb.demo.service.MovieService;
import br.com.ilia.moviedb.demo.service.RatingService;
import br.com.ilia.moviedb.demo.service.dto.MovieDTO;
import br.com.ilia.moviedb.demo.service.dto.RatingDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@AllArgsConstructor
@RestController
@Api(value = "Movie")
public class MovieResource {

    private MovieService movieService;
    private RatingService ratingService;

    @ApiOperation(value = "Retorna lista dos mais bem avaliados")
    @GetMapping("/movie/top_rated")
    public ResponseEntity<Page<MovieDTO>> getMovieTopRated(@RequestParam Integer page, @RequestParam Integer size){
        log.debug("getMovieTopRated");
        Page<MovieDTO> movies = movieService.getMovieTopRated(PageRequest.of(page, size));
        return ResponseEntity.ok(movies);
    }

    @ApiOperation(value = "Retorna detalhes de um filme especifico")
    @GetMapping("/movie/{movie_id}")
    public ResponseEntity<MovieDTO> getDetailMovie(@PathVariable("movie_id") Integer id){
        log.debug("getDetailMovie");
        MovieDTO movieDTO = movieService.getMovieById(id);
        return ResponseEntity.ok(movieDTO);
    }

    @ApiOperation(value = "Grava uma avaliacao")
    @PostMapping("/movie/{movie_id}/rating")
    public ResponseEntity<Void> saveRatingMovie(@PathVariable("movie_id") Integer id, @RequestBody RatingDTO ratingDTO){
        log.debug("saveRatingMovie");
        ratingService.saveRating(id, ratingDTO.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "Deleta uma avaliacao")
    @DeleteMapping("/movie/{movie_id}/rating")
    public ResponseEntity<Void> deleteRatingMovie(@PathVariable("movie_id") Integer id){
        log.debug("deleteRatingMovie");
        ratingService.deleteRatingMovie(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
