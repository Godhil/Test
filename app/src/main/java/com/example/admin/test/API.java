package com.example.admin.test;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

/**
 * Created by admin on 01.03.2015.
 */
public interface API {

    //запрос логина
    @Headers({
            "X-Parse-Application-Id: A4BZqxxFMoEyoUWcKmEFP3IWkF1SzMTYMITaXjQd",
            "X-Parse-REST-API-Key: qi9tySwA6DpLRGWSt5KkzEV4J3MNNG7BHy9CvYOc"
    })
    @GET("/1/login")
    public void getData(@Query("username") String userName, @Query("password") String password, Callback<LoginResult> response);

    //запрос данных офисов
    @Headers({
            "X-Parse-Application-Id: A4BZqxxFMoEyoUWcKmEFP3IWkF1SzMTYMITaXjQd",
            "X-Parse-REST-API-Key: qi9tySwA6DpLRGWSt5KkzEV4J3MNNG7BHy9CvYOc"
    })
    @GET(("/1/classes/Office"))
    public void getOfficeInfo(Callback<OfficeFeed> response);
}
