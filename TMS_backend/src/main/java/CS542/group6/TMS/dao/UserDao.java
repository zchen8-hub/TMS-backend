package CS542.group6.TMS.dao;

import CS542.group6.TMS.model.User;

import java.util.UUID;

public interface UserDao {

    Boolean insertUser(UUID uid, User user);

    default Boolean insertUser(User user){
        UUID uid = UUID.randomUUID();
        return insertUser(uid, user);
    }
}
