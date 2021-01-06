package br.com.ilia.moviedb.demo.service;

import br.com.ilia.moviedb.demo.domain.User;
import br.com.ilia.moviedb.demo.repository.UserRepository;
import br.com.ilia.moviedb.demo.service.dto.UserNewDTO;
import br.com.ilia.moviedb.demo.service.exception.EmailAlreadyInUseException;
import br.com.ilia.moviedb.demo.service.exception.InvalidParamException;
import br.com.ilia.moviedb.demo.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User getUserByEmail(String email){
        User user = userRepository.findByEmail(email);
        return user;
    }

    public User saveUser(UserNewDTO userNewDTO){
        User user = getUserByEmail(userNewDTO.getEmail());
        if(Objects.isNull(user)){
            if(userNewDTO.getPassword().equals(userNewDTO.getPasswordConfirmation())){
                User newUser = userMapper.dtoParaEntidade(userNewDTO);
                newUser.setPassword(bCryptPasswordEncoder.encode(userNewDTO.getPassword()));
                newUser = userRepository.save(newUser);
                return newUser;
            }else{
                throw new InvalidParamException("A confirmacao da senha precisa ser igual a senha");
            }
        }else{
            throw new EmailAlreadyInUseException("O email recebido ja se encontra em uso");
        }
    }

    public void deleteUser(){
        userRepository.deleteAll();
    }
}
