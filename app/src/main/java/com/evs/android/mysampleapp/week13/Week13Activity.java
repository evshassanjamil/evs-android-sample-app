package com.evs.android.mysampleapp.week13;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;
import com.evs.android.mysampleapp.week13.location.ActivityLocationApi;
import com.evs.android.mysampleapp.week13.notification.ActivityNotification;
import com.evs.android.mysampleapp.week13.realtimeDatabase.ActivityRealtimeDatabase;

public class Week13Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week13);

        findViewById(R.id.btnLocationAPI).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Week13Activity.this, ActivityLocationApi.class));
            }
        });

        findViewById(R.id.btnNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Week13Activity.this, ActivityNotification.class));
            }
        });

        findViewById(R.id.btnRealtimeDB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Week13Activity.this, ActivityRealtimeDatabase.class));
            }
        });

    }
}
