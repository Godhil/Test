package com.example.admin.test;


import com.example.admin.test.Login.LoginResult;
import com.example.admin.test.Office.Place.CreateReservation;
import com.example.admin.test.Office.Reservation.ReservationFeed;
import com.example.admin.test.Office.OfficeFeed;
import com.example.admin.test.Office.Place.PlaceFeed;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
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
    @GET("/1/classes/Office/")
    public void getOfficeInfo(Callback<OfficeFeed> response);

    //запрос броней
    @Headers({
            "X-Parse-Application-Id: A4BZqxxFMoEyoUWcKmEFP3IWkF1SzMTYMITaXjQd",
            "X-Parse-REST-API-Key: qi9tySwA6DpLRGWSt5KkzEV4J3MNNG7BHy9CvYOc"
    })
    @GET("/1/classes/Reservation")
    public void getReservation(Callback<ReservationFeed> response);

    //запрос данных о столиках
    @Headers({
            "X-Parse-Application-Id: A4BZqxxFMoEyoUWcKmEFP3IWkF1SzMTYMITaXjQd",
            "X-Parse-REST-API-Key: qi9tySwA6DpLRGWSt5KkzEV4J3MNNG7BHy9CvYOc"
    })
    @GET("/1/classes/Place")
    public void getPlaceInfo(Callback<PlaceFeed> response);

    //создание брони, не работает привязка к столику
    @Headers({
            "X-Parse-Application-Id: A4BZqxxFMoEyoUWcKmEFP3IWkF1SzMTYMITaXjQd",
            "X-Parse-REST-API-Key: qi9tySwA6DpLRGWSt5KkzEV4J3MNNG7BHy9CvYOc"
    })
    @POST("/1/classes/Reservation")
    public void createReservation(@Body CreateReservation createReservation, Callback<Response> response);

    //удаление брони, что то тут не так
    @Headers({
            "X-Parse-Application-Id: A4BZqxxFMoEyoUWcKmEFP3IWkF1SzMTYMITaXjQd",
            "X-Parse-REST-API-Key: qi9tySwA6DpLRGWSt5KkzEV4J3MNNG7BHy9CvYOc"
    })
    @DELETE("/")
    public void deleteReservation(Callback<Response> response);
}
