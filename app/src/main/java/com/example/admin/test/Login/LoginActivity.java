package com.example.admin.test.Login;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.admin.test.API;
import com.example.admin.test.Office.MainActivity;
import com.example.admin.test.R;
import com.example.admin.test.TempVariables;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TempVariables tempVariables = new TempVariables();
        final TextView userLogin = (TextView) findViewById(R.id.editLogin);
        final TextView userPassword = (TextView) findViewById(R.id.editPassword);
        final TextView checkLogin = (TextView) findViewById(R.id.textStatus);


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
                    @Override
                    public void success(final LoginResult loginResult, Response response) {
                        tempVariables.setTempUserName(loginResult.getUsername());
                        userLogin.setText("");
                        userPassword.setText("");
                        LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        Log.e("Login", retrofitError.getMessage());
                        TextView checkLogin = (TextView) findViewById(R.id.textStatus);
                        checkLogin.setText("Неверный логин или пароль");
                    }
                });
            }
        });
        //конец

    }
}
