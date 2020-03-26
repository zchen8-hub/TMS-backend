package CS542.group6.TMS.repository;

import CS542.group6.TMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("select u from User u where u.username = ?1")
    User findByUsername(String username);

    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);
}
