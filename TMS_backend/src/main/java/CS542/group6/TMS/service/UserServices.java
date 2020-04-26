package CS542.group6.TMS.service;

import CS542.group6.TMS.model.InviCode;
import CS542.group6.TMS.model.Project;
import CS542.group6.TMS.model.User;
import CS542.group6.TMS.repository.InvitationCodeRepository;
import CS542.group6.TMS.repository.ProjectRepository;
import CS542.group6.TMS.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    private final UserRepository userRepository;
    private final InvitationCodeRepository invitationCodeRepository;
    private final ProjectRepository projectRepository;

    public UserServices(UserRepository userRepository, InvitationCodeRepository invitationCodeRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.invitationCodeRepository = invitationCodeRepository;
        this.projectRepository = projectRepository;
    }

    public String login(User user0) {
        User user = userRepository.findByUsername(user0.getUsername());
        if (user == null)
            return "invalid username or password";
        String password = user.getPassword();
        if (user0.getPassword().equals(password))
            return user.getUid();
        return "invalid username or password";
    }

    public String signUp(User user) {
        if (userRepository.findByUsername(user.getUsername()) == null
                && userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);
            return user.getUid();
        }
        return "failed";
    }

    public Project joinProject(String uid, String code){
        InviCode inviCode = invitationCodeRepository.findByCodeString(code);
        if (inviCode != null){
            User user = userRepository.getOne(uid);
            Project project = projectRepository.getOne(inviCode.getProjectId());
            if (project.getUserList().contains(user) || uid.equals(inviCode.getInviterId()))
                return project;
            user.getProjectList().add(project);
            userRepository.save(user);
            return project;   //Success
        }
        return null;   //Failed
    }
}
