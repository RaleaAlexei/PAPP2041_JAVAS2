package com.zheni.evaluarenr2;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class Figura extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.figura);

        TextView perimeterTextView = findViewById(R.id.perimeterTextView);
        TextView areaTextView = findViewById(R.id.areaTextView);
        View shapeView = findViewById(R.id.shapeView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            double perimeter = extras.getDouble("perimeter", 0);
            double area = extras.getDouble("area", 0);
            String shape = extras.getString("shape", "");
            Resources resources = getResources();
            int resource = resources.getIdentifier(shape, "drawable", getPackageName());
            if(resource == 0){
                return;
            }
            shapeView.setBackground(ResourcesCompat.getDrawable(resources, resource, null));
            perimeterTextView.setText(String.format("Perimeter: %.2f", perimeter));
            areaTextView.setText(String.format("Area: %.2f", area));
        }
    }
}