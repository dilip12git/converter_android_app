package com.project.converter;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
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

public class CGPA extends AppCompatActivity {

    private LinearLayout termLayout;
    private Button addTermButton, calculateButton;
    private EditText numberOfTermsInput;
    private ArrayList<Term> terms = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa);
        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));

        termLayout = findViewById(R.id.termLayout);
        addTermButton = findViewById(R.id.addTermButton);
        calculateButton = findViewById(R.id.calculateButton);
        numberOfTermsInput = findViewById(R.id.numberOfTermsInput);

        addTermButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numTermsStr = numberOfTermsInput.getText().toString();
                if (!numTermsStr.isEmpty()) {
                    int numTerms = Integer.parseInt(numTermsStr);
                    if (numTerms > 0) {
                        addTerms(numTerms);
                    } else {
                        showToast("Please enter a positive number of terms");
                    }
                } else {
                    showToast("Please enter number of terms");
                }
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateOverallCGPA();
            }
        });
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    private void addTerms(int numberOfTerms) {
        termLayout.removeAllViews();
        terms.clear();

        for (int t = 0; t < numberOfTerms; t++) {
            TextView termHeader = new TextView(this);
            termHeader.setText("Term " + (t + 1));
            termLayout.addView(termHeader);

            LinearLayout.LayoutParams mrgin = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            mrgin.setMargins(0, 0, 0, 16);

            EditText numSubjectsInput = new EditText(this);
            numSubjectsInput.setHint("No of subjects for Term " + (t + 1));
            numSubjectsInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            numSubjectsInput.setTextSize(14);
            numSubjectsInput.setBackgroundResource(R.drawable.card_border);
            numSubjectsInput.setPadding(30,40,30,40);
            numSubjectsInput.setHintTextColor(R.color.dark);
            numSubjectsInput.setTextColor(getResources().getColor(android.R.color.black));
            numSubjectsInput.setLayoutParams(mrgin);
            termLayout.addView(numSubjectsInput);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            int widthInDp = 150;
            int widthInPx = (int) (widthInDp * getResources().getDisplayMetrics().density);
            params.width = widthInPx;
            params.gravity = Gravity.END;

            Button addSubjectsButton = new Button(this);
            addSubjectsButton.setText("Add Term " + (t + 1));
            addSubjectsButton.setBackgroundResource(R.drawable.card_border);
            addSubjectsButton.setTextColor(getResources().getColor(R.color.red));
            addSubjectsButton.setLayoutParams(params);

            final int currentTerm = t;
            addSubjectsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String numSubjectsStr = numSubjectsInput.getText().toString();
                    if (!numSubjectsStr.isEmpty()) {
                        int numSubjects = Integer.parseInt(numSubjectsStr);
                        if (numSubjects > 0) {
                            addTerm(currentTerm, numSubjects);
                            addSubjectsButton.setEnabled(false); // Disable the button after adding subjects
                        } else {
                            showToast("Please enter a positive number of subjects");
                        }
                    } else {
                        showToast("Please enter number of subjects");
                    }
                }
            });
            termLayout.addView(addSubjectsButton);
        }
    }

    @SuppressLint("ResourceAsColor")
    private void addTerm(int termIndex, int numberOfSubjects) {
        Term term = new Term(numberOfSubjects);
        terms.add(term);

        LinearLayout termContainer = new LinearLayout(this);
        termContainer.setOrientation(LinearLayout.VERTICAL);
        termContainer.setPadding(16, 16, 16, 16);
        termContainer.setBackgroundColor(getResources().getColor(android.R.color.white));

        LinearLayout.LayoutParams termParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        termParams.setMargins(0, 16, 0, 16);
        termContainer.setLayoutParams(termParams);

        TextView termHeader = new TextView(this);
        termHeader.setText("Term " + (termIndex + 1));
        termHeader.setTextSize(18);
        termHeader.setTextColor(getResources().getColor(android.R.color.black));
        termHeader.setPadding(0, 0, 0, 8);
        termContainer.addView(termHeader);

        for (int i = 0; i < numberOfSubjects; i++) {
            LinearLayout subjectLayout = new LinearLayout(this);
            subjectLayout.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout.LayoutParams inputParams = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 1
            );
            inputParams.setMargins(8, 12, 8, 0);

            EditText gradeInput = new EditText(this);
            gradeInput.setHint("Grade Point (Sub " + (i + 1) + ")");

//            design
            gradeInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            gradeInput.setTextSize(12);
            gradeInput.setBackgroundResource(R.drawable.card_border);
            gradeInput.setPadding(30,30,30,30);
            gradeInput.setHintTextColor(R.color.dark);
            gradeInput.setTextColor(getResources().getColor(android.R.color.black));
            gradeInput.setLayoutParams(inputParams);
            subjectLayout.addView(gradeInput);

            EditText creditInput = new EditText(this);
            creditInput.setHint("Credit (Sub " + (i + 1) + ")");
            creditInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            creditInput.setTextSize(12);
            creditInput.setBackgroundResource(R.drawable.card_border);
            creditInput.setPadding(30,30,30,30);
            creditInput.setHintTextColor(R.color.dark);
            creditInput.setTextColor(getResources().getColor(android.R.color.black));
            creditInput.setLayoutParams(inputParams);
            subjectLayout.addView(creditInput);

            termContainer.addView(subjectLayout);

            term.addSubjectInput(gradeInput, creditInput);
        }

        termLayout.addView(termContainer);
    }

    private void calculateOverallCGPA() {
        double totalWeightedGP = 0.0;
        int totalCredits = 0;

        for (Term term : terms) {
            totalWeightedGP += term.getWeightedGradePoints();
            totalCredits += term.getTotalCredits();
        }

        if (totalCredits > 0) {
            double overallCGPA = totalWeightedGP / totalCredits;
            double percentage = overallCGPA * 9.5;

            showResultDialog(overallCGPA, percentage);
        } else {
            showToast("No data to calculate CGPA");
        }
    }

    private class Term {
        private ArrayList<EditText> gradeInputs = new ArrayList<>();
        private ArrayList<EditText> creditInputs = new ArrayList<>();

        public Term(int numberOfSubjects) {
        }

        public void addSubjectInput(EditText gradeInput, EditText creditInput) {
            gradeInputs.add(gradeInput);
            creditInputs.add(creditInput);
        }

        public double getWeightedGradePoints() {
            double weightedGP = 0.0;
            for (int i = 0; i < gradeInputs.size(); i++) {
                if (!gradeInputs.get(i).getText().toString().isEmpty() && !creditInputs.get(i).getText().toString().isEmpty()) {
                    double gradePoint = Double.parseDouble(gradeInputs.get(i).getText().toString());
                    int credits = Integer.parseInt(creditInputs.get(i).getText().toString());
                    weightedGP += (gradePoint * credits);
                }
            }
            return weightedGP;
        }

        public int getTotalCredits() {
            int totalCredits = 0;
            for (EditText creditInput : creditInputs) {
                if (!creditInput.getText().toString().isEmpty()) {
                    totalCredits += Integer.parseInt(creditInput.getText().toString());
                }
            }
            return totalCredits;
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("DefaultLocale")
    private void showResultDialog(double overallCGPA, double percentage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CGPA Calculation Result");
        builder.setMessage("Overall CGPA: " + String.format("%.2f", overallCGPA) + "\nPercentage: " + String.format("%.2f", percentage)+"%");
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}
