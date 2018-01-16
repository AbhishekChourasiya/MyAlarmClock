package com.example.rishi.myalarmclock;

/**
 * Created by AbhishekKumar on 1/13/2018.
 */


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class AlarmReciever extends WakefulBroadcastReceiver {

    Ringtone ringtone;


    @Override
    public void onReceive(final Context context, Intent intent) {
        //this will update the UI with message
        MainActivity inst = MainActivity.instance();

        if(MainActivity.x == Calendar.getInstance().getTimeInMillis()){
            inst.setAlarmText("Class A starting soon");
            Log.d("AlarmService", "Alarm On "+MainActivity.x);

        }else if(MainActivity.x*60000*1 == Calendar.getInstance().getTimeInMillis()) {
            inst.setAlarmText("Class B starting soon");

        }else if(MainActivity.x*60000*2 == Calendar.getInstance().getTimeInMillis()) {
            inst.setAlarmText("Class C starting soon");

        }else if(MainActivity.x*60000*3 == Calendar.getInstance().getTimeInMillis()) {
            inst.setAlarmText("Class D starting soon");

        }else if(MainActivity.x*60000*4 == Calendar.getInstance().getTimeInMillis()) {
            inst.setAlarmText("Classes over, enjoy :)");
        }

        //this will sound the alarm tone
        //this will sound the alarm once, if you wish to
        //raise alarm in loop continuously then use MediaPlayer and setLooping(true)

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        }



        //ringtone = RingtoneManager.getRingtone(context, alarmUri);

        //ringtone.play();

        //Hey rishi, if you want to add media file and repeat it in a loop kindly copy the file to res/raw folder and change the bellow attributes
        final MediaPlayer mediaPlayer = MediaPlayer.create(context, alarmUri);
        //mediaPlayer.setLooping(true);
        mediaPlayer.start();

        //After 1s stop the alarm
        // You can adjust the time depending upon your requirement.
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //if (ringtone!=null)
                //ringtone.stop();
                mediaPlayer.stop();
            }
        }, 10000);

        //this will send a notification message
        ComponentName comp = new ComponentName(context.getPackageName(),
                AlarmService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}