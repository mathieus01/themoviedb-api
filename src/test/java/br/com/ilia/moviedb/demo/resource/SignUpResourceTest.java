package br.com.ilia.moviedb.demo.resource;

import br.com.ilia.moviedb.demo.service.UserService;
import br.com.ilia.moviedb.demo.service.dto.RatingDTO;
import br.com.ilia.moviedb.demo.service.dto.UserNewDTO;
import br.com.ilia.moviedb.factory.dto.UserNewDTOFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public class SignUpResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private JacksonTester<UserNewDTO> dtoJacksonTester;

    @Before
    public void setup(){
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void deveFazerSignIn() throws Exception {

        UserNewDTO userNewDTO = UserNewDTOFactory.umUserNewDTO();
        when(userService.saveUser(any(UserNewDTO.class))).thenReturn(null);

        mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoJacksonTester.write(userNewDTO).getJson()))
                .andExpect(status().isCreated());
        verify(userService, times(1)).saveUser(any(UserNewDTO.class));
    }
}
