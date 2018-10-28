package com.github.GroupUs.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//User
//        {
//        “_id”: ObjectID,
//        “email”: “XX@columbia.edu”,
//        “password”: “XX”,
//        “joined”: [1, 2],
//        “posted”: [3, 4],
//        “name”: “XX”
//        }

@SuppressWarnings("serial")
public class UserInfo implements Serializable {
    private String email;
    private String password;
    private List<String> joined = new ArrayList<>();
    private List<String> posted = new ArrayList<>();
    private String name;
    public UserInfo() {}
//    public UserInfo(String email, String password, List<String> joined, List<String> posted, String name) {
//        this.email = email;
//        this.password = password;
//        this.joined = joined;
//        this.posted = posted;
//        this.name = name;
//    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setJoined(List<String> joined) {
        this.joined = joined;
    }

    public void setPosted(List<String> posted) {
        this.posted = posted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() { return password; }

    public List<String> getJoined() {
        return joined;
    }

    public List<String> getPosted() {
        return posted;
    }

    public String getName() {
        return name;
    }
}

