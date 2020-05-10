package CS542.group6.TMS.api;

import CS542.group6.TMS.dto.JsonResult;
import CS542.group6.TMS.dto.ProjectDTO;
import CS542.group6.TMS.dto.UserDTO;
import CS542.group6.TMS.model.Project;
import CS542.group6.TMS.model.User;
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
        User user = userServices.login(userDTO.convertToUser());
        if (user != null){
            UserDTO json = new UserDTO();
            json = json.convertFromUser(user);
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
    public JsonResult<ProjectDTO> joinProjectByInvitationCode(@PathVariable String uid, @PathVariable String code) {
        Project result = userServices.joinProject(uid, code);
        if (result == null)
            return new JsonResult("Invitation code not existed");
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId(result.getProjectId());
        projectDTO.setProjectName(result.getProjectName());
        projectDTO.setCreaterId(result.getCreaterId());


        return new JsonResult<>(projectDTO);
    }
}
