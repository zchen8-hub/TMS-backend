package CS542.group6.TMS.service;

import CS542.group6.TMS.dao.UserDao;
import CS542.group6.TMS.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("userDB") UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean addUser(User user){
        return userDao.insertUser(user);
    }
}
