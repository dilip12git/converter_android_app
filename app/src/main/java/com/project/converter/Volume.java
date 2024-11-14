package com.project.converter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


public class Volume extends AppCompatActivity {
    private EditText literLtr, literMl, cubicMeterM, cubicMeterLtr, gallonG, gallonLtr;
    private boolean isUpdating = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));

        literLtr = findViewById(R.id.input_liter);
        literMl = findViewById(R.id.input_ml);
        cubicMeterM = findViewById(R.id.cubic_meter);
        cubicMeterLtr = findViewById(R.id.input_cubic_ltr);
        gallonG = findViewById(R.id.input_gallon);
        gallonLtr = findViewById(R.id.input_gltr);

        addTextWatcher(literLtr);
        addTextWatcher(literMl);
        addTextWatcher(cubicMeterM);
        addTextWatcher(cubicMeterLtr);
        addTextWatcher(gallonG);
        addTextWatcher(gallonLtr);
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

        if (editText == literLtr) {
            double ml = value * 1000;
            updateText(literMl, ml);
        } else if (editText == literMl) {
            double ltr = value / 1000;
            updateText(literLtr, ltr);
        } else if (editText == cubicMeterM) {
            double ltr = value * 1000;
            updateText(cubicMeterLtr, ltr);
        } else if (editText == cubicMeterLtr) {
            double cmltr = value / 1000;
            updateText(cubicMeterM, cmltr);
        } else if (editText == gallonG) {
            double gLtr = value * 3.785;
            updateText(gallonLtr, gLtr);
        } else if (editText == gallonLtr) {
            double gG = value / 3.758;
            updateText(gallonG, gG);
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

        if (editText == literLtr) {
            literMl.setText("");
        } else if (editText == literMl) {
            literLtr.setText("");
        } else if (editText == cubicMeterLtr) {
            cubicMeterM.setText("");
        } else if (editText == cubicMeterM) {
            cubicMeterLtr.setText("");
        } else if (editText == gallonG) {
            gallonLtr.setText("");
        } else if (editText == gallonLtr) {
            gallonG.setText("");
        }

        isUpdating = false;
    }
}
