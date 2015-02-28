package com.example.admin.test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //подключение к parse.com
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "A4BZqxxFMoEyoUWcKmEFP3IWkF1SzMTYMITaXjQd", "qi9tySwA6DpLRGWSt5KlzEV4J3MNNG7BHy9CvYOc");

        //логин за manager1, сомневаюсь что этого хватит чтобы работало
        ParseUser.logInInBackground("manager1", "manager1", new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {

            }

        });
        TextView YourMessage = (TextView) findViewById(R.id.Show);
        YourMessage.setText("Any text");
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
    JsonContainer jsonContainer = myGson.fromJson(json, JsonContainer.class); // json - будем надеятся что так будет называться, то что получим от parse.com

}
