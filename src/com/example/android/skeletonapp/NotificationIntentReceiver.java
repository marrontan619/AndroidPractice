package com.example.android.skeletonapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationIntentReceiver extends BroadcastReceiver {
    public NotificationIntentReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification notification = new Notification();
//        notification.icon = android.R.drawable.stat_notify_call_mute;
//        notification.tickerText = "You received an new Notification.";
//        notification.when = System.currentTimeMillis();
//        notification.flags = Notification.FLAG_AUTO_CANCEL;
//        notification.setLatestEventInfo(context, "Title", "Content Text", null);
//        notificationManager.notify(context.getString(R.string.skeleton_app), R.id.intentSelectButton, notification);
        Notification notification = new Notification.Builder(context)
            .setSmallIcon(android.R.drawable.stat_notify_call_mute)
            .setTicker("You received an new Notification.")
            .setAutoCancel(true)
            .setContentTitle("Title")
            .setContentText("Content Text")
            .build();
        notificationManager.notify(R.string.skeleton_app, notification);
    }
}
