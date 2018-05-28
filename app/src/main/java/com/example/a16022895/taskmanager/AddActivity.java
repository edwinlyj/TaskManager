package com.example.a16022895.taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    int reqCode = 12345;
    EditText etName, etDetail, etTime;
    Button btCancel, btAddTask;
    Task data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etName = (EditText)findViewById(R.id.etAddName);
        etDetail = (EditText)findViewById(R.id.etAddDesc);
        btAddTask = (Button)findViewById(R.id.btnAddTask);
        btCancel = (Button)findViewById(R.id.btnCancel);
        etTime = (EditText)findViewById(R.id.etTime);

        btAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = etName.getText().toString();
                String detail = etDetail.getText().toString();
                String time = etTime.getText().toString();
                int time2 = Integer.parseInt(time);

                DBHelper db = new DBHelper(AddActivity.this);
                db.getWritableDatabase();
                db.insertTask(name, detail);

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, time2);

                Intent intent = new Intent(AddActivity.this,
                        ScheduledNotificationReceiver.class);

                intent.putExtra("name", name);
                intent.putExtra("detail", detail);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        AddActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);

                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
