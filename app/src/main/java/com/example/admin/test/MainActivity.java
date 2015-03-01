package com.example.admin.test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //подключение к parse.com
        Parse.enableLocalDatastore(this);
       // Parse.initialize(this, "A4BZqxxFMoEyoUWcKmEFP3IWkF1SzMTYMITaXjQd", "qi9tySwA6DpLRGWSt5KkzEV4J3MNNG7BHy9CvYOc");
        Parse.initialize(this, "Co8dcFpQqW68wuoHyw88THP2uCmnynnnamLoZgLq", "dHGkBk8SuRzMdZjKWSkvOAnxfRccU1IE5gFJCtgs");
        //логин, сомневаюсь что этого хватит чтобы работало

        ParseUser newUser = new ParseUser();
        newUser.setUsername("Manager2");
        newUser.setPassword("1234");
        newUser.setEmail("ma1n@ya.ru");
        newUser.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    TextView createUser = (TextView) findViewById(R.id.createUser);
                    createUser.setText("New user creating");
                    // Hooray! Let them use the app now.
                } else {
                    TextView checkLogin = (TextView) findViewById(R.id.createUser);
                    checkLogin.setText("New user creating fauled");
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });
        ParseUser.logInInBackground("Manager", "1234", new LogInCallback() {
            public void done(ParseUser userManager1, ParseException e) {
                if (userManager1 != null) {
                    TextView checkLogin = (TextView) findViewById(R.id.checkLogin);
                    checkLogin.setText("Login is work");
                    // Hooray! The user is logged in.
                } else {
                    TextView checkLogin = (TextView) findViewById(R.id.checkLogin);
                    checkLogin.setText("Login failed");
                    // Signup failed. Look at the ParseException to see what happened.
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


    //список, который содержит полученный данные от parse.com
    public static void office(){
        ArrayList officeList = new ArrayList();
    }

    Gson myGson = new GsonBuilder().create();
   // JsonContainer jsonContainer = myGson.fromJson(json, JsonContainer.class); // json - будем надеятся что так будет называться, то что получим от parse.com


}
