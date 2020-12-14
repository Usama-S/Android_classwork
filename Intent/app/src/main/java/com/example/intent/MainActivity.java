package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button submitBtn;
    private EditText mailSrc, passSrc;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submitBtn:
                mailSrc = findViewById(R.id.emailTxt);
                passSrc = findViewById(R.id.passTxt);
                if (mailSrc.getText().toString().equals("admin@gmail.com") && passSrc.getText().toString().equals("abc123")) {
                    Intent intent = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(intent);
                }
                else {
                    Toast toast = Toast.makeText(MainActivity.this, "Invalid Login!", Toast.LENGTH_SHORT);
                    toast.show();
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

        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);
    }
}