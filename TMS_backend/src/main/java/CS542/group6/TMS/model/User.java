package CS542.group6.TMS.model;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="user")
public class User {

    @Id
    @Column(name = "user_id")
    private final UUID uid;
    @Column(name = "user_name",nullable = false)
    private String username;
    private String email;
    private String phone;
    @Column(name = "pwd",nullable = false)
    private String password;

    public User( UUID uid, String username, String email, String phone, String password) {
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public UUID getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }
}
