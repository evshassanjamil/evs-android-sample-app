package com.evs.android.mysampleapp.week13.location;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;

public class ActivityLocationApi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_api);

        /*TextView tvLocationDetails = findViewById(R.id.tvLocationDetails);
        String strLocation = "Latitude: " + location.lat + "\n" +
                "Longitude: " + location.longi;
        tvLocationDetails.setText(strLocation);*/
    }
}
