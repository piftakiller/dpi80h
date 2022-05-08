package com.istvanbalint.eletbiztositas;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;
public class Notification {
    private static final String CHANNEL_ID = "notification_channel";
    private final int NOTIFICATION_ID = 0;

    private NotificationManager mManager;
    private Context mContext;


    public Notification(Context context) {
        this.mContext = context;
        this.mManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        createChannel();


    }

    private void createChannel() {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) {
            return;
        }

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                "Notification",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLightColor(5);
        channel.setDescription("Értesítés!");
        this.mManager.createNotificationChannel(channel);

    }

    public void send(String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, CHANNEL_ID).setContentTitle("Életbiztosítás").setContentText(message).setSmallIcon(R.drawable.common_google_signin_btn_text_dark_normal);
        this.mManager.notify(NOTIFICATION_ID, builder.build());
    }
}
