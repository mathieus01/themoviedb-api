package br.com.ilia.moviedb.demo.resource;

import br.com.ilia.moviedb.demo.service.UserService;
import br.com.ilia.moviedb.demo.service.dto.UserNewDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@Api(value = "SignUP")
public class SignUpResource {

    private UserService userService;

    @ApiOperation(value = "Realiza o cadastro de um usuario")
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody UserNewDTO userNewDTO){
        log.debug("signup");
        userService.saveUser(userNewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
