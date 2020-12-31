package com.example.dashui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DashActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView usernameTxt;
    private String username;
    private RelativeLayout logout;

    private SharedPreferences pref;
    String prefName = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        pref = getSharedPreferences(prefName, MODE_PRIVATE);
        if (pref.contains("username")){
            username = pref.getString("username", null);
            usernameTxt = findViewById(R.id.userName);
            usernameTxt.setText("Hi, " + username);
        } else {
            startActivity(new Intent(DashActivity.this, MainActivity.class));
            finish();
        }

        logout = findViewById(R.id.dashOption4);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dashOption4:
                pref = getSharedPreferences(prefName, MODE_PRIVATE);
                pref.edit().remove("username");
                startActivity(new Intent(DashActivity.this, MainActivity.class));
                finish();
                break;
            default:
                break;
        }
    }
}