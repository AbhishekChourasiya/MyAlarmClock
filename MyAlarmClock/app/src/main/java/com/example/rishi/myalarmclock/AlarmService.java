package com.example.rishi.myalarmclock;

/**
 * Created by AbhishekKumar on 1/13/2018.
 */


        import android.app.IntentService;
        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.content.Context;
        import android.content.Intent;
        import android.media.MediaPlayer;
        import android.os.Handler;
        import android.support.v4.app.NotificationCompat;
        import android.util.Log;

        import java.lang.reflect.Constructor;
        import java.lang.reflect.Field;
        import java.lang.reflect.Method;
        import java.util.Calendar;


public class AlarmService extends IntentService {
    private NotificationManager alarmNotificationManager;




    public AlarmService() {
        super("AlarmService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        if(MainActivity.x == Calendar.getInstance().getTimeInMillis()){
            sendNotification("Class A starting soon");


        }else if(MainActivity.x*60000*1 == Calendar.getInstance().getTimeInMillis()) {
            sendNotification("Class B starting soon");

        }else if(MainActivity.x*60000*2 == Calendar.getInstance().getTimeInMillis()) {
            sendNotification("Class C starting soon");

        }else if(MainActivity.x*60000*3 == Calendar.getInstance().getTimeInMillis()) {
            sendNotification("Class D starting soon");

        }else if(MainActivity.x*60000*4 == Calendar.getInstance().getTimeInMillis()) {
            sendNotification("Classes Over, Enjoy :)");
        }
    }

    private void sendNotification(String msg) {
        Log.d("AlarmService", "Preparing to send notification...: " + msg);
        alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(
                this).setContentTitle("Alarm").setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);


        alamNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1, alamNotificationBuilder.build());
        Log.d("AlarmService", "Notification sent.");
    }
}