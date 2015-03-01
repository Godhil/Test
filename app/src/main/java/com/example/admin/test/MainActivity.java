package com.example.admin.test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //api
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://api.github.com")
                .build();
        final API myLogin = restAdapter.create(API.class);
        myLogin.login("manager1","manager1", new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                TextView checkLogin = (TextView) findViewById(R.id.checkLogin);
                checkLogin.setText("Login is work");
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                //строчка ниже смотрит ошибку в json'е
                String json =  new String(((TypedByteArray)retrofitError.getResponse().getBody()).getBytes());


                TextView checkLogin = (TextView) findViewById(R.id.checkLogin);
                checkLogin.setText(json);
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
