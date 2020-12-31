package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DashActivity extends AppCompatActivity {

    private TextView txtSelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        txtSelectedItem = findViewById(R.id.selectedItem);
        txtSelectedItem.setText(getIntent().getExtras().getString("selectedItem"));
    }
}