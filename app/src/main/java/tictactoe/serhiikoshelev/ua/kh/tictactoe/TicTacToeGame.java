package tictactoe.serhiikoshelev.ua.kh.tictactoe;

/**
 * Created by serhiikoshelev on 30.03.17.
 * A simplest logic that could be to have a trainig with Android App structure and logic
 * I have many ideas to make better, make it later
 *  v1.0
 */
import java.util.Random;

public class TicTacToeGame
{

    private char mBoard[];

    private final static int BOARD_SIZE=9;
    public final static char HUMAN_PLAYER = 'X';
    public final static char ANDROID_PLAYER= 'O';
    public final static char EMPTY_SPACE=' ';

    private Random mRand;

    public  static int getBoardSize()
    {
        return BOARD_SIZE;
    }

    public void cleanBoard()
    {

        for(int i=0; i<BOARD_SIZE; i++)
        {
            mBoard[i]=EMPTY_SPACE;
        }
    }

    public TicTacToeGame()
    {
        mBoard = new char[BOARD_SIZE];

        cleanBoard();

     mRand = new Random();
    }

    public void setMove(char player, int location)
    {
        mBoard[location] = player;
    }

    public  int getComputerMove()
    {
        int move;
        //if computer can win
        for(int i=0; i< getBoardSize();i++)
        {
            if(mBoard[i] != HUMAN_PLAYER && mBoard[i] != ANDROID_PLAYER)
            {
                char curr = mBoard[i];
                mBoard[i] = ANDROID_PLAYER;
                if(checkForWinner() == 3)
                {
                    setMove(ANDROID_PLAYER,i);
                    return i;
                }
                else
                {
                    curr = mBoard[i];
                }
            }
        }

        //if computer can't ween, but can block user
        for(int i=0; i< getBoardSize();i++)
        {
            if(mBoard[i] != HUMAN_PLAYER && mBoard[i] != ANDROID_PLAYER)
            {
                char curr = mBoard[i];
                mBoard[i] = HUMAN_PLAYER;
                if(checkForWinner() == 2)
                {
                    setMove(ANDROID_PLAYER,i);
                    return i;
                }
                else
                {
                    curr = mBoard[i];
                }
            }
        }
        //if computer can't win and block, do the random move
        do
        {
            move = mRand.nextInt(getBoardSize());
        } while (mBoard[move] == HUMAN_PLAYER || mBoard[move] == ANDROID_PLAYER);

        setMove(ANDROID_PLAYER,move);
        return move;
    }

    public int checkForWinner()
    {
        //some scary logic, but it's "good enough". Make check by custom loops

        //First, for horizontal lines check 3 in row with step 3 to check each row
        for(int i=0;i <=6 ; i+=3 )
        {
            if(     mBoard[i] == HUMAN_PLAYER &&
                    mBoard[i+1] == HUMAN_PLAYER &&
                    mBoard[i+2] == HUMAN_PLAYER)
            return 2;

            if(     mBoard[i] == ANDROID_PLAYER &&
                    mBoard[i+1] == ANDROID_PLAYER &&
                    mBoard[i+2] == ANDROID_PLAYER)
            return 3;
        }

        //Then, for vertical lines
        for(int i=0;i <=2 ; i++ )
        {
            if(     mBoard[i] == HUMAN_PLAYER &&
                    mBoard[i+3] == HUMAN_PLAYER &&
                    mBoard[i+6] == HUMAN_PLAYER)
                return 2;

            if(     mBoard[i] == ANDROID_PLAYER &&
                    mBoard[i+3] == ANDROID_PLAYER &&
                    mBoard[i+6] == ANDROID_PLAYER)
                return 3;
        }

        //And last, for diagonal lines
        if((mBoard[0]==HUMAN_PLAYER &&
            mBoard[4] == HUMAN_PLAYER &&
            mBoard[8]== HUMAN_PLAYER) ||
                mBoard[2]== HUMAN_PLAYER &&
                mBoard[4] == HUMAN_PLAYER &&
                mBoard[6] == HUMAN_PLAYER)
            return 2;

        if((mBoard[0]== ANDROID_PLAYER &&
                mBoard[4] == ANDROID_PLAYER &&
                mBoard[8]== ANDROID_PLAYER) ||
                mBoard[2]== ANDROID_PLAYER &&
                        mBoard[4] == ANDROID_PLAYER &&
                        mBoard[6] == ANDROID_PLAYER)
            return 3;

        //Check here,if we still have a EMPTY_SPACE to continue game
        for(int i=0;i<getBoardSize();i++)
        {
            if(mBoard[i] != HUMAN_PLAYER && mBoard[i] != ANDROID_PLAYER)
                return 0;
        }

        //if all false , then we had a tie
        return 1;
    }


}
