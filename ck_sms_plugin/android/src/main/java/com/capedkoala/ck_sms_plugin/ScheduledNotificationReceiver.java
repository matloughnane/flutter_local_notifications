package com.capedkoala.ck_sms_plugin;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Keep;
import androidx.core.app.NotificationManagerCompat;

import com.capedkoala.ck_sms_plugin.models.NotificationDetails;
import com.capedkoala.ck_sms_plugin.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by michaelbui on 24/3/18.
 */

@Keep
public class ScheduledNotificationReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(final Context context, Intent intent) {
        String notificationDetailsJson = intent.getStringExtra(CKSMSPlugin.NOTIFICATION_DETAILS);
        if (StringUtils.isNullOrEmpty(notificationDetailsJson)) {
            // This logic is needed for apps that used the plugin prior to 0.3.4
            Notification notification = intent.getParcelableExtra("notification");
            notification.when = System.currentTimeMillis();
            int notificationId = intent.getIntExtra("notification_id",
                    0);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(notificationId, notification);
            boolean repeat = intent.getBooleanExtra("repeat", false);
            if (!repeat) {
                CKSMSPlugin.removeNotificationFromCache(context, notificationId);
            }
        } else {
            Gson gson = CKSMSPlugin.buildGson();
            Type type = new TypeToken<NotificationDetails>() {
            }.getType();
            NotificationDetails notificationDetails = gson.fromJson(notificationDetailsJson, type);
            CKSMSPlugin.showNotification(context, notificationDetails);
            if (notificationDetails.scheduledNotificationRepeatFrequency != null) {
                CKSMSPlugin.zonedScheduleNextNotification(context, notificationDetails);
            } else if (notificationDetails.matchDateTimeComponents != null) {
                CKSMSPlugin.zonedScheduleNextNotificationMatchingDateComponents(context, notificationDetails);
            } else if (notificationDetails.repeatInterval != null) {
                CKSMSPlugin.scheduleNextRepeatingNotification(context, notificationDetails);
            } else {
                CKSMSPlugin.removeNotificationFromCache(context, notificationDetails.id);
            }
        }

    }

}
