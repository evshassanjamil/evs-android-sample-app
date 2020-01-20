package com.evs.android.mysampleapp.week6.worker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootCompleteReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == null || !intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
            return;
        Log.i("BootCompleteReceiver", "Worker started");
        MyWorker.enqueueSelf(context);
    }
}