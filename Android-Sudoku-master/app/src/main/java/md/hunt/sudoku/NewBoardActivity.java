package md.hunt.sudoku;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import md.hunt.sudoku.fragments.CellGroupFragment;
import md.hunt.sudoku.model.Board;

public class NewBoardActivity extends AppCompatActivity implements CellGroupFragment.OnFragmentInteractionListener {
    private final String TAG = "NewBoardActivity";
    private TextView clickedCell;
    private Board newBoard;
    private int clickedGroup;
    private int clickedCellId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_board);

        int cellGroupFragments[] = new int[]{R.id.cellGroupFragment, R.id.cellGroupFragment2, R.id.cellGroupFragment3, R.id.cellGroupFragment4,
                R.id.cellGroupFragment5, R.id.cellGroupFragment6, R.id.cellGroupFragment7, R.id.cellGroupFragment8, R.id.cellGroupFragment9};
        for (int i = 1; i < 10; i++) {
            CellGroupFragment thisCellGroupFragment = (CellGroupFragment) getSupportFragmentManager().findFragmentById(cellGroupFragments[i-1]);
            thisCellGroupFragment.setGroupId(i);
        }

        newBoard = new Board();
    }

    public void onGoBackButtonClicked(View view) {
        finish();
    }

    public void onContinueButtonClicked(View view) {
        Intent intent = new Intent("md.hunt.GameDifficultyActivity");
        intent.putExtra("newBoard", true);
        startForResult.launch(intent);
    }

    private void saveBoard(int difficulty) {
        String fileName = "boards-";
        if (difficulty == 0) {
            fileName += "easy";
        } else if (difficulty == 1) {
            fileName += "normal";
        } else {
            fileName += "hard";
        }
        String content = newBoard.toString();
        System.out.println("new Board: " + content);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = openFileOutput(fileName, Context.MODE_APPEND);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final ActivityResultLauncher<Intent> startForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data.getBooleanExtra("boardSaved", false)) {
                        saveBoard(data.getIntExtra("difficulty", 1));
                        Toast.makeText(this, getString(R.string.successful_new_board), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, getString(R.string.successful_new_board), Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    @Override
    public void onFragmentInteraction(int groupId, int cellId, View view) {
        clickedCell = (TextView) view;
        clickedCellId = cellId;
        clickedGroup = groupId;
        Log.i(TAG, "Clicked group " + groupId + ", cell " + cellId);
        Intent intent = new Intent("md.hunt.ChooseNumberActivity");
        intent.putExtra("newBoard", true);
        startForResult.launch(intent);
    }
}
