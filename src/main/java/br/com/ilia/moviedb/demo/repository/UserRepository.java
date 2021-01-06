package br.com.ilia.moviedb.demo.repository;

import br.com.ilia.moviedb.demo.domain.Rating;
import br.com.ilia.moviedb.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select u from User u where u.email = :email")
    User findByEmail(@Param("email") String email);
}
