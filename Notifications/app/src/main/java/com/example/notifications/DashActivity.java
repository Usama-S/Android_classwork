package com.example.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashActivity extends AppCompatActivity implements View.OnClickListener {

    private Button logoutBtn, goToWebBtn;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logoutBtn:
                getLogoutNotification();
                break;
            case R.id.webBtn:
                getWebNotification();
                break;
            default:
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        logoutBtn = findViewById(R.id.logoutBtn);
        goToWebBtn = findViewById(R.id.webBtn);

        logoutBtn.setOnClickListener(this);
        goToWebBtn.setOnClickListener(this);
    }

    public void getLogoutNotification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("MyId", "MyId", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Intent openHome = new Intent(DashActivity.this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, openHome, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyId");
        builder.setContentTitle("Account Logged Out")
                .setContentText("You have been logged out of the app.")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_baseline_login_24);

        NotificationManager notificationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(102, builder.build());
    }

    public void getWebNotification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("MyId", "MyId", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Intent openWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, openWeb, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyId");
        builder.setContentTitle("Account Logged In")
                .setContentText("You have been logged in to the app.")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_baseline_login_24);

        NotificationManager notificationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(103, builder.build());
    }
}