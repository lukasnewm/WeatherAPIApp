package com.example.lukas.midterm2018;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/*
Lukas Newman
MainActivity.java
Midterm
Emulated on API 25
 */

public class MainActivity extends AppCompatActivity implements GetAPIAndParse.MyInterface {
    ArrayList<Forecast> theWeathers = new ArrayList<>();
    Button getForecast;
    EditText theCity;
    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Weather App");
        //Stop the keyboard from moving the list view
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        getForecast = (Button)findViewById(R.id.buttonGetWeather);
        theCity = (EditText)findViewById(R.id.editTextCity);

        getForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected() == true && theCity.getText().length() > 0) {
                    city = theCity.getText().toString().toLowerCase();
                    GetAPIAndParse getTheWeather = new GetAPIAndParse(MainActivity.this, MainActivity.this);
                    getTheWeather.execute(city);
                    getForecast.setEnabled(false);
                    theCity.setEnabled(false);

                } else {
                    if (isConnected() == false) {
                        Toast.makeText(MainActivity.this, "Connect to the Internet", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Enter a City", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;

    }


    @Override
    public void sendWeather(final ArrayList<Forecast> weatherList) {
        theWeathers = weatherList;
        ListView weatherListView = (ListView)findViewById(R.id.listViewWeather);
        weatherListView.setVisibility(View.VISIBLE);
        getForecast.setEnabled(true);
        theCity.setEnabled(true);

        WeatherAdapter weatherAdapter = new WeatherAdapter(MainActivity.this, R.layout.list_weather, theWeathers);
        weatherListView.setAdapter(weatherAdapter);

        weatherListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent myIntent = new Intent(MainActivity.this, Details.class);
                myIntent.putExtra("weather", theWeathers.get(i));
                startActivity(myIntent);

            }
        });

    }
}
