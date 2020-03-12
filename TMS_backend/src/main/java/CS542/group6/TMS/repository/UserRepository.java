package CS542.group6.TMS.repository;

import CS542.group6.TMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {


}
