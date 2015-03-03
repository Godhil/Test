package com.example.admin.test;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

/**
 * Created by admin on 01.03.2015.
 */
public interface API {

    //реализация логина
    @Headers({
            //motmom
            "X-Parse-Application-Id: A4BZqxxFMoEyoUWcKmEFP3IWkF1SzMTYMITaXjQd",
            "X-Parse-REST-API-Key: qi9tySwA6DpLRGWSt5KkzEV4J3MNNG7BHy9CvYOc"

            //моё
            /*"X-Parse-Application-Id: Co8dcFpQqW68wuoHyw88THP2uCmnynnnamLoZgLq",
            "X-Parse-REST-API-Key: 0LiLbe5BYH0T1KQWq6X8yIjBrG3UsiLxNJyGsSN2"*/
    })
    @GET("/1/login")
    public void login(@Query("username") String userName, @Query("password") String password, Callback<Object> calBack);

    //запрос данных офисов
    @Headers({
            //motmom
            "X-Parse-Application-Id: A4BZqxxFMoEyoUWcKmEFP3IWkF1SzMTYMITaXjQd",
            "X-Parse-REST-API-Key: qi9tySwA6DpLRGWSt5KkzEV4J3MNNG7BHy9CvYOc"

            //моё
            /*"X-Parse-Application-Id: Co8dcFpQqW68wuoHyw88THP2uCmnynnnamLoZgLq",
            "X-Parse-REST-API-Key: 0LiLbe5BYH0T1KQWq6X8yIjBrG3UsiLxNJyGsSN2"*/
    })
    @GET(("/1/classes/Office"))
    public void getOfficeInfo(Callback<Object> officeNames);
}
