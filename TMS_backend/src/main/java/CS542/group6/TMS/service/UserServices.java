package CS542.group6.TMS.service;

import CS542.group6.TMS.model.User;
import CS542.group6.TMS.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
