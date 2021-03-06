package CS542.group6.TMS.service;

import CS542.group6.TMS.model.InviCode;
import CS542.group6.TMS.model.Project;
import CS542.group6.TMS.model.User;
import CS542.group6.TMS.repository.InvitationCodeRepository;
import CS542.group6.TMS.repository.ProjectRepository;
import CS542.group6.TMS.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public User login(User user0) {
        User user = userRepository.findByUsername(user0.getUsername());
        if (user == null)
            return null;
        String password = user.getPassword();
        if (user0.getPassword().equals(password))
            return user;
        return null;
    }

    public String signUp(User user) {
        if (userRepository.findByUsername(user.getUsername()) == null
                && userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);
            return user.getUid();
        }
        return "failed";
    }

    public Project joinProject(String uid, String code) {
        InviCode inviCode = invitationCodeRepository.findByCodeString(code);
        if (inviCode != null) {
            User user = userRepository.getOne(uid);
            Project project = projectRepository.findById(inviCode.getProjectId()).get();
            if (project.getUserList().contains(user) || uid.equals(inviCode.getInviterId()))
                return project;
            project.getUserList().add(user);
            user.getProjectList().add(project);
            userRepository.save(user);
            return project;   //Success
        }
        return null;   //Failed
    }

    public User findUserById(String uid) {
        Optional<User> user = userRepository.findById(uid);
        return user.orElse(null);
    }

    public String findUserNameById(String uid) {
        Optional<User> user = userRepository.findById(uid);
        return user.map(User::getUsername).orElse(null);
    }
}
