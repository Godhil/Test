package com.example.admin.test.Office.Reservation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

        //начало запроса броней
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://api.parse.com")
                .build();
        final API reservationInfo = restAdapter.create(API.class);
        reservationInfo.getReservation(new Callback<ReservationFeed>() {
            @Override
            public void success(final ReservationFeed reservationFeed, final Response response) {
                final ArrayList<String> name = new ArrayList<String>();
                final ArrayList<String> resId = new ArrayList<String>();
                for (int i = 0; i < reservationFeed.getResults().size(); i++) {
                    name.add(i, "Имя" + reservationFeed.getResults().get(i).getCustomerName() + "Телефон" + reservationFeed.getResults().get(i).getCustomerPhone());
                    resId.add(i, reservationFeed.getResults().get(i).getObjectId());
                }
                ListView listView = (ListView) findViewById(R.id.reservation);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ReservationActivity.this, android.R.layout.simple_list_item_1, name);
                listView.setAdapter(adapter);

                //нажатие по элементу списка
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ReservationActivity.this);
                        builder
                                .setTitle("Удалить")
                                .setMessage("Вы уверены?")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        //удаление HardCore Edition, не смог в запрос
                                        RestAdapter restAdapter1 = new RestAdapter.Builder()
                                                .setLogLevel(RestAdapter.LogLevel.FULL)
                                                .setEndpoint("https://api.parse.com/1/classes/Reservation/" + resId.get(position))
                                                .build();
                                        final API delRes = restAdapter1.create(API.class);
                                        delRes.deleteReservation(new Callback<Response>() {
                                            @Override
                                            public void success(Response response, Response response2) {

                                                //обновление, если удалилось
                                                Intent intent = getIntent();
                                                finish();
                                                startActivity(intent);
                                                //конец обновления

                                            }

                                            @Override
                                            public void failure(RetrofitError retrofitError) {
                                                Log.e("Delete", retrofitError.getMessage());
                                                Toast.makeText(getBaseContext(), "Что-то пошло не так и не удалилось", Toast.LENGTH_SHORT).show();
                                            }

                                        });
                                        //конец удаления

                                    }
                                })
                                .setNegativeButton("Нет", null)
                                .show();

                    }
                });
            }

            @Override
            public void failure(RetrofitError retrofitError) {

            }
        });
        //конец запроса


        //создать новую бронь
        Button btnCreateNew = (Button) findViewById(R.id.buttonCreateNew);
        btnCreateNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                ReservationActivity.this.startActivity(new Intent(ReservationActivity.this, PlaceActivity.class));
            }
        });
        //конец создавания
    }
}
