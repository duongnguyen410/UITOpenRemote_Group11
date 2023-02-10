package com.example.uitopenremote_group11;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Calendar;

public class SetAlarmActivity extends AppCompatActivity implements View.OnClickListener {

    private TimePicker tp;
    private Button setAlarm_Button;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        getWindow().setStatusBarColor(ContextCompat.getColor(SetAlarmActivity.this, R.color.my_orange));

        setAlarm_Button = findViewById(R.id.setAlarm_Button);
        tp = findViewById(R.id.time_picker);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setAlarm_Button.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                tp.getHour(),
                tp.getMinute(), 0);
        Alarm_set(cal.getTimeInMillis());
    }

    private void Alarm_set(long timeInMillis){
        AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,Alarm_BR.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Your alarm is Set", Toast.LENGTH_LONG).show();
    }
}