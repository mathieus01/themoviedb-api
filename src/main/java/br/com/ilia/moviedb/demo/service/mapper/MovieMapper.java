package br.com.ilia.moviedb.demo.service.mapper;

import br.com.ilia.moviedb.demo.domain.Movie;
import br.com.ilia.moviedb.demo.domain.Rating;
import br.com.ilia.moviedb.demo.repository.RatingRepository;
import br.com.ilia.moviedb.demo.service.RatingService;
import br.com.ilia.moviedb.demo.service.dto.MovieDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class MovieMapper {

    public MovieDTO entidadeParaDTO(Movie entidade){
        return MovieDTO.builder()
                .adult(entidade.getAdult())
                .backdrop_path(entidade.getBackdrop_path())
                .id(entidade.getId())
                .original_language(entidade.getOriginal_language())
                .original_title(entidade.getOriginal_title())
                .overview(entidade.getOverview())
                .popularity(entidade.getPopularity())
                .poster_path(entidade.getPoster_path())
                .release_date(entidade.getBackdrop_path())
                .title(entidade.getTitle())
                .video(entidade.getVideo())
                .vote_count(entidade.getRatings().size())
                .vote_average(getVoteAvegare(entidade.getRatings()))
                .build();
    }

    public List<MovieDTO> entidadesParaDTO(List<Movie> entidades){
        return entidades.stream().map(this::entidadeParaDTO).collect(Collectors.toList());
    }

    public Page<MovieDTO> pageEntidadeParaPageDto(Page<Movie> entidade) {
        return entidade.map(this::entidadeParaDTO);
    }

    private Double getVoteAvegare(List<Rating> ratings){
        Double sum = 0d;
        for (Rating rating: ratings){
            sum += rating.getValue();
        }
        return Objects.nonNull(ratings) && ratings.size() > 0 ? sum / ratings.size() : 0;
    }
}
