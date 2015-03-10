package com.example.admin.test;

/**
 * Created by admin on 08.03.2015.
 */
public class TempVariables {
    private static String tempOfficeId;
    private static String tempUserName;



    public static String getTempUserName() {
        return tempUserName;
    }
    public static void setTempUserName(String tempUserName) {
        TempVariables.tempUserName = tempUserName;
    }

    public static String getTempOfficeId() {
        return tempOfficeId;
    }
    public static void setTempOfficeId(String tempOfficeId) {
        TempVariables.tempOfficeId = tempOfficeId;
    }
}
