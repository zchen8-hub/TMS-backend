package CS542.group6.TMS.api;

import CS542.group6.TMS.dto.JsonResult;
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
    public JsonResult<UserDTO> login(@Valid @RequestBody UserDTO userDTO) {
        String uid = userServices.login(userDTO.convertToUser());
        if (uid != null){
            UserDTO json = new UserDTO();
            json.setUid(uid);
            return new JsonResult<>(json);
        } else {
            return new JsonResult("Invalid username or passcode");
        }
    }

    @PostMapping("/user/signup")
    public JsonResult<UserDTO> signUp(@Valid @RequestBody UserDTO userDTO) {
        String uid = userServices.signUp(userDTO.convertToUser());
        if (uid != null){
            UserDTO json = new UserDTO();
            json.setUid(uid);
            return new JsonResult<>(json);
        } else {
            return new JsonResult("Username or email already been used!");
        }
    }

    @PostMapping("/user/{uid}/invicode/{code}")
    public JsonResult joinProjectByInviCode(@PathVariable String uid, @PathVariable String code) {
        String msg = userServices.joinProject(uid, code);
        return new JsonResult(msg);
    }
}
