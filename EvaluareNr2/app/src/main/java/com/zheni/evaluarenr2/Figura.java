package com.zheni.evaluarenr2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Patrat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patrat);
        TextView perimeterTextView = findViewById(R.id.perimeterTextView); // Add this TextView in your layout
        TextView areaTextView = findViewById(R.id.areaTextView); // Add this TextView in your layout

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            double perimeter = extras.getDouble("perimeter", 0);
            double area = extras.getDouble("area", 0);

            perimeterTextView.setText(String.format("Perimeter: %.2f", perimeter));
            areaTextView.setText(String.format("Area: %.2f", area));
        }
    }
}