package CS542.group6.TMS.api;

import CS542.group6.TMS.dto.UserDTO;
import CS542.group6.TMS.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api")
@RestController
public class UserController {

    private UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/user/login")
    public String login(@Valid @RequestBody UserDTO userDTO){
        String uid = userServices.login(userDTO.convertToUser());
        return uid != null ? uid : "Wrong username or password";
    }

    @PostMapping("/user/signup")
    public String signUp(@Valid @RequestBody UserDTO userDTO){
        String uid = userServices.signUp(userDTO.convertToUser());
        return uid != null ? uid : "Username or email already been used!";
    }

    @PostMapping("/user/{uid}/invicode/{code}")
    public String joinProjectByInviCode(@PathVariable String uid, @PathVariable String code){
        if (userServices.joinProject(uid, code) != null)
            return "success";
        return "failed";
    }
}
