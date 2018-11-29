package com.example.lukas.midterm2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/*
Lukas Newman
Details.java
Midterm
Emulated on API 25
 */

public class Details extends AppCompatActivity {
    TextView theMinTemp;
    TextView theMaxTemp;
    TextView theWindSpeed;
    TextView theHumidity;
    TextView detailsfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setTitle("Details");

        Intent intent = this.getIntent();
        Forecast foreDetails = (Forecast)intent.getSerializableExtra("weather");

        detailsfor = (TextView)findViewById(R.id.textViewDetails);
        theMinTemp = (TextView)findViewById(R.id.textViewMin);
        theMaxTemp = (TextView)findViewById(R.id.textViewMax);
        theWindSpeed = (TextView)findViewById(R.id.textViewWind);
        theHumidity = (TextView)findViewById(R.id.textViewHumidity);

        detailsfor.setText("Details for " + foreDetails.getDatetimeText());
        theMinTemp.setText("Minimum Temperature: " + foreDetails.getMinTemp());
        theMaxTemp.setText("Maximum Temperature: " + foreDetails.getMaxTemp());
        theWindSpeed.setText("Wind Speed: " + foreDetails.getWindSpeed());
        theHumidity.setText("Humidity: " + foreDetails.getHumidity());

    }
}
