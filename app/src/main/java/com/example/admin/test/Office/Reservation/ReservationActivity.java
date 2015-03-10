package com.example.admin.test.Office.Reservation;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.admin.test.API;
import com.example.admin.test.Office.Place.PlaceActivity;
import com.example.admin.test.R;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ReservationActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://api.parse.com")
                .build();
        final API bookingInfo = restAdapter.create(API.class);
        bookingInfo.getBooking(new Callback<ReservationFeed>() {
            @Override
            public void success(final ReservationFeed reservationFeed, final Response response) {
                final ArrayList<String> name = new ArrayList<String>();
                final ArrayList<String> resId = new ArrayList<String>();
                for(int i = 0; i < reservationFeed.getResults().size(); i++){
                    name.add(i, reservationFeed.getResults().get(i).getCustomerName() + " " + reservationFeed.getResults().get(i).getObjectId());
                    resId.add(i, reservationFeed.getResults().get(i).getObjectId());
                }
                ListView listView = (ListView)findViewById(R.id.reservation);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ReservationActivity.this, android.R.layout.simple_list_item_1, name);
                listView.setAdapter(adapter);

                //нажатие по элементу списка
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //удаление HardCore Edition, не смог в запрос
                        RestAdapter restAdapter1 = new RestAdapter.Builder()
                                .setLogLevel(RestAdapter.LogLevel.FULL)
                                .setEndpoint("https://api.parse.com/1/classes/Reservation/" + resId.get(position))
                                .build();
                        final API delRes = restAdapter1.create(API.class);
                        delRes.deleteReservation(new Callback<Response>() {
                            @Override
                            public void success(Response response, Response response2) {
                                Log.d("Delete", "success");
                            }

                            @Override
                            public void failure(RetrofitError retrofitError) {
                                Log.e("Delete", retrofitError.getMessage());
                            }

                        });
                    }
                });
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });


        //создать новую бронь
        Button btnCreateNew = (Button) findViewById(R.id.buttonCreateNew);
        btnCreateNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                ReservationActivity.this.startActivity(new Intent(ReservationActivity.this, PlaceActivity.class));
            }
        });
        //конец создавания

        //боновить
        //@Override
        Button btnRefrash = (Button)findViewById(R.id.buttonRefresh);
        btnRefrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        //конец обновления

    }
}
