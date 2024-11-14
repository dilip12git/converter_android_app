package com.project.converter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Length extends AppCompatActivity {
    private EditText inchCmInch, inchCmCcm, footInch, footInchInch, meterCmMeter, meterCmCm;
    private boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));

        inchCmInch = findViewById(R.id.input_inch);
        inchCmCcm = findViewById(R.id.input_cm);
        footInch = findViewById(R.id.inputfoot);
        footInchInch = findViewById(R.id.inputfootinch);
        meterCmMeter = findViewById(R.id.input_meter);
        meterCmCm = findViewById(R.id.inputmeter_cm);

        addTextWatcher(inchCmInch);
        addTextWatcher(inchCmCcm);
        addTextWatcher(footInch);
        addTextWatcher(footInchInch);
        addTextWatcher(meterCmMeter);
        addTextWatcher(meterCmCm);
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

        if (editText == inchCmInch) {
            double cm = value * 2.54;
            updateText(inchCmCcm, cm);
        } else if (editText == inchCmCcm) {
            double inch = value / 2.54;
            updateText(inchCmInch, inch);
        } else if (editText == footInch) {
            double inches = value * 12;
            updateText(footInchInch, inches);
        } else if (editText == footInchInch) {
            double feet = value / 12;
            updateText(footInch, feet);
        } else if (editText == meterCmMeter) {
            double cm = value * 100;
            updateText(meterCmCm, cm);
        } else if (editText == meterCmCm) {
            double meters = value / 100;
            updateText(meterCmMeter, meters);
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

        if (editText == inchCmInch) {
            inchCmCcm.setText("");
        } else if (editText == inchCmCcm) {
            inchCmInch.setText("");
        } else if (editText == footInch) {
            footInchInch.setText("");
        } else if (editText == footInchInch) {
            footInch.setText("");
        } else if (editText == meterCmMeter) {
            meterCmCm.setText("");
        } else if (editText == meterCmCm) {
            meterCmMeter.setText("");
        }

        isUpdating = false;
    }
}
