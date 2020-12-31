package com.example.dashui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button loginBtn;
    private EditText nameSrc;

    private SharedPreferences pref;
    String prefName = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.loginBtn);
        nameSrc = findViewById(R.id.nameTxt);

        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginBtn:
                String name = nameSrc.getText().toString();
                if (name.isEmpty()){
                    Toast.makeText(this, "Name is Required!", Toast.LENGTH_SHORT).show();
                } else {
                    pref = getSharedPreferences(prefName, MODE_PRIVATE);
                    pref.edit().putString("username", name).commit();
                    startActivity(new Intent(MainActivity.this, DashActivity.class));
                    finish();
                }
                break;
            default:
                break;
        }
    }
}