package tictactoe.serhiikoshelev.ua.kh.tictactoe;

/**
 * Created by serhiikoshelev on 30.03.17.
 * A simplest logic that could be to have a trainig with Android App structure and logic
 *  v1.2
 */
import java.util.Random;


public class TicTacToeSinglePlayer extends TicTacToeMultiPlayer {

    public final static char HUMAN_PLAYER = FIRST_PLAYER;
    public final static char ANDROID_PLAYER = SECOND_PLAYER;
    private Random mRand;

    public TicTacToeSinglePlayer(){

        new TicTacToeMultiPlayer();


        mRand = new Random();
    }

    public int getComputerMove()
    {
        int move;

        for (int i = 0; i < getBoardSize(); i++)
        {
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != ANDROID_PLAYER)
            {
                char curr = mBoard[i];
                mBoard[i] = ANDROID_PLAYER;
                if (checkForWinner() == 3)
                {
                    setMove(ANDROID_PLAYER, i);
                    return i;
                }
                else
                    mBoard[i] = curr;
            }
        }

        for (int i = 0; i < getBoardSize(); i++)
        {
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != ANDROID_PLAYER)
            {
                char curr = mBoard[i];
                mBoard[i] = HUMAN_PLAYER;
                if (checkForWinner() == 2)
                {
                    setMove(ANDROID_PLAYER, i);
                    return i;
                }
                else
                    mBoard[i] = curr;
            }
        }

        do
        {
            move = mRand.nextInt(getBoardSize());
        } while (mBoard[move] == HUMAN_PLAYER || mBoard[move] == ANDROID_PLAYER);

        setMove(ANDROID_PLAYER, move);
        return move;
    }

}

