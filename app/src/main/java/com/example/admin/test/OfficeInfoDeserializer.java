package com.example.admin.test;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by admin on 02.03.2015.
 */
public class OfficeInfoDeserializer implements JsonDeserializer<OfficeInfo> {
    @Override
    public OfficeInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException{
        OfficeInfo result = new OfficeInfo();
        JsonObject jsonObject = json.getAsJsonObject();

        for(Map.Entry<String, JsonElement> entry : jsonObject.entrySet()){
            Office office = context.deserialize(entry.getValue(), Office.class);
            office.setName(entry.getKey());
            result.addOffice(office);
        }

        return result;
    }
}
