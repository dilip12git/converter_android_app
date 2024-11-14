package com.project.converter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Time extends AppCompatActivity {
    private EditText Seconds, minutes, hours, year, week, days, Yweeks,hmins;
    private boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));

        Seconds = findViewById(R.id.sec);
        minutes = findViewById(R.id.min);
        hours = findViewById(R.id.hrs);
        hmins = findViewById(R.id.hmin);
        week = findViewById(R.id.weeks);
        days = findViewById(R.id.day);
        year = findViewById(R.id.yrs);
        Yweeks = findViewById(R.id.yk);

        addTextWatcher(Seconds);
        addTextWatcher(minutes);

        addTextWatcher(hours);
        addTextWatcher(hmins);
        addTextWatcher(week);
        addTextWatcher(days);
        addTextWatcher(year);
        addTextWatcher(Yweeks);
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

        if (editText == Seconds) {
            double mn = value /60;
            updateText(minutes, mn);
        } else if (editText == minutes) {
            double seco = value * 60;
            updateText(Seconds, seco);
        } else if (editText == hours) {
            double hmin = value * 60;
            updateText(hmins, hmin);
        } else if (editText == hmins) {
            double hr = value / 60;
            updateText(hours, hr);
        } else if (editText == week) {
            double dy = value * 7;
            updateText(days, dy);
        } else if (editText == days) {
            double meters = value / 7;
            updateText(week, meters);
        } else if (editText == year) {
            double yw = value * 52.14;
            updateText(Yweeks, yw);
        } else if (editText == Yweeks) {
            double yer = value / 52.14;
            updateText(year, yer);
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

        if (editText == Seconds) {
            minutes.setText("");
        } else if (editText == minutes) {
            Seconds.setText("");
        } else if (editText == hours) {
            hmins.setText("");
        } else if (editText == hmins) {
            hours.setText("");
        } else if (editText == week) {
            days.setText("");
        } else if (editText == days) {
            week.setText("");
        }else if (editText == year) {
            Yweeks.setText("");
        } else if (editText == Yweeks) {
            year.setText("");
        }

        isUpdating = false;
    }
}


