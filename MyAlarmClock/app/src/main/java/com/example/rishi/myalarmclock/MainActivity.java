package com.example.rishi.myalarmclock;


        import android.annotation.TargetApi;
        import android.app.AlarmManager;
        import android.app.PendingIntent;
        import android.content.Intent;
        import android.os.Build;
        import android.os.SystemClock;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.TimePicker;

        import android.widget.ToggleButton;

        import java.util.ArrayList;
        import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static MainActivity inst;
    private TextView alarmTextView;
    Calendar calendar = null;
    public static long x;
    public static MainActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) findViewById(R.id.alarmText);

    }

    @TargetApi(Build.VERSION_CODES.M)
    public void onToggleClicked(View view) {
        if (((ToggleButton) view).isChecked()) {
            Log.d("MainActivity", "Alarm On");

            calendar = Calendar.getInstance();


            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            x=calendar.getTimeInMillis();
    
            // context variable contains your `Context`
            AlarmManager mgrAlarm = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
            ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();

            for(int i = 0; i < 4; ++i)
            {
                Intent intent = new Intent(MainActivity.this, AlarmReciever.class);
                // Loop counter `i` is used as a `requestCode`
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, i, intent, 0);
                // Single alarms in 1, 2, ..., 10 minutes (in `i` minutes)
                mgrAlarm.set(AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis()+ 60000 *i,
                        pendingIntent);
                Log.d("MainActivity", "Alarm On "+calendar.getTimeInMillis() +60000*i);
                intentArray.add(pendingIntent);
            }


        } else {
            if(alarmManager!=null)
                alarmManager.cancel(pendingIntent);
            setAlarmText("");
            Log.d("MainActivity", "Alarm Off");

        }
    }

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }

}