package com.example.android.skeletonapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ToastIntentReceiver extends BroadcastReceiver {
    public ToastIntentReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Toast was selected.", Toast.LENGTH_LONG).show();
    }
}
