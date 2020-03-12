package CS542.group6.TMS.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class User {

    private final UUID uid;
    private String username;
    private String email;
    private String phone;
    private String password;

    public User(@JsonProperty("uid") UUID uid,
                @JsonProperty("username") String username,
                @JsonProperty("email") String email,
                @JsonProperty("phone") String phone,
                @JsonProperty("password") String password) {
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
