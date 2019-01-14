package com.core.app.util

import android.content.Context
import androidx.core.app.NotificationCompat

import com.urbanairship.push.PushMessage
import com.urbanairship.push.notifications.DefaultNotificationFactory

class AppUrbanAirshipNotificationFactory(context: Context) : DefaultNotificationFactory(context) {

    override fun extendBuilder(builder: NotificationCompat.Builder, message: PushMessage, notificationId: Int): NotificationCompat.Builder {
        //        if (SDKUtils.hasLollipop()) {
        //            builder.setSmallIcon(R.mipmap.ic_notification);
        //        } else {
        //            builder.setSmallIcon(R.mipmap.ic_launcher);
        //        }
        return builder
    }

}
