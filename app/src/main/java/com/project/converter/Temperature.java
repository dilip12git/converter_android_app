package com.project.converter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Temperature extends AppCompatActivity {
    private EditText celcius, fahrenheit, Kcelcius, kelvin;
    private boolean isUpdating = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempreture);

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));

        celcius = findViewById(R.id.celcius);
        fahrenheit = findViewById(R.id.fahrenheit);
        Kcelcius = findViewById(R.id.kcl);
        kelvin = findViewById(R.id.kelvin);

        addTextWatcher(celcius);
        addTextWatcher(fahrenheit);
        addTextWatcher(Kcelcius);
        addTextWatcher(kelvin);
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

        if (editText == celcius) {
            double fh=(value*1.8)+32;
            updateText(fahrenheit, fh);
        } else if (editText == fahrenheit) {
            double cl = (value-32)*0.555;
            updateText(celcius, cl);
        } else if (editText == Kcelcius) {
            double kel = value+273.15;
            updateText(kelvin, kel);
        } else if (editText == kelvin) {
            double kcl = value-273.15;
            updateText(Kcelcius, kcl);
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

        if (editText == celcius) {
            fahrenheit.setText("");
        } else if (editText == fahrenheit) {
            celcius.setText("");
        } else if (editText == Kcelcius) {
            kelvin.setText("");
        } else if (editText == kelvin) {
            Kcelcius.setText("");
        }

        isUpdating = false;
    }
}
