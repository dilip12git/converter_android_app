package com.project.converter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;

public class SCGPA extends AppCompatActivity {

    private LinearLayout subjectLayout;
    private Button addSubjectsButton, calculateButton;
    private EditText numberOfSubjectsInput;
    private ArrayList<Subject> subjects = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scgpa);
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));

        subjectLayout = findViewById(R.id.subjectLayout);
        addSubjectsButton = findViewById(R.id.addSubjectsButton);
        calculateButton = findViewById(R.id.calculateButton);
        numberOfSubjectsInput = findViewById(R.id.numberOfSubjectsInput);

        addSubjectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numSubjectsStr = numberOfSubjectsInput.getText().toString();
                if (!numSubjectsStr.isEmpty()) {
                    int numSubjects = Integer.parseInt(numSubjectsStr);
                    if (numSubjects > 0) {
                        addSubjects(numSubjects);
                    } else {
                        showToast("Please enter a positive number of subjects");
                    }
                } else {
                    showToast("Please enter number of subjects");
                }
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSCGPA();
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void addSubjects(int numberOfSubjects) {
        subjectLayout.removeAllViews();
        subjects.clear();

        for (int i = 0; i < numberOfSubjects; i++) {
            LinearLayout subjectContainer = new LinearLayout(this);
            subjectContainer.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout.LayoutParams inputParams = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 1
            );
            inputParams.setMargins(8, 10, 8, 0);

            EditText gradeInput = new EditText(this);
            gradeInput.setHint("Grade Point (Sub " + (i + 1) + ")");
            gradeInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            gradeInput.setTextSize(12);
            gradeInput.setBackgroundResource(R.drawable.card_border);
            gradeInput.setPadding(30,30,30,30);
            gradeInput.setHintTextColor(R.color.dark);
            gradeInput.setTextColor(getResources().getColor(android.R.color.black));
            gradeInput.setLayoutParams(inputParams);
            subjectContainer.addView(gradeInput);

            EditText creditInput = new EditText(this);
            creditInput.setHint("Credit (Sub " + (i + 1) + ")");
            creditInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            creditInput.setTextSize(12);
            creditInput.setBackgroundResource(R.drawable.card_border);
            creditInput.setPadding(30,30,30,30);
            creditInput.setHintTextColor(R.color.dark);
            creditInput.setTextColor(getResources().getColor(android.R.color.black));
            creditInput.setLayoutParams(inputParams);
            subjectContainer.addView(creditInput);

            subjectLayout.addView(subjectContainer);

            subjects.add(new Subject(gradeInput, creditInput));
        }
    }

    private void calculateSCGPA() {
        double totalWeightedGP = 0.0;
        int totalCredits = 0;

        for (Subject subject : subjects) {
            if (!subject.gradeInput.getText().toString().isEmpty() && !subject.creditInput.getText().toString().isEmpty()) {
                double gradePoint = Double.parseDouble(subject.gradeInput.getText().toString());
                int credits = Integer.parseInt(subject.creditInput.getText().toString());
                totalWeightedGP += (gradePoint * credits);
                totalCredits += credits;
            }
        }

        if (totalCredits > 0) {
            double scgpa = totalWeightedGP / totalCredits;
            double percentage = scgpa * 9.5;

            showResultDialog(scgpa, percentage);
        } else {
            showToast("No data to calculate SCGPA");
        }
    }

    private class Subject {
        EditText gradeInput;
        EditText creditInput;

        public Subject(EditText gradeInput, EditText creditInput) {
            this.gradeInput = gradeInput;
            this.creditInput = creditInput;
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("DefaultLocale")
    private void showResultDialog(double scgpa, double percentage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("SCGPA Calculation Result");
        builder.setMessage("SCGPA: " + String.format("%.2f", scgpa) + "\nPercentage: " + String.format("%.2f", percentage) + "%");
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}
