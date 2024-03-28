package com.zheni.evaluarenr2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                editText2.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
                editText3.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calculareBtn.setOnClickListener(v -> {
            double perimetru = 0.0;
            double aria = 0.0;
            try {
                double latura1 = Double.parseDouble(editText1.getText().toString());
                String figura = spinnerFigures.getSelectedItem().toString();
                switch (figura) {
                    case "Patrat":
                        perimetru = 4 * latura1;
                        aria = latura1 * latura1;
                        break;
                    case "Triunghi":
                        double latura2 = Double.parseDouble(editText2.getText().toString());
                        double latura3 = Double.parseDouble(editText3.getText().toString());
                        if(!isValidTriangle(latura1, latura2, latura3)){
                            textView.setText("Triunghi invalid!");
                            return;
                        }
                        perimetru = calculatePerimeter(latura1, latura2, latura3);
                        aria = calculateArea(latura1, latura2, latura3);
                        break;
                }
                Intent intent = new Intent(MainActivity.this, Figura.class);
                intent.putExtra("perimeter", perimetru);
                intent.putExtra("area", aria);
                intent.putExtra("shape", figura.toLowerCase());
                startActivity(intent);
            }
            catch(NumberFormatException ex){
                textView.setText("A aparut o eroare la citirea datelor!");
            }
        });
    }
    public boolean isValidTriangle(double a, double b, double c) {
        return a + b > c && a + c > b && b + c > a;
    }

    public double calculatePerimeter(double a, double b, double c) {
        return a + b + c;
    }

    public double calculateArea(double a, double b, double c) {
        // Formula lu Heron
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}