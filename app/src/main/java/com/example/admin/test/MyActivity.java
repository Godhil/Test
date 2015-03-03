package com.example.admin.test;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;


public class MyActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final TextView userLogin = (TextView) findViewById(R.id.editLogin);
        final TextView userPassword = (TextView) findViewById(R.id.editPassword);

        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String setLogin = userLogin.getText().toString();
                String setPassword = userPassword.getText().toString();
                //логин
                Gson gsonLogin = new GsonBuilder()
                        .registerTypeAdapter(LoginInfo.class, new LoginDesserialize())
                        .create();
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .setEndpoint("https://api.parse.com")
                        .setConverter(new GsonConverter(gsonLogin))
                        .build();
                final API myLogin = restAdapter.create(API.class);
                myLogin.login(setLogin,setPassword, new Callback<Object>() {
                    @Override
                    public void success(Object o, Response response) {
                        if(response != null) {
                            Log.d("Login ", "success");
                            TextView checkLogin = (TextView) findViewById(R.id.textView2);
                            checkLogin.setText("Login success");
                        }
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        if(retrofitError != null){
                            Log.e("Login ", retrofitError.getMessage());
                            TextView checkLogin = (TextView) findViewById(R.id.textView2);
                            checkLogin.setText("Login " + retrofitError.getMessage());
                        }
                    }
                });
            }
        });
/*
        findViewById(R.id.buttonShow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginInfo my = new LoginInfo();

                String myName = my.getUserName();
                String myEmail = my.getEmail();
                String myId = my.getObjectId();
                String myToken = my.getSessionToken();
                TextView checkLogin = (TextView) findViewById(R.id.textView2);
                checkLogin.setText(myName);
            }
        });*/
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
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
