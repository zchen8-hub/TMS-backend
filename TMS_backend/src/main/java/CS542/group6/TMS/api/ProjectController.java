package CS542.group6.TMS.api;

import CS542.group6.TMS.dto.JsonResult;
import CS542.group6.TMS.dto.ProjectDTO;
import CS542.group6.TMS.model.Project;
import CS542.group6.TMS.model.User;
import CS542.group6.TMS.service.ProjectServices;
import CS542.group6.TMS.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
public class ProjectController {
    private ProjectServices projectServices;
    private UserServices userServices;

    @Autowired
    public ProjectController(ProjectServices projectServices, UserServices userServices) {
        this.projectServices = projectServices;
        this.userServices = userServices;
    }

    @GetMapping("/user/{uid}/projects")
    public JsonResult<List<ProjectDTO>> getProjects(@PathVariable String uid) {
        List<Project> projects = projectServices.getProjectsByUserId(uid);
        List<ProjectDTO> dtos = new ArrayList<>();
        for (Project project : projects) {
            User user = userServices.findUserById(project.getCreaterId());
            ProjectDTO dto = new ProjectDTO();
            dto = dto.convertFromProject(project);
            dto.setCreatorName(user.getUsername());
            dtos.add(dto);
        }
        return new JsonResult<>(dtos);
    }

    @GetMapping("/user/{uid}/project/{pid}")
    public JsonResult<ProjectDTO> getProject(@PathVariable String uid, @PathVariable String pid) {
        Project project = projectServices.getProjectById(pid);
        User user = userServices.findUserById(project.getCreaterId());
        ProjectDTO dto = new ProjectDTO();
        dto = dto.convertFromProject(project);
        dto.setCreatorName(user.getUsername());
        dto.setGroupDTOS(GroupController.assembleGroupDTO(project.getGroupList()));
        dto.setUserDTOs(UserController.assembleUserDTOs(project.getUserList()));
        return new JsonResult<>(dto);
    }

    @PostMapping("/user/{uid}/project")
    public JsonResult<ProjectDTO> createProject(@PathVariable String uid, @Valid @RequestBody ProjectDTO projectDTO) {
        projectDTO.setCreaterId(uid);
        User user = userServices.findUserById(uid);
        Project project = projectServices.createProject(projectDTO.convertToProject(), uid);
        ProjectDTO dto = assembleProjectDTO(project, user);
        return new JsonResult<>(dto);
    }

    @DeleteMapping("/user/{uid}/project/{pid}")
    public JsonResult deleteProject(@PathVariable String uid, @PathVariable String pid) {
        String msg = projectServices.deleteProject(uid, pid);
        if (msg == null){
            return new JsonResult("500", "Failed");
        }
        return new JsonResult(msg);
    }

    @PostMapping("user/{uid}/project/{pid}")
    public JsonResult<String> generateInvitationCode(@PathVariable String uid, @PathVariable String pid) {
        String code = projectServices.generateInvitationCode(uid, pid);
        return new JsonResult<>(code);
    }

    static ProjectDTO assembleProjectDTO(Project project, User user){
        ProjectDTO dto = new ProjectDTO();
        dto = dto.convertFromProject(project);
        dto.setCreatorName(user.getUsername());
        if (!project.getGroupList().isEmpty())
            dto.setGroupDTOS(GroupController.assembleGroupDTO(project.getGroupList()));
        if (!project.getUserList().isEmpty())
            dto.setUserDTOs(UserController.assembleUserDTOs(project.getUserList()));
        return dto;
    }
}
