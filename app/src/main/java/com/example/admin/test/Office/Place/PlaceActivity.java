package com.example.admin.test.Office.Place;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.test.API;
import com.example.admin.test.Office.Reservation.ReservationActivity;
import com.example.admin.test.R;
import com.example.admin.test.TempVariables;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class PlaceActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        final EditText editUserName = (EditText)findViewById(R.id.clientName);
        final EditText editUserPhone = (EditText)findViewById(R.id.clientPhone);

        //Запрос списка столиков
        final TempVariables temp = new TempVariables();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://api.parse.com")
                .build();
        final API officePlase = restAdapter.create(API.class);
        officePlase.getPlaceInfo(new Callback<PlaceFeed>() {
            @Override
            public void success(PlaceFeed placeFeed, Response response) {
                final String[] tempString = new String[1];
                int numStr = 0;
                final ArrayList<String> placeId = new ArrayList<String>(); //id столика
                ArrayList<String> aboutPlace = new ArrayList<String>(); //данные о столике
                for(int i = 0; i < placeFeed.getResults().size(); i++){
                    if (placeFeed.getResults().get(i) != null) { //а был ли мальчик
                        if (placeFeed.getResults().get(i).getOffice() != null) {
                            if (placeFeed.getResults().get(i).getOffice().getObjectId() != null) {
                                if(placeFeed.getResults().get(i).getOffice().getObjectId().equals(temp.getTempOfficeId())){
                                    placeId.add(numStr, placeFeed.getResults().get(i).getObjectId());
                                    aboutPlace.add(numStr, "Столик № " + placeFeed.getResults().get(i).getNumber() + ", количество мест " + placeFeed.getResults().get(i).getCapacity());
                                    numStr++;
                                }
                            }
                        } else Log.d("Office", "empty");
                    }else Log.d("Results", "empty");
                }
                //Collections.sort(about);
                Spinner spinnerPlace = (Spinner)findViewById(R.id.spinnerPlace);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PlaceActivity.this, android.R.layout.simple_spinner_dropdown_item, aboutPlace);
                spinnerPlace.setAdapter(adapter);

                //вывод полученного списка
                spinnerPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        tempString[0] = placeId.get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                });
                //конец вывода списка

                //начало кнопки создать
                Button btnCreate = (Button) findViewById(R.id.btnCreate);
                btnCreate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userName = editUserName.getText().toString();
                        String userPhone = editUserPhone.getText().toString();
                        if (userName.length() != 0){
                            if (userPhone.length() != 0){
                                //отправка новой брони
                                RestAdapter restAdapter1 = new RestAdapter.Builder()
                                        .setLogLevel(RestAdapter.LogLevel.FULL)
                                        .setEndpoint("https://api.parse.com")
                                        .build();
                                final API createRes = restAdapter1.create(API.class);
                                CreateReservation.Place place = new CreateReservation.Place();
                                place.setType("Pointer");
                                place.setClassName("Place");
                                place.setObjectId(tempString[0]);
                                CreateReservation createReservation = new CreateReservation(userName, userPhone, place);
                                createRes.createReservation(createReservation, new Callback<Response>() {
                                    @Override
                                    public void success(Response response, Response response2) {
                                        PlaceActivity.this.startActivity(new Intent(PlaceActivity.this, ReservationActivity.class));
                                    }

                                    @Override
                                    public void failure(RetrofitError retrofitError) {
                                        Log.e("Create", retrofitError.getMessage());
                                    }
                                });
                                //конец отправки
                            }else Toast.makeText(getBaseContext(), "Введите номер телефона клиента", Toast.LENGTH_SHORT).show();
                        }else Toast.makeText(getBaseContext(), "Введите имя клиента", Toast.LENGTH_SHORT).show();

                    }
                });
                //конец кнопки создать

                //начало кнопки отмена
                Button btnCancel = (Button)findViewById(R.id.btnCancel);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PlaceActivity.this.startActivity(new Intent(PlaceActivity.this, ReservationActivity.class));
                    }
                });
                //конец кнопки отмена
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.e("Place", retrofitError.getMessage());
            }
        });

    }



}
