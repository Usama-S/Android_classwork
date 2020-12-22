package com.example.notifications;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mailSrc, passSrc;
    private Button submitBtn;


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submitBtn:
                String mail = mailSrc.getText().toString();
                String pass = passSrc.getText().toString();

                if (mail.equals("admin@gmail.com") && pass.equals("abc123")){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        getLoginNotification();
                    }
                }
                break;
            default:
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mailSrc = findViewById(R.id.mailTxt);
        passSrc = findViewById(R.id.passTxt);
        submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(this);
    }

    public void getLoginNotification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("MyId", "MyId", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Intent openDash = new Intent(MainActivity.this, DashActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, openDash, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyId");
        builder.setContentTitle("Account Logged In")
                .setContentText("You have been logged in to the app.\nClick here to go to Dashboard")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_baseline_login_24);

        NotificationManager notificationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(101, builder.build());
    }

}