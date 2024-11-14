package com.project.converter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.splashscreen.SplashScreenViewProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.core.splashscreen.SplashScreen;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListner {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private ArrayList<DataModel> itemList;
    private FloatingActionButton fabBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabBtn=findViewById(R.id.fab);
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, info.class);
                startActivity(i);
            }
        });

        initializeRecyclerView();
        populateItemList();
        setupAdapter();


        splashScreen.setOnExitAnimationListener(SplashScreenViewProvider::remove);
        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));
    }

    private void initializeRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view); // Initialize RecyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }

    private void populateItemList() {
        itemList = new ArrayList<>();
        itemList.add(new DataModel("Unit Converter", R.drawable.math));
        itemList.add(new DataModel("Currency", R.drawable.paisa));
        itemList.add(new DataModel("BMI Calculator", R.drawable.bmi));
        itemList.add(new DataModel("Temperature", R.drawable.temp));
        itemList.add(new DataModel("Time Converter", R.drawable.time));
        itemList.add(new DataModel("Love Calculator", R.drawable.love));
        itemList.add(new DataModel("CGPA", R.drawable.scgpa));
        itemList.add(new DataModel("SCGPA", R.drawable.cgpa));
    }

    private void setupAdapter() {
        adapter = new MyAdapter( itemList, this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(int position) {
        switch (position){
            case 0:
                Intent intent0 = new Intent(this, UnitConverter.class);
                this.startActivity(intent0);
                break;

            case 1:
                Intent intent1=new Intent(this, Currency.class);
                startActivity(intent1);
                break;


            case 2:
                Intent intent2=new Intent(this, BMI.class);
                startActivity(intent2);
                break;

            case 3:
                Intent intent3=new Intent(this, Temperature.class);
                startActivity(intent3);
                break;

            case 4:
                Intent intent4=new Intent(this, Time.class);
                startActivity(intent4);
                break;

            case 5:
                Intent intent5=new Intent(this, LoveCalculator.class);
                startActivity(intent5);
                break;
            case 6:
                Intent intent6=new Intent(this, CGPA.class);
                startActivity(intent6);
                break;
            case 7:
                Intent intent7=new Intent(this, SCGPA.class);
                startActivity(intent7);
                break;
        }

    }
}
