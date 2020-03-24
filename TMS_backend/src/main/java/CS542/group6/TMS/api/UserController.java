package CS542.group6.TMS.api;

import CS542.group6.TMS.dto.UserDTO;
import CS542.group6.TMS.model.User;
import CS542.group6.TMS.repository.UserRepository;
import CS542.group6.TMS.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/api")
@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/users/add")
    public String addUser(@Valid @RequestBody UserDTO userDTO){
        User user = userDTO.convertToUser();
        userRepository.save(user);
        return user.getUid();
    }
}
