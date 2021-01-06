package br.com.ilia.moviedb.demo.resource;

import br.com.ilia.moviedb.demo.domain.User;
import br.com.ilia.moviedb.demo.security.JwtUtil;
import br.com.ilia.moviedb.demo.service.MovieService;
import br.com.ilia.moviedb.demo.service.RatingService;
import br.com.ilia.moviedb.demo.service.UserService;
import br.com.ilia.moviedb.demo.service.dto.RatingDTO;
import br.com.ilia.moviedb.demo.service.dto.UserNewDTO;
import br.com.ilia.moviedb.factory.domain.MovieFactory;
import br.com.ilia.moviedb.factory.dto.MovieDTOFactory;
import br.com.ilia.moviedb.factory.dto.UserNewDTOFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public class MovieResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @MockBean
    private MovieService movieService;

    @MockBean
    private RatingService ratingService;


    private JacksonTester<RatingDTO> dtoJacksonTester;

    @Before
    public void setup(){
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @After
    public void destroy(){
        userService.deleteUser();
    }


    private String makeAcessToken(){
        User user = userService.saveUser(UserNewDTOFactory.umUserNewDTO());
        return jwtUtil.generateToken(user.getEmail());
    }

    @Test
    public void deveBuscarTopRatedMovies() throws Exception {

        when(movieService.getMovieTopRated(any(Pageable.class))).thenReturn(new PageImpl<>(MovieDTOFactory.umaListaDeMoviesDTOs()));
        mockMvc.perform(get("/movie/top_rated?page=0&size=20"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(movieService, times(1)).getMovieTopRated(any(Pageable.class));
    }

    @Test
    public void deveBuscarDetailMovie() throws Exception {

        when(movieService.getMovieById(anyInt()))
                .thenReturn(MovieDTOFactory.umMovieDTO());

        mockMvc.perform(get("/movie/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(movieService, times(1)).getMovieById(anyInt());
    }

    @Test
    public void deveSalvarRatingMovie() throws Exception {

        RatingDTO ratingDTO = RatingDTO.builder().value(10d).build();
        doNothing().when(ratingService).saveRating(anyInt(), anyDouble());

        mockMvc.perform(post("/movie/1/rating")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + makeAcessToken())
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoJacksonTester.write(ratingDTO).getJson()))
                .andExpect(status().isCreated());
        verify(ratingService, times(1)).saveRating(anyInt(), anyDouble());
    }

    @Test
    public void deveDeletarRatingMovie() throws Exception {

        doNothing().when(ratingService).deleteRatingMovie(anyInt());

        mockMvc.perform(delete("/movie/1/rating")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + makeAcessToken())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(ratingService, times(1)).deleteRatingMovie(anyInt());
    }

}
