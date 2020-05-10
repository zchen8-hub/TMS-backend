package CS542.group6.TMS.service;

import CS542.group6.TMS.model.Group;
import CS542.group6.TMS.model.InviCode;
import CS542.group6.TMS.model.Project;
import CS542.group6.TMS.model.User;
import CS542.group6.TMS.repository.InvitationCodeRepository;
import CS542.group6.TMS.repository.ProjectRepository;
import CS542.group6.TMS.repository.UserRepository;
import CS542.group6.TMS.util.Util;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServices {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final InvitationCodeRepository invitationCodeRepository;
    private final GroupServices groupServices;

    public ProjectServices(ProjectRepository projectRepository, UserRepository userRepository, InvitationCodeRepository invitationCodeRepository, GroupServices groupServices) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.invitationCodeRepository = invitationCodeRepository;
        this.groupServices = groupServices;
    }

    public List<Project> getProjectsByUserId(String uid){
        return userRepository.getOne(uid).getProjectList();
    }

    public Project getProjectById(String pid) {
        Optional<Project> project = projectRepository.findById(pid);
        return project.orElse(null);
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

        Group todo = new Group();
        todo.setGroupName("TODO");
        todo.setProjectId(p.getProjectId());
        groupServices.createGroup(todo);

        Group inProgress = new Group();
        inProgress.setGroupName("IN PROGRESS");
        inProgress.setProjectId(p.getProjectId());
        groupServices.createGroup(inProgress);

        Group done = new Group();
        done.setGroupName("DONE");
        done.setProjectId(p.getProjectId());
        groupServices.createGroup(done);
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
