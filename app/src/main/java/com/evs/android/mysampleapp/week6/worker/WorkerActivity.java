package com.evs.android.mysampleapp.week6.worker;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;

public class WorkerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);

        findViewById(R.id.btnStartWorkerActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyWorker.enqueueSelf(WorkerActivity.this);
            }
        });

        findViewById(R.id.btnCancelWorker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyWorker.dequeueSelf(WorkerActivity.this);
            }
        });
    }


}
