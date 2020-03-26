package CS542.group6.TMS.api;

import CS542.group6.TMS.model.User;
import CS542.group6.TMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/")
@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("users/{userId}")
    public Optional<User> getUser(@PathVariable String userId){
        return userRepository.findById(userId);
    }
    @PostMapping("users/add")
    public void addUser(@Valid @RequestBody User user){
        userRepository.save(user);
    }
}
