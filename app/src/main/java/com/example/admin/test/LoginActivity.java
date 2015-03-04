package com.example.admin.test;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LoginActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView userLogin = (TextView) findViewById(R.id.editLogin);
        final TextView userPassword = (TextView) findViewById(R.id.editPassword);

        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String setLogin = userLogin.getText().toString();
                String setPassword = userPassword.getText().toString();
                //логин
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .setEndpoint("https://api.parse.com")
                        .build();
                final API myLogin = restAdapter.create(API.class);
                myLogin.getData(setLogin, setPassword, new Callback<LoginResult>() {
                    //myLogin.getData("manager1", "manager1", new Callback<LoginResult>() {
                    @Override
                    public void success(final LoginResult loginResult, Response response) {
                        Log.d("GSON", loginResult.getUsername() + loginResult.getSessionToken());
                        TextView checkLogin = (TextView) findViewById(R.id.textView2);
                        checkLogin.setText("Login success");
                        //посмотреть кто залогинился
                        findViewById(R.id.buttonShow).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String myName = "UserName ";
                                String myId = "Id ";
                                TextView checkLogin = (TextView) findViewById(R.id.textView2);
                                checkLogin.setText(myName + loginResult.getUsername() + ", " + myId + loginResult.getObjectId());


                            }
                        });
                        findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        Log.e("Login ", retrofitError.getMessage());
                        TextView checkLogin = (TextView) findViewById(R.id.textView2);
                        checkLogin.setText("Login " + retrofitError.getMessage());
                    }
                });
            }
        });
    }
}
