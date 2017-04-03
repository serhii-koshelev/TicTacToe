package tictactoe.serhiikoshelev.ua.kh.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static tictactoe.serhiikoshelev.ua.kh.tictactoe.TicTacToeGame.*;

public class SinglePlayer extends AppCompatActivity
{

    private TicTacToeGame mGame;

    private Button mBoardButtons[];
    private Button mStartNewGame;

    private TextView mHumanCount;
    private TextView mTieCount;
    private TextView mAndroidCount;
    private TextView mInfoTextView;

    private int mHumanCounter = 0;
    private int mTieCounter = 0;
    private int mAndroidCounter = 0;

    private boolean mHumanTurnFirst = true;
    private boolean mGameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

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
        mHumanCount = (TextView) findViewById(R.id.humanScore);
        mTieCount = (TextView) findViewById(R.id.tiesScore);
        mAndroidCount = (TextView) findViewById(R.id.androidScore);

        mHumanCount.setText(Integer.toString(mHumanCounter));
        mTieCount.setText(Integer.toString(mTieCounter));
        mAndroidCount.setText(Integer.toString(mAndroidCounter));

        mGame = new TicTacToeGame();

        startNewGame(mStartNewGame);

    }

    public void startNewGame(View view)
    {
        mGame.clearBoard();

        for (int i = 0; i < mGame.getBoardSize(); i++) {
            mBoardButtons[i].setText("");
            mBoardButtons[i].setEnabled(true);
            mBoardButtons[i].setOnClickListener(new ButtonClickListener(i));
        }

        if(mHumanTurnFirst)
        {
            mInfoTextView.setText(R.string.human_turn);
            mHumanTurnFirst = false;

        }
        else
        {
            mInfoTextView.setText(R.string.android_turn);
            int move =mGame.getComputerMove();
            setMove(mGame.ANDROID_PLAYER, move);
            mHumanTurnFirst = true;
        }



    }

    private class ButtonClickListener implements View.OnClickListener
    {
        int location;

        public  ButtonClickListener(int location)
        {
            this.location = location;
        }

        public  void onClick(View view)
        {

            if(!mGameOver)
            {
                mStartNewGame.setEnabled(false);

                if(mBoardButtons[location].isEnabled()) {
                    setMove(mGame.HUMAN_PLAYER, location);

                    int winner = mGame.checkForWinner();

                    if (winner == 0)
                        {
                        mInfoTextView.setText(R.string.android_turn);
                        int move = mGame.getComputerMove();
                        setMove(mGame.ANDROID_PLAYER, move);
                        winner = mGame.checkForWinner();
                        }
                        if (winner == 0)
                        {
                            mInfoTextView.setText(R.string.human_turn);
                        } else if (winner == 1)
                        {
                            mInfoTextView.setText(R.string.tie);
                            mTieCounter++;
                            mTieCount.setText(Integer.toString(mTieCounter));
                            mGameOver = true;
                        } else if (winner == 2)
                        {
                            mInfoTextView.setText(R.string.human_won);
                            mHumanCounter++;
                            mHumanCount.setText(Integer.toString(mHumanCounter));
                            mGameOver = true;
                       } else if (winner == 3)
                       {
                            mInfoTextView.setText(R.string.android_win);
                            mAndroidCounter++;
                            mAndroidCount.setText(Integer.toString(mAndroidCounter));
                            mGameOver = true;
                        }
                    }
                }
                if(mGameOver)
                {
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
        if(player == mGame.HUMAN_PLAYER)
        {
            mBoardButtons[location].setTextColor(Color.RED);
        }
        if(player == mGame.ANDROID_PLAYER)
        {
            mBoardButtons[location].setTextColor(Color.BLACK);
        }

    }

}
