package com.evs.android.mysampleapp.week6.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    public static final String ACTION = "com.evs.android.MESSAGE";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getExtras() != null
                && intent.hasExtra("msg")) {
            Toast.makeText(context, "Receiver - Message Received from "
                            + context.getApplicationInfo().packageName
                            + ": " + intent.getExtras().getString("msg"),
                    Toast.LENGTH_LONG).show();
        }
    }
}
