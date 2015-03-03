package com.example.admin.test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedByteArray;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //api
        Gson gsonLogin = new GsonBuilder()
                .registerTypeAdapter(LoginInfo.class, new LoginDesserialize())
                .create();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://api.parse.com")
                .setConverter(new GsonConverter(gsonLogin))
                .build();
        final API myLogin = restAdapter.create(API.class);
        myLogin.login("manager2","manager2", new Callback<Object>() {
            @Override
            public void success(Object o, Response response) {
                if(response != null) {
                    Log.d("Login ", "success");
                    TextView checkLogin = (TextView) findViewById(R.id.checkLogin);
                    checkLogin.setText(o.toString());
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                if(retrofitError != null){
                    Log.e("Login ", retrofitError.toString());
                }
            }
        });


        try {
            Thread.sleep(2000);
            // any action
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //получение списка офисов
        final Gson gsonOffice = new GsonBuilder()
                .registerTypeAdapter(OfficeInfo.class, new OfficeInfoDeserializer())
                .registerTypeAdapter(Office.class, new OfficeDesserializer())
                .create();
        final RestAdapter restAdapterOffice = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gsonOffice))
                .setEndpoint("https://api.parse.com")
                .build();
        final API officeInfo = restAdapterOffice.create(API.class); //работает, но кажется, что что-то тут не так
        officeInfo.getOfficeInfo(new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                if(response != null){
                    Log.d("Office", response.getBody().toString());
                    Log.d("Office", response2.getBody().toString());
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                if(retrofitError != null){
                    Log.e("Office", retrofitError.getBody().toString());
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
