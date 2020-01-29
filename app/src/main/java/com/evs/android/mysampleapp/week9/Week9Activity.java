package com.evs.android.mysampleapp.week9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;
import com.evs.android.mysampleapp.week6.intent.ActivityA;
import com.evs.android.mysampleapp.week9.animate.bitmaps.AnimateBitmapsActivity;
import com.evs.android.mysampleapp.week9.dialog.DialogActivity;
import com.evs.android.mysampleapp.week9.permission.PermissionActivity;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class Week9Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week9);

        findViewById(R.id.btnAnimateBitmaps).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Week9Activity.this, AnimateBitmapsActivity.class));
            }
        });

        findViewById(R.id.btnPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Week9Activity.this, PermissionActivity.class));
            }
        });

        findViewById(R.id.btnSharedPreferences).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Week9Activity.this, ActivityA.class));
            }
        });

        findViewById(R.id.btnAlertDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Week9Activity.this, DialogActivity.class));
            }
        });
    }
}
