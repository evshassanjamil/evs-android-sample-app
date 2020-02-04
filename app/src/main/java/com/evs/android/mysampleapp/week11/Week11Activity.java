package com.evs.android.mysampleapp.week11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;
import com.evs.android.mysampleapp.week11.actionbar.ActionbarActivity;
import com.evs.android.mysampleapp.week11.viewpager.ViewPagerActivity;

public class Week11Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week11);

        findViewById(R.id.btnActionbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Week11Activity.this, ActionbarActivity.class));
            }
        });

        findViewById(R.id.btnViewPager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Week11Activity.this, ViewPagerActivity.class));
            }
        });

        /*findViewById(R.id.btnSqlite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Week11Activity.this, SqliteActivity.class));
            }
        });*/
    }
}
