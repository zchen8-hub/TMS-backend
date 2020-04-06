package CS542.group6.TMS.api;

import CS542.group6.TMS.dto.JsonResult;
import CS542.group6.TMS.dto.ProjectDTO;
import CS542.group6.TMS.model.Project;
import CS542.group6.TMS.service.ProjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api")
@RestController
public class ProjectController {
    private ProjectServices projectServices;

    @Autowired
    public ProjectController(ProjectServices projectServices) {
        this.projectServices = projectServices;
    }

    @GetMapping("/user/{uid}/projects")
    public JsonResult<List<Project>> getProjects(@PathVariable String uid){
        List<Project> projects = projectServices.getProjectsByUserId(uid);
        return new JsonResult<>(projects);
    }

    @PostMapping("/user/{uid}/project")
    public JsonResult<Project> createProject(@PathVariable String uid, @Valid @RequestBody ProjectDTO projectDTO){
        projectDTO.setCreaterId(uid);
        Project project = projectServices.createProject(projectDTO.convertToProject(), uid);
        return new JsonResult<>(project);
    }

    @DeleteMapping("/user/{uid}/project/{pid}")
    public JsonResult deleteProject(@PathVariable String uid, @PathVariable String pid){
        String msg = projectServices.deleteProject(uid, pid);
        return new JsonResult(msg);
    }

    @PostMapping("user/{uid}/project/{pid}")
    public JsonResult<String> generateInvitationCode(@PathVariable String uid, @PathVariable String pid){
        String code = projectServices.generateInvitationCode(uid, pid);
        return new JsonResult<>(code);
    }
}
