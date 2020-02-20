package com.evs.android.mysampleapp.week12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;
import com.evs.android.mysampleapp.week12.auth.GoogleSignInActivity;
import com.evs.android.mysampleapp.week12.cameraGallery.ImagePickActivity;
import com.evs.android.mysampleapp.week12.media.VideoActivity;
import com.evs.android.mysampleapp.week12.network.NetworkActivity;
import com.evs.android.mysampleapp.week12.restapi.RestApiActivity;

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

        findViewById(R.id.btnMedia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Week12Activity.this, VideoActivity.class));
            }
        });

        findViewById(R.id.btnNetwork).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Week12Activity.this, NetworkActivity.class));
            }
        });

        findViewById(R.id.btnImagePicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Week12Activity.this, ImagePickActivity.class));
            }
        });

        findViewById(R.id.btnRestApi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Week12Activity.this, RestApiActivity.class));
            }
        });
    }
}
