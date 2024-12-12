package com.sontung.blooddonation.model;

import com.sontung.blooddonation.shared.Types;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String userId;
    private String username;
    private String email;
    private String password;
    private String profileUrl = null;
    private String userRole = Types.USER_PERMISSION;
    private String bloodType;
    private ArrayList<String> registeredEventsList = new ArrayList<>();
    private ArrayList<String> volunteerEventsList = new ArrayList<>();
    private ArrayList<String> hostedSiteList = new ArrayList<>();
    private ArrayList<String> eventRequestsList = new ArrayList<>();

    public User() {
    }

    public User(String userId, String username, String email, String password, String bloodType) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.bloodType = bloodType;
    }

    public User(
            String userId,
            String username,
            String email,
            String password,
            String profileUrl,
            String userRole,
            String bloodType,
            ArrayList<String> registeredEventsList,
            ArrayList<String> volunteerEventsList,
            ArrayList<String> hostedSiteList,
            ArrayList<String> eventRequestsList
    ) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profileUrl = profileUrl;
        this.userRole = userRole;
        this.bloodType = bloodType;
        this.registeredEventsList = registeredEventsList;
        this.volunteerEventsList = volunteerEventsList;
        this.hostedSiteList = hostedSiteList;
        this.eventRequestsList = eventRequestsList;
    }

}

