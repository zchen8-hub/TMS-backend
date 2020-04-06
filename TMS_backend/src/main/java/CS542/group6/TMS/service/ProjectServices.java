package CS542.group6.TMS.service;

import CS542.group6.TMS.model.InviCode;
import CS542.group6.TMS.model.Project;
import CS542.group6.TMS.model.User;
import CS542.group6.TMS.repository.InvitationCodeRepository;
import CS542.group6.TMS.repository.ProjectRepository;
import CS542.group6.TMS.repository.UserRepository;
import CS542.group6.TMS.util.Util;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServices {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final InvitationCodeRepository invitationCodeRepository;

    public ProjectServices(ProjectRepository projectRepository, UserRepository userRepository, InvitationCodeRepository invitationCodeRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.invitationCodeRepository = invitationCodeRepository;
    }

    public List<Project> getProjectsByUserId(String uid){
        return userRepository.getOne(uid).getProjectList();
    }

    public Project listProjects(String uid, String pid){
        List<Project> projectList = userRepository.getOne(uid).getProjectList();
        for (Project p : projectList) {
            if (p.getProjectId().equals(pid))
                return p;
        }
        return null;
    }

    public Project createProject(Project project, String uid){
        Project p = projectRepository.save(project);
        User user = userRepository.getOne(uid);
        user.getProjectList().add(p);
        userRepository.save(user);
        return p;
    }

    public String deleteProject(String uid, String pid){
        Project project = projectRepository.getOne(pid);
        if (!project.getCreaterId().equals(uid))
            return "Permission denied";
        projectRepository.deleteById(pid);
        return "Success";
    }

    public String generateInvitationCode(String uid, String pid){
        InviCode inviCode = new InviCode();
        inviCode.setInviterId(uid);
        inviCode.setProjectId(pid);
        inviCode.setCodeString(Util.GenerateRandomString(10));

        return invitationCodeRepository.save(inviCode).getCodeString();
    }
}
