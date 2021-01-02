package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> cities = new ArrayList<>();
        cities.add("Karachi");
        cities.add("Lahore");
        cities.add("Islamabad");
        cities.add("Peshawar");
        cities.add("Quetta");
        cities.add("Sahiwal");

        listView = findViewById(R.id.listView);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item, R.id.txtItem, cities);

        listView.setAdapter(adapter);
    }
}