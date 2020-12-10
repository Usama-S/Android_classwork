package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submitBtn:
                setContentView(R.layout.activity_main2);
                Window window = getWindow();
                window.setStatusBarColor(Color.parseColor("#200c53"));
                TextView back = findViewById(R.id.caret);
                back.setOnClickListener(this);
                break;
            case R.id.caret:
                setContentView(R.layout.activity_main);
                window = getWindow();
                window.setStatusBarColor(Color.BLACK);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.setStatusBarColor(Color.BLACK);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSubmit = findViewById(R.id.submitBtn);
        btnSubmit.setOnClickListener(this);
    }
}