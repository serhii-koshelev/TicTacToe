package tictactoe.serhiikoshelev.ua.kh.tictactoe;

import java.util.Random;
/**
 * Created by serhiikoshelev on 03.04.17.
 */

public class TicTacToeMultiPlayer
{
    char mBoard[];
    private final static int BOARD_SIZE = 9;

    public static final char FIRST_PLAYER = 'X';
    public static final char SECOND_PLAYER = '0';
    public static final char EMPTY_SPACE = ' ';
    public int trg ;


    public static int getBoardSize() {
        return BOARD_SIZE;
    }

    public TicTacToeMultiPlayer(){

        mBoard = new char[BOARD_SIZE];
        clearBoard();
    }

    public void clearBoard()
    {
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            mBoard[i] = EMPTY_SPACE;
        }
    }

    public void setMove( char player,int location)
    {
        mBoard[location] = player;
    }

    public int checkForWinner()
    {
        for (int i = 0; i <= 6; i += 3)
        {
            if (mBoard[i] == FIRST_PLAYER &&
                    mBoard[i+1] == FIRST_PLAYER &&
                    mBoard[i+2] == FIRST_PLAYER)
                return 2;
            if (mBoard[i] == SECOND_PLAYER &&
                    mBoard[i+1] == SECOND_PLAYER &&
                    mBoard[i+2] == SECOND_PLAYER)
                return 3;
        }

        for (int i = 0; i <= 2; i++)
        {
            if (mBoard[i] == FIRST_PLAYER &&
                    mBoard[i+3] == FIRST_PLAYER &&
                    mBoard[i+6] == FIRST_PLAYER)
                return 2;
            if (mBoard[i] == SECOND_PLAYER &&
                    mBoard[i+3] == SECOND_PLAYER &&
                    mBoard[i+6] == SECOND_PLAYER)
                return 3;
        }

        if ((mBoard[0] == FIRST_PLAYER &&
                mBoard[4] == FIRST_PLAYER &&
                mBoard[8] == FIRST_PLAYER) ||
                mBoard[2] == FIRST_PLAYER &&
                        mBoard[4] == FIRST_PLAYER &&
                        mBoard[6] == FIRST_PLAYER)
            return 2;
        if ((mBoard[0] == SECOND_PLAYER &&
                mBoard[4] == SECOND_PLAYER &&
                mBoard[8] == SECOND_PLAYER) ||
                mBoard[2] == SECOND_PLAYER &&
                        mBoard[4] == SECOND_PLAYER &&
                        mBoard[6] == SECOND_PLAYER)
            return 3;

        for (int i = 0; i < getBoardSize(); i++)
        {
            if (mBoard[i] != FIRST_PLAYER && mBoard[i] != SECOND_PLAYER)
                return 0;
        }

        return 1;
    }
}

