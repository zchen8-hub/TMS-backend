package CS542.group6.TMS.service;

import CS542.group6.TMS.dto.ProjectDTO;
import CS542.group6.TMS.model.Project;
import CS542.group6.TMS.model.User;
import CS542.group6.TMS.repository.ProjectRepository;
import CS542.group6.TMS.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServices {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectServices(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public List<Project> getProjectIdsByUserId(String uid){
        return userRepository.findById(uid).get().getProjectList();
    }

    public Project createProject(Project project){
        return projectRepository.save(project);
    }
}
