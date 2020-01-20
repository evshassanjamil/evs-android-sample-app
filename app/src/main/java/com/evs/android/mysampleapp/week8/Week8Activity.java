package com.evs.android.mysampleapp.week8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.evs.android.mysampleapp.R;
import com.evs.android.mysampleapp.week8.recyclerview.ListActivity;

public class Week8Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week8);

        findViewById(R.id.btnRecyclerView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Week8Activity.this, ListActivity.class));
            }
        });
    }
}
