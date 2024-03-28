package md.hunt.sudoku;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class GameDifficultyActivity extends AppCompatActivity {
    private boolean newBoard = false;
    private int selectedDifficulty = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_difficulty);
        newBoard = getIntent().getBooleanExtra("newBoard", false);
        if (newBoard) {
            Button buttonContinue = findViewById(R.id.buttonContinue);
            buttonContinue.setText(getString(R.string.add_new_board));
        }

        RadioButton radioButtonEasy = findViewById(R.id.radioButtonEasy);
        RadioButton radioButtonNormal = findViewById(R.id.radioButtonNormal);
        RadioButton radioButtonHard = findViewById(R.id.radioButtonHard);
        Button buttonContinue = findViewById(R.id.buttonContinue);
        Button buttonGoBack = findViewById(R.id.button);

        radioButtonEasy.setOnClickListener(this::onDifficultyRadioButtonsClicked);
        radioButtonNormal.setOnClickListener(this::onDifficultyRadioButtonsClicked);
        radioButtonHard.setOnClickListener(this::onDifficultyRadioButtonsClicked);
        buttonContinue.setOnClickListener(this::onStartGameButtonClicked);
        buttonGoBack.setOnClickListener(this::onGoBackButtonClicked);
    }

    public void onGoBackButtonClicked(View view) {
        finish();
    }

    public void onDifficultyRadioButtonsClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if(!checked) return;
        int id = view.getId();
        if(id == R.id.radioButtonEasy){
            selectedDifficulty = 0;
        } else if (id == R.id.radioButtonNormal) {
            selectedDifficulty = 1;
        } else if (id == R.id.radioButtonHard) {
            selectedDifficulty = 2;
        }
    }

    public void onStartGameButtonClicked(View view) {
        if (newBoard) {
            Intent intent = new Intent();
            intent.putExtra("boardSaved", true);
            intent.putExtra("difficulty", selectedDifficulty);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("difficulty", selectedDifficulty);
            startActivity(intent);
        }
    }
}
