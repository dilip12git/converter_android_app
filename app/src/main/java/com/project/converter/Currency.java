package com.project.converter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Currency extends AppCompatActivity {
    EditText etAmount;
    Spinner spinnerFromCurrency, spinnerToCurrency;
    Button btnConvert;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        getWindow().setStatusBarColor(getResources().getColor(R.color.blue));

        etAmount = findViewById(R.id.et_amount);
        spinnerFromCurrency = findViewById(R.id.spinner_from_currency);
        spinnerToCurrency = findViewById(R.id.spinner_to_currency);
        btnConvert = findViewById(R.id.button_convert);
        tvResult = findViewById(R.id.tv_result);


        String[] currencies = {
                "India - INR",
                "Nepal - NPR",
                "Sri Lanka - LKR",
                "United States - USD",
                "Bangladesh - BDT",
        };


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, currencies);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerFromCurrency.setAdapter(adapter);
        spinnerToCurrency.setAdapter(adapter);

        // Conversion button click listener
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private void convertCurrency() {
        String fromCurrency = getCurrencyCode(spinnerFromCurrency.getSelectedItem().toString());
        String toCurrency = getCurrencyCode(spinnerToCurrency.getSelectedItem().toString());
        String amountStr = etAmount.getText().toString();

        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
            return;
        }


        try {
            double amount = Double.parseDouble(amountStr);
            double conversionRate = getConversionRate(fromCurrency, toCurrency);
            double convertedAmount = amount * conversionRate;
            tvResult.setText(String.format("%.2f %s", convertedAmount, toCurrency));
        } catch (IllegalArgumentException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private String getCurrencyCode(String currencyString) {
        return currencyString.split("-")[1].trim();
    }
    private double getConversionRate(String fromCurrency, String toCurrency) {
        if (fromCurrency.equals("USD") && toCurrency.equals("INR")) {
            return 83.00;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("USD")) {
            return 0.012;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("BDT")) {
            return 1.28;
        } else if (fromCurrency.equals("BDT") && toCurrency.equals("INR")) {
            return 0.78;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("BDT")) {
            return 108.00;
        } else if (fromCurrency.equals("BDT") && toCurrency.equals("USD")) {
            return 0.0092;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("NPR")) {
            return 1.60;
        } else if (fromCurrency.equals("NPR") && toCurrency.equals("INR")) {
            return 0.63;
        } else if (fromCurrency.equals("INR") && toCurrency.equals("LKR")) {
            return 1.35;
        } else if (fromCurrency.equals("LKR") && toCurrency.equals("INR")) {
            return 0.74;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("NPR")) {
            return 132.00;
        } else if (fromCurrency.equals("NPR") && toCurrency.equals("USD")) {
            return 0.0076;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("LKR")) {
            return 315.00;
        } else if (fromCurrency.equals("LKR") && toCurrency.equals("USD")) {
            return 0.0032;
        } else if (fromCurrency.equals("LKR") && toCurrency.equals("NPR")) {
            return 1.17;
        } else if (fromCurrency.equals("NPR") && toCurrency.equals("LKR")) {
            return 0.85;
        } else {
            throw new IllegalArgumentException("Conversion rate not available for these currencies.");
        }
    }

}

