package com.l19_pushnotification;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by ZOG on 9/8/2015.
 */
public class MyGcmListenerService extends GcmListenerService {

    private MainActivity mMainActivity;

    public void setMainActivity(MainActivity _MainActivity) {
        this.mMainActivity = _MainActivity;
    }

    @Override
    public void onMessageReceived(String from, Bundle data) {
        Log.d(MainActivity.TAG, "Got message: " + data.getString("message"));
        showNotification(this, data);
        saveNotification(this, data);
    }

    public void showNotification(Context context, final Bundle bundle) {
        String message = bundle.getString("message");
        String title = bundle.getString("title");
        String subtitle = bundle.getString("subtitle");
        String tickerText = bundle.getString("tickerText");
        int vibrate = Integer.valueOf(bundle.getString("vibrate"));
        int sound = Integer.valueOf(bundle.getString("sound"));

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle(title)
                .setSubText(subtitle)
                .setContentText(message)
                .setTicker(tickerText)
                .setAutoCancel(true);

        if (vibrate == 1) builder.setVibrate(new long[]{0, 200, 200, 400});
        if (sound == 1) builder.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.smb_jump_small));

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1234, builder.build());
    }

    private void saveNotification(Context context, final Bundle bundle){

        Log.d(MainActivity.TAG, "#" + mMainActivity.toString());

        ReceivedMessage receivedMessage = new ReceivedMessage(
                mMainActivity.messagesCounter,
                bundle.getString("message"),
                bundle.getString("title"),
                bundle.getString("subtitle"),
                bundle.getString("tickerText"),
                Integer.valueOf(bundle.getString("vibrate")),
                Integer.valueOf(bundle.getString("sound"))
        );
    }

    public class MyBinder extends Binder {
        public MyGcmListenerService getService() {
            return MyGcmListenerService.this;
        }
    }
}
