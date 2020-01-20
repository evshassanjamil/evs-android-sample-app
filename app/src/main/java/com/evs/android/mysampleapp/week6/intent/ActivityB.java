package com.evs.android.mysampleapp.week6.intent;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;

public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        String name = "???";

        if (getIntent().getExtras() != null && getIntent().hasExtra("username")) {
            name = getIntent().getExtras().getString("username");
        }

        name = "Welcome, " + name + "!";
        ((TextView) findViewById(R.id.tvUsername)).setText(name);
    }
}
