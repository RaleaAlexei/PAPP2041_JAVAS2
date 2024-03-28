package md.hunt.sudoku;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

public class ChooseNumberActivity extends AppCompatActivity {
    private int selectedNumber = 1;
    private boolean checkBoxChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_number);
        initializeSpinner();
        boolean newBoardCreating = getIntent().getBooleanExtra("newBoard", false);
        if (newBoardCreating) {
            CheckBox checkBox = findViewById(R.id.checkBox);
            checkBox.setVisibility(View.INVISIBLE);
        }

        Spinner spinner = findViewById(R.id.spinner);
        Button buttonChooseNumber = findViewById(R.id.buttonChooseNumber);
        Button buttonGoBack = findViewById(R.id.button);
        Button buttonRemovePiece = findViewById(R.id.button2);
        CheckBox checkBox = findViewById(R.id.checkBox);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedNumber = (Integer) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonChooseNumber.setOnClickListener(this::chooseNumberButtonClicked);
        buttonGoBack.setOnClickListener(this::onGoBackButtonClicked);
        buttonRemovePiece.setOnClickListener(this::onRemovePieceButtonClicked);
        checkBox.setOnClickListener(this::onCheckBoxClicked);
    }

    public void chooseNumberButtonClicked(View view) {
        Intent intent = new Intent();
        intent.putExtra("chosenNumber", selectedNumber);
        intent.putExtra("isUnsure", checkBoxChecked);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onRemovePieceButtonClicked(View view) {
        Intent intent = new Intent();
        intent.putExtra("removePiece", true);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onGoBackButtonClicked(View view) {
        finish();
    }

    public void onCheckBoxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        checkBoxChecked = view.getId() == R.id.checkBox && checked;
    }

    private void initializeSpinner() {
        final Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numbers);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter);
    }
}
