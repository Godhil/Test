package com.example.admin.test;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by admin on 02.03.2015.
 */
public class OfficeDesserializer implements JsonDeserializer<Office> {
    @Override
    public Office deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException{
        JsonObject jsonObject = json.getAsJsonObject();

        Office office = new Office();
        office.setName(jsonObject.get("name").getAsString());
        office.setAdress(jsonObject.get("address").getAsString());
        return office;
    }
}
