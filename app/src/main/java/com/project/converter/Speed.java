package com.project.converter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


public class Speed extends AppCompatActivity {
    private EditText kmph, mph, mps, mphs;
    private boolean isUpdating = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));

        kmph = findViewById(R.id.input_kmph);
        mph = findViewById(R.id.input_mph);
        mps = findViewById(R.id.mps);
        mphs = findViewById(R.id.kmh);

        addTextWatcher(kmph);
        addTextWatcher(mph);
        addTextWatcher(mps);
        addTextWatcher(mphs);
    }

    private void addTextWatcher(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) return;

                String typedValue = s.toString();
                if (!typedValue.isEmpty()) {
                    try {
                        double value = Double.parseDouble(typedValue);
                        performConversion(editText, value);
                    } catch (NumberFormatException ignored) {}
                }
                else {
                    clearCorrespondingFields(editText);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void performConversion(EditText editText, double value) {
        isUpdating = true;

        if (editText == kmph) {
            double mh = value * 0.621371;
            updateText(mph, mh);
        } else if (editText == mph) {
            double kmp = value / 1.60934;
            updateText(kmph, kmp);
        } else if (editText == mps) {
            double mps = value * 3.6;
            updateText(mphs, mps);
        } else if (editText == mphs) {
            double mphs = value / 3.6;
            updateText(mps, mphs);
        }
        isUpdating = false;
    }

    private void updateText(EditText editText, double value) {
        String newText = String.valueOf(value);
        if (!editText.getText().toString().equals(newText)) {
            editText.setText(newText);
        }

    }
    private void clearCorrespondingFields(EditText editText) {
        isUpdating = true;

        if (editText == kmph) {
            mph.setText("");
        } else if (editText == mph) {
            kmph.setText("");
        } else if (editText == mps) {
            mphs.setText("");
        } else if (editText == mphs) {
            mps.setText("");
        }

        isUpdating = false;
    }



}