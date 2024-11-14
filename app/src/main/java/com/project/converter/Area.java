package com.project.converter;

import android.annotation.SuppressLint;
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

public class Area extends AppCompatActivity {
    private EditText kmsq, msq, Ftmsq, ftsq, acre, aftsq,hectare,hmsq;
    private boolean isUpdating = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));

        kmsq = findViewById(R.id.kmsq);
        msq = findViewById(R.id.sqm);
        Ftmsq = findViewById(R.id.ftsqm);
        ftsq = findViewById(R.id.ftsq);
        acre = findViewById(R.id.acre);
        aftsq = findViewById(R.id.aftsq);
        hectare = findViewById(R.id.hectare);
        hmsq = findViewById(R.id.hmsq);

        addTextWatcher(kmsq);
        addTextWatcher(msq);
        addTextWatcher(Ftmsq);
        addTextWatcher(ftsq);
        addTextWatcher(acre);
        addTextWatcher(aftsq);
        addTextWatcher(hectare);
        addTextWatcher(hmsq);
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

        if (editText == kmsq) {
            double ms = value * 1000000;
            updateText(msq, ms);
        } else if (editText == msq) {
            double kms = value / 1000000;
            updateText(kmsq, kms);
        } else if (editText == Ftmsq) {
            double ftms = value * 10.764;
            updateText(ftsq, ftms);
        } else if (editText == ftsq) {
            double fts = value / 10.764;
            updateText(Ftmsq, fts);
        } else if (editText == acre) {
            double aft = value * 43560;
            updateText(aftsq, aft);
        } else if (editText == aftsq) {
            double acr = value / 43560;
            updateText(acre, acr);
        }else if (editText == hectare) {
            double hms = value * 10000;
            updateText(hmsq, hms);
        }else if (editText == hmsq) {
            double hec = value / 10000;
            updateText(hectare, hec);
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

        if (editText == kmsq) {
            msq.setText("");
        } else if (editText == msq) {
            kmsq.setText("");
        } else if (editText == Ftmsq) {
            ftsq.setText("");
        } else if (editText == ftsq) {
            Ftmsq.setText("");
        } else if (editText == acre) {
            aftsq.setText("");
        } else if (editText == aftsq) {
            acre.setText("");
        }else if (editText == hectare) {
            hmsq.setText("");
        } else if (editText == hmsq) {
            hectare.setText("");
        }

        isUpdating = false;
    }
}

