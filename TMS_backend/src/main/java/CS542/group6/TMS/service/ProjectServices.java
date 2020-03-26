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

    public List<Project> getProjectsByUserId(String uid){
        return userRepository.findById(uid).get().getProjectList();
    }

    public Project getProjectById(String uid, String pid){
        List<Project> projectList = userRepository.findById(uid).get().getProjectList();
        for (Project p :
                projectList) {
            if (p.getProjectId().equals(pid))
                return p;
        }
        return null;
    }

    public Project createProject(Project project, String uid){
        Project p = projectRepository.save(project);
        User user = userRepository.findById(uid).get();
        user.getProjectList().add(p);
        userRepository.save(user);
        return p;
    }

    public boolean deleteProject(String userId, String projectId){
        Project project = projectRepository.findById(projectId).get();
        if (project == null || !project.getCreaterId().equals(userId))
            return false;
        projectRepository.deleteById(projectId);
        return true;
    }
}
