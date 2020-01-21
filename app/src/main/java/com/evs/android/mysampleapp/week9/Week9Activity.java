package com.evs.android.mysampleapp.week9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;
import com.evs.android.mysampleapp.week9.animate.bitmaps.AnimateBitmapsActivity;

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
    }
}
