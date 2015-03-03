package com.example.admin.test;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by admin on 03.03.2015.
 */
public class LoginDesserialize implements JsonDeserializer<LoginInfo> {
    @Override
    public LoginInfo deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException{
        JsonObject jsonObject = json.getAsJsonObject();
        LoginInfo myLoginInfo = new LoginInfo();
        myLoginInfo.setEmail(jsonObject.get("email").getAsString());
        myLoginInfo.setObjectId(jsonObject.get("objectId").getAsString());
        myLoginInfo.setSessionToken(jsonObject.get("sessionToken").getAsString());
        myLoginInfo.setUserName(jsonObject.get("username").getAsString());
        return myLoginInfo;

    }
}
