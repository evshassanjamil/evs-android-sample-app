package com.evs.android.mysampleapp.week12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;
import com.evs.android.mysampleapp.week12.auth.GoogleSignInActivity;

public class Week12Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week12);

        findViewById(R.id.btnAuth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Week12Activity.this, GoogleSignInActivity.class));
            }
        });
    }
}
