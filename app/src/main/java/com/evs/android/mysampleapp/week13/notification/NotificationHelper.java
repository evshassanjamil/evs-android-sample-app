package com.evs.android.mysampleapp.week13.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.evs.android.mysampleapp.R;

import java.util.HashMap;
import java.util.Objects;

class NotificationHelper {
    static void showNotification(Context context, @DrawableRes int resSmallIcon, BroadcastReceiver openReceiver,
                                 BroadcastReceiver dismissReceiver, @NonNull HashMap<String, String> map) {
        int flagPendingIntent = PendingIntent.FLAG_UPDATE_CURRENT;
        int flagIntent = Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK;
        NotificationManager nManager;
        Intent intent;
        PendingIntent pendingIntentNotifOpen, pendingIntentNotifDismiss;
        NotificationCompat.Builder builder;

        int id = Integer.valueOf(Objects.requireNonNull(map.get(Constants.NOTIFICATION_ID)));

        nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (nManager == null)
            return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // IF ANDROID OS Version is greater and equals than O then we need to create notification channel
            // before generating notification through NotificationManager/NotificationService.
            NotificationChannel mChannel = nManager.getNotificationChannel(
                    context.getString(R.string.default_channel));

            if (mChannel == null) {
                mChannel = new NotificationChannel(
                        context.getString(R.string.default_channel),
                        context.getString(R.string.default_channel),
                        NotificationManager.IMPORTANCE_HIGH);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                nManager.createNotificationChannel(mChannel);
            }
        }
        builder = new NotificationCompat.Builder(context,
                context.getString(R.string.default_channel));

        intent = new Intent(context, openReceiver.getClass());
        intent.setFlags(flagIntent);
        for (String key : map.keySet()) {
            intent.putExtra(key, map.get(key));
        }
        pendingIntentNotifOpen = PendingIntent.getActivity(context, id, intent, flagPendingIntent);
        Intent intentDismiss = new Intent(context, dismissReceiver.getClass());
        intentDismiss.putExtra(Constants.NOTIFICATION_ID, map.get(Constants.NOTIFICATION_ID));
        intentDismiss.putExtra(Constants.NOTIFICATION_NAME, map.get(Constants.NOTIFICATION_NAME));
        intentDismiss.setFlags(flagIntent);
        pendingIntentNotifDismiss = PendingIntent.getBroadcast(context.getApplicationContext(),
                id, intentDismiss, flagPendingIntent);

        builder.setContentTitle(map.get(Constants.NOTIFICATION_NAME))                            // required
                .setSmallIcon(resSmallIcon)   // required
                .setContentText(map.get(Constants.NOTIFICATION_SHORT_TEXT)) // required
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentIntent(pendingIntentNotifOpen)
                .setDeleteIntent(pendingIntentNotifDismiss)
                .setTicker(map.get(Constants.NOTIFICATION_NAME))
                .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

        if (nManager != null) {
            nManager.notify(id, builder.build());
        }
    }

}
