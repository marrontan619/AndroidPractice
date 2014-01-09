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
        Notification notification = new Notification();
        notification.tickerText = "Notification was selected.";
        notification.when = System.currentTimeMillis();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(context.getString(R.string.skeleton_app), R.id.intentSelectButton, notification);
    }
}
