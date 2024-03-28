package md.hunt.sudoku;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import java.util.Locale;

public class InstructionsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        Locale locale = getResources().getConfiguration().getLocales().get(0);
        System.out.println(locale);

        Resources res = getBaseContext().getResources();
        Configuration configuration = res.getConfiguration();
        configuration.setLocale(locale);
        res.updateConfiguration(configuration, res.getDisplayMetrics());

        locale = getResources().getConfiguration().getLocales().get(0);
        System.out.println(locale);
    }

    public void onGoBackButtonClicked(View view) {
        finish();
    }
}
