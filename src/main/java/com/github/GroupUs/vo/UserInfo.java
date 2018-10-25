package com.github.GroupUs.vo;

import java.io.Serializable;
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
    private List<Integer> joined;
    private List<Integer> posted;
    private String name;
    public UserInfo() {}
    public UserInfo(String email, String password, List<Integer> joined, List<Integer> posted, String name) {
        this.email = email;
        this.password = password;
        this.joined = joined;
        this.posted = posted;
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setJoined(List<Integer> joined) {
        this.joined = joined;
    }

    public void setPosted(List<Integer> posted) {
        this.posted = posted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() { return password; }

    public List<Integer> getJoined() {
        return joined;
    }

    public List<Integer> getPosted() {
        return posted;
    }

    public String getName() {
        return name;
    }
}

