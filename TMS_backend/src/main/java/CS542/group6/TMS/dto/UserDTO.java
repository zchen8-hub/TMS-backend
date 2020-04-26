package CS542.group6.TMS.dto;

import CS542.group6.TMS.model.User;
import com.google.common.base.Converter;
import org.springframework.beans.BeanUtils;

/**
 * DTO: Data Transfer Object,
 * In this project, it is used for data transmission between front-end and back-end.
 */
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String uid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Convert DTO to PO (Persistence object, completely match parameters of user table )
     *
     * @return PO
     */
    public User convertToUser() {
        UserDTOConvert userDTOConvert = new UserDTOConvert();
        return userDTOConvert.convert(this);
    }

    public UserDTO convertFromUser(User user) {
        UserDTOConvert userDTOConvert = new UserDTOConvert();
        return userDTOConvert.doBackward(user);
    }

    private static class UserDTOConvert extends Converter<UserDTO, User> {

        @Override
        protected User doForward(UserDTO userDTO) {
            User user = new User();
            BeanUtils.copyProperties(userDTO, user);
            return user;
        }

        @Override
        protected UserDTO doBackward(User user) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            return userDTO;
        }
    }
}
