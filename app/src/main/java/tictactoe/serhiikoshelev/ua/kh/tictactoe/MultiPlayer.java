package tictactoe.serhiikoshelev.ua.kh.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MultiPlayer extends AppCompatActivity {

    private TicTacToeMultiPlayer mGame;

    private Button mBoardButtons[];
    private Button mStartNewGame;

    private TextView mPlayerXCount;
    private TextView mTieCount;
    private TextView mPlayerOCount;
    private TextView mInfoTextView;

    private int mPlayerXCounter = 0;
    private int mTieCounter = 0;
    private int mPlayerOCounter = 0;

    boolean mPlayerXTurnFirst = true;
    private boolean mGameOver = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mStartNewGame = (Button) findViewById(R.id.startNewGame);

        mBoardButtons = new Button[mGame.getBoardSize()];
        mBoardButtons[0] = (Button) findViewById(R.id.one);
        mBoardButtons[1] = (Button) findViewById(R.id.two);
        mBoardButtons[2] = (Button) findViewById(R.id.three);
        mBoardButtons[3] = (Button) findViewById(R.id.four);
        mBoardButtons[4] = (Button) findViewById(R.id.five);
        mBoardButtons[5] = (Button) findViewById(R.id.six);
        mBoardButtons[6] = (Button) findViewById(R.id.seven);
        mBoardButtons[7] = (Button) findViewById(R.id.eight);
        mBoardButtons[8] = (Button) findViewById(R.id.nine);

        mInfoTextView = (TextView) findViewById(R.id.information);
        mPlayerXCount = (TextView) findViewById(R.id.humanScore);
        mTieCount = (TextView) findViewById(R.id.tiesScore);
        mPlayerOCount = (TextView) findViewById(R.id.androidScore);

        mPlayerXCount.setText(Integer.toString(mPlayerXCounter));
        mTieCount.setText(Integer.toString(mTieCounter));
        mPlayerOCount.setText(Integer.toString(mPlayerOCounter));

        mGame = new TicTacToeMultiPlayer();

        startNewGame(mStartNewGame);
    }

    public void startNewGame(View view)
    {
        mGame.clearBoard();

        for (int i = 0; i < mGame.getBoardSize(); i++) {
            mBoardButtons[i].setText("");
            mBoardButtons[i].setEnabled(true);
            mBoardButtons[i].setOnClickListener(new MultiPlayer.ButtonClickListener(i));
        }

        if(mPlayerXTurnFirst)
        {
            mInfoTextView.setText(R.string.playerX_turn);//fix!!!
            mPlayerXTurnFirst = false;
        }
        else
        {
            mInfoTextView.setText(R.string.playerO_turn);
            mPlayerXTurnFirst = true;
        }
    }

    private class ButtonClickListener implements View.OnClickListener {
        int location;


        public ButtonClickListener(int location) {
            this.location = location;
        }

        public void onClick(View view) {

            if (!mGameOver) {
                mStartNewGame.setEnabled(false);

                if (mBoardButtons[location].isEnabled()) {
                    if(mGame.trg % 2 == 0){
                        setMove(mGame.FIRST_PLAYER,location);
                        mInfoTextView.setText(R.string.playerO_turn);

                        mGame.trg++;
                    }

                    else
                    {
                        setMove(mGame.SECOND_PLAYER,location);
                        mInfoTextView.setText(R.string.playerX_turn);
                        mGame.trg++;
                    }

                    int winner = mGame.checkForWinner();

                    if (winner == 1) {
                        mInfoTextView.setText(R.string.tie);
                        mTieCounter++;
                        mTieCount.setText(Integer.toString(mTieCounter));
                        mGameOver = true;
                    } else if (winner == 2) {
                        mInfoTextView.setText(R.string.playerX_won);//fix
                        mPlayerXCounter++;
                        mPlayerXCount.setText(Integer.toString(mPlayerXCounter));
                        mGameOver = true;
                    } else if (winner == 3) {
                        mInfoTextView.setText(R.string.playrO_won);
                        mPlayerOCounter++;
                        mPlayerOCount.setText(Integer.toString(mPlayerOCounter));
                        mGameOver = true;
                    }
                }
            }
            if (mGameOver) {
                mStartNewGame.setEnabled(true);
                mGameOver = false;
            }
        }
    }

    private void setMove(char player, int location)
    {
        mGame.setMove(player, location);
        mBoardButtons[location].setEnabled(false);
        mBoardButtons[location].setText(String.valueOf(player));
        if(player == mGame.FIRST_PLAYER)
        {
            mBoardButtons[location].setTextColor(Color.RED);
        }
        if(player == mGame.SECOND_PLAYER)
        {
            mBoardButtons[location].setTextColor(Color.BLACK);
        }
    }


}
