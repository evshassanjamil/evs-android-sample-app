package com.evs.android.mysampleapp.week6.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.evs.android.mysampleapp.R;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class BroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        findViewById(R.id.btnBroadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent localIntent = new Intent(MyBroadcastReceiver.ACTION);
                String msg = ((EditText) findViewById(R.id.etMessage)).getText().toString();
                localIntent.putExtra("msg", msg);
                LocalBroadcastManager.getInstance(BroadcastActivity.this).sendBroadcast(localIntent);
            }
        });
    }

    MyBroadcastReceiver broadcastReceiver = new MyBroadcastReceiver();

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter(MyBroadcastReceiver.ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, filter);

    }

    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }
}
