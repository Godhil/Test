package com.example.admin.test.Office;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.admin.test.API;
import com.example.admin.test.CustomListAdapter;
import com.example.admin.test.Login.LoginActivity;
import com.example.admin.test.Office.Reservation.ReservationActivity;
import com.example.admin.test.Office.Place.PlaceActivity;
import com.example.admin.test.R;
import com.example.admin.test.TempVariables;

import java.io.IOException;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TempVariables tempVariables = new TempVariables();
        //переход к логину
        findViewById(R.id.buttonLoginPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        //что то там делает синхронно
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //получение списка офисов + вывод названий
        final RestAdapter restAdapterOffice = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://api.parse.com")
                .build();
        final API officeInfo = restAdapterOffice.create(API.class);
        officeInfo.getOfficeInfo("OgzGGHp90V",new Callback<OfficeFeed>() {
            @Override
            public void success(final OfficeFeed officeFeed, Response response) {

                int sizeList = officeFeed.getFeed().size();
                final Bitmap[] imageId = new Bitmap[sizeList];
                final String[] name = new String[sizeList];
                final String[] adress = new String[sizeList];

                for (int i = 0; i < sizeList; i++) {
                    try {
                        imageId[i] = BitmapFactory.decodeStream(officeFeed.getFeed().get(i).getImage().getUrl().openConnection().getInputStream());
                        name[i] = officeFeed.getFeed().get(i).getName();
                        adress[i]= officeFeed.getFeed().get(i).getAdress();
                    } catch (IOException e) {
                    e.printStackTrace();
                }
                }

                //выводит названия заведений в listview
                CustomListAdapter adapter = new CustomListAdapter(MainActivity.this, name, adress, imageId);
                ListView list=(ListView)findViewById(R.id.listViewOffice);
                list.setAdapter(adapter);

                //нажатие по элементу списка
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tempVariables.setTempOfficeId(officeFeed.getFeed().get(position).getObjectId());
                        MainActivity.this.startActivity(new Intent(MainActivity.this, ReservationActivity.class));
                    }
                });
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.e("Office", retrofitError.getMessage());
            }
        });
        //конец


        findViewById(R.id.bu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, ReservationActivity.class));

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
