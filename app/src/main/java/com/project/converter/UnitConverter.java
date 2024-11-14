package com.project.converter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


public class UnitConverter extends AppCompatActivity {
private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_converter);
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));

        listView=findViewById(R.id.list_item);
        String[] listItem={
                "Length",
                "Speed",
                "Volume",
                "Area",

        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_layout, R.id.list_item_text, listItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent i0 = new Intent(UnitConverter.this, Length.class);
                        startActivity(i0);
                        break;
                    case 1:
                        Intent i1 = new Intent(UnitConverter.this, Speed.class);
                        startActivity(i1);
                        break;
                    case 2:
                        Intent i2 = new Intent(UnitConverter.this, Volume.class);
                        startActivity(i2);
                        break;
                    case 3:
                        Intent i3 = new Intent(UnitConverter.this, Area.class);
                        startActivity(i3);
                        break;
                }
            }
        });

    }
}