package CS542.group6.TMS.api;

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
    public List<Project> getProjects(@PathVariable String uid){
        return projectServices.getProjectsByUserId(uid);
    }

    @PostMapping("/user/{uid}/project")
    public Project createProject(@PathVariable String uid, @Valid @RequestBody ProjectDTO projectDTO){
        projectDTO.setCreaterId(uid);
        return projectServices.createProject(projectDTO.convertToProject(), uid);
    }

    @DeleteMapping("/user/{uid}/project/{pid}")
    public String deleteProject(@PathVariable String uid, @PathVariable String pid){
        if (projectServices.deleteProject(uid, pid))
            return "success";
        return "failed";
    }

    @PostMapping("user/{uid}/project/{pid}")
    public String generateInvitationCode(@PathVariable String uid, @PathVariable String pid){
        return projectServices.generateInvitationCode(uid, pid);
    }
}
