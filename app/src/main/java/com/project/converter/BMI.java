package com.project.converter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class BMI extends AppCompatActivity {
    private EditText weight, height;
    private TextView moon, bmi, tips;
    private Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));
        initializeViews();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBMI();
            }
        });
    }

    // Method to initialize the views
    private void initializeViews() {
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        moon = findViewById(R.id.moon);
        bmi = findViewById(R.id.bmi);
        tips = findViewById(R.id.tips);
        button = findViewById(R.id.calc);
    }

    private void calculateBMI() {
        String wt = weight.getText().toString().trim();
        String ht = height.getText().toString().trim();

        if (wt.isEmpty() || ht.isEmpty()) {
            Toast.makeText(BMI.this, "Please enter a valid value", Toast.LENGTH_SHORT).show();
        } else {
            try {
                double weightValue = Double.parseDouble(wt);
                double heightValueInInches = Double.parseDouble(ht);

                // Convert height from inches to meters
                double heightValueInMeters = heightValueInInches * 0.0254;

                // Calculate BMI
                double calculatedBMI = weightValue / (heightValueInMeters * heightValueInMeters);
                bmi.setText(String.format("%.2f", calculatedBMI));

                // Calculate moon weight and display tip
                double moonWeight = weightValue / 6;
                moon.setText(String.format(" %.2f Kg",moonWeight));

                tips.setText("So you are not fat, you're just on the wrong planet! 😂");
            } catch (NumberFormatException e) {
                Toast.makeText(BMI.this, "Invalid input format. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
