package com.codeone.android.takeaclimb;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] button = new Button[3][3];
    boolean playerTurn = true;
    private int roundCount;
    private int player1Point;
    private int player2Point;
    private TextView txtViewPlayer1;
    private TextView txtViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtViewPlayer1 = findViewById(R.id.player1);
        txtViewPlayer2 = findViewById(R.id.player2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String butonID = "Button_" + i + j;
                int resourceID = getResources().getIdentifier(butonID, "id", getPackageName());
                button[i][j] = findViewById(resourceID);
                button[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset = findViewById(R.id.btnreset);
    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        if (playerTurn) {
            ((Button) view).setText("X");
        } else {
            ((Button) view).setText("O");
        }
        roundCount++;
        if (winnerChecker()) {
            if (playerTurn) {
                firstPlayer();
            }
        } else {
            secondPlayer();
        }
        if (roundCount == 9) {
            draw();
        } else {
            playerTurn = !playerTurn;
        }
    }

    public void reset(View view) {

    }

    private boolean winnerChecker() {
        String[][] BoardPostion = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                BoardPostion[i][j] = button[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (BoardPostion[i][0].equals(BoardPostion[i][1]) && BoardPostion[i][0].equals(BoardPostion[i][2]) && BoardPostion[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (BoardPostion[0][i].equals(BoardPostion[1][i]) && BoardPostion[0][i].equals(BoardPostion[2][i]) && BoardPostion[0][i].equals("")) {
                return true;
            }
        }

        if (BoardPostion[0][0].equals(BoardPostion[1][1]) && BoardPostion[0][0].equals(BoardPostion[2][2]) && BoardPostion[0][0].equals("")) {
            return true;
        }

        if (BoardPostion[0][2].equals(BoardPostion[1][1]) && BoardPostion[0][2].equals(BoardPostion[2][0]) && BoardPostion[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private void firstPlayer() {
        player1Point++;
        Toast.makeText(this, "player 1 wins", Toast.LENGTH_SHORT).show();
        updateboard();
        resetBoard();
    }

    private void secondPlayer() {
        player2Point++;
        Toast.makeText(this, "player 2 wins", Toast.LENGTH_SHORT).show();
        updateboard();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "it's a draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updateboard() {
        txtViewPlayer1.setText("player 1 - "+player1Point);
        txtViewPlayer2.setText("player 2 - "+player2Point);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
               button [i][j].setText("");
            }
        }
        roundCount = 0;
        playerTurn = true;
    }
}
