package com.zheni.evaluarenr2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinnerFigures = findViewById(R.id.spinnerFigures);
        TextView textView = (TextView) findViewById(R.id.textView1);
        EditText editText1 = (EditText) findViewById(R.id.editText1);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        EditText editText3 = (EditText) findViewById(R.id.editText3);
        Button calculareBtn = (Button) findViewById(R.id.calculateButton);

        spinnerFigures.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    editText1.setHint("Raza");
                    editText2.setVisibility(View.GONE);
                    editText3.setVisibility(View.GONE);
                } else if (position == 1) {
                    editText1.setHint("Baza mare");
                    editText2.setVisibility(View.VISIBLE);
                    editText3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calculareBtn.setOnClickListener(v -> {
            double perimetru = 0.0;
            double aria = 0.0;
            try {
                double raza_bazaMare = Double.parseDouble(editText1.getText().toString());
                String figura = spinnerFigures.getSelectedItem().toString();
                switch (figura) {
                    case "Cerc":
                        perimetru = 2 * Math.PI * raza_bazaMare;
                        aria = Math.PI * raza_bazaMare * raza_bazaMare;
                        break;
                    case "Trapez":
                        double bazaMica = Double.parseDouble(editText2.getText().toString());
                        double inaltimea = Double.parseDouble(editText3.getText().toString());
                        perimetru = 2 * (((raza_bazaMare - bazaMica) / 2) * ((raza_bazaMare - bazaMica) / 2) + inaltimea * inaltimea) + raza_bazaMare + bazaMica;
                        aria = (raza_bazaMare + bazaMica) / 2 * inaltimea;
                        break;
                }
                textView.setText(String.format("Perimetrul %s: %.2f, Aria: %.2f", figura.toLowerCase(), perimetru, aria));
            }
            catch(NumberFormatException ex){
                textView.setText("A aparut o eroare la citirea datelor!");
            }
        });
    }
}