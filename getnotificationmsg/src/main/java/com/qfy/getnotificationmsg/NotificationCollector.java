package com.qfy.getnotificationmsg;

import android.annotation.SuppressLint;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

@SuppressLint("NewApi")
public class NotificationCollector extends NotificationListenerService {
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.i("xiaolong", "open" + "-----" + sbn.getPackageName());
        Log.i("xiaolong", "open" + "------" + sbn.getNotification().tickerText);
        Log.i("xiaolong", "open" + "-----" + sbn.getNotification().extras.get("android.title"));
        Log.i("xiaolong", "open" + "-----" + sbn.getNotification().extras.get("android.text"));
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("xiaolong", "remove" + "-----" + sbn.getPackageName());

    }
}
