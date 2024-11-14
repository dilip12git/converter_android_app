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
import android.os.Handler;
import android.os.Looper;

public class LoveCalculator extends AppCompatActivity {
    private EditText name1, name2;
    private Button calculateButton;
    private TextView result;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love_calculator);
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setStatusBarColor(getResources().getColor(R.color.red));

        name1 = findViewById(R.id.your_name);
        name2 = findViewById(R.id.your_partner_name);
        calculateButton = findViewById(R.id.btn);
        result = findViewById(R.id.result);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String person1 = name1.getText().toString();
                String person2 = name2.getText().toString();

                // Inside your button click handler
                if (!person1.isEmpty() && !person2.isEmpty()) {
                    int lovePercentage = calculateLovePercentage(person1, person2);
                    startCountdown(lovePercentage, result); // Use countdown animation
                } else {
                    result.setText("");
                    Toast.makeText(LoveCalculator.this, "Enter both names", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int calculateLovePercentage(String name1, String name2) {
        String combinedNames = (name1 + name2).toLowerCase();
        int nameSum = 0;
        for (char c : combinedNames.toCharArray()) {
            nameSum += (int) c;
        }
        int lovePercentage = (nameSum % 100) + 1;
        return lovePercentage;
    }

    private void startCountdown(final int lovePercentage, final TextView result) {
        final Handler handler = new Handler(Looper.getMainLooper()); // Use Looper for newer Android versions
        final int[] currentPercentage = {0}; // Start from 0

        // Runnable to update the text incrementally
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Increment the current percentage
                if (currentPercentage[0] <= lovePercentage) {
                    result.setText(currentPercentage[0] + "%");
                    currentPercentage[0]++;
                    handler.postDelayed(this, 20); // Update every 20ms
                }
            }
        };

        // Start the countdown
        handler.post(runnable);
    }




}