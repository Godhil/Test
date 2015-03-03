package com.example.admin.test;

import android.util.Log;
import android.widget.TextView;

/**
 * Created by admin on 03.03.2015.
 */
public class LoginInfo {
    private String userName, sessionToken, objectId, email;

    public LoginInfo(){

    }

    public LoginInfo(String userName, String sessionToken, String objectId, String email){
        this.userName = userName;
        this.sessionToken = sessionToken;
        this.objectId = objectId;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
