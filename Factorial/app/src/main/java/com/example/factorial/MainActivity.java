package com.example.factorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calc = findViewById(R.id.calcBtn);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText numSrc = findViewById(R.id.numField);
                int num = Integer.parseInt(numSrc.getText().toString());

                int result = 1;
                for (int i = num; i >= 1; i--){
                    result *= i;
                }

                TextView resultView = findViewById(R.id.result);
                resultView.setText("Factorial : " + result);
            }
        });
    }
}