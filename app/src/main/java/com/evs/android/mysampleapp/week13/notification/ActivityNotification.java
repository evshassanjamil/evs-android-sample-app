package com.evs.android.mysampleapp.week13.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;
import com.evs.android.mysampleapp.utils.AppUtils;

import java.util.HashMap;

public class ActivityNotification extends AppCompatActivity {

    BroadcastReceiver openReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            AppUtils.showToastShort(context.getApplicationContext(), "Notification Open");
        }
    };

    BroadcastReceiver dismissReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            AppUtils.showToastShort(context.getApplicationContext(), "Notification Dismiss");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ((EditText) findViewById(R.id.etNotifId)).setText("1");
        ((EditText) findViewById(R.id.etNotifTitle)).setText("Alert");
        ((EditText) findViewById(R.id.etNotifShortContent)).setText("A short alert message");
        ((EditText) findViewById(R.id.etNotifLongContent)).setText("A long alert message");

        findViewById(R.id.btnGenerate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();
                map.put(Constants.NOTIFICATION_ID,
                        ((EditText) findViewById(R.id.etNotifId)).getText().toString());
                map.put(Constants.NOTIFICATION_NAME,
                        ((EditText) findViewById(R.id.etNotifTitle)).getText().toString());
                map.put(Constants.NOTIFICATION_SHORT_TEXT,
                        ((EditText) findViewById(R.id.etNotifShortContent)).getText().toString());
                map.put(Constants.NOTIFICATION_LONG_TEXT,
                        ((EditText) findViewById(R.id.etNotifLongContent)).getText().toString());
                NotificationHelper.showNotification(ActivityNotification.this,
                        R.drawable.ic_notifications, openReceiver, dismissReceiver, map);
            }
        });


    }
}
