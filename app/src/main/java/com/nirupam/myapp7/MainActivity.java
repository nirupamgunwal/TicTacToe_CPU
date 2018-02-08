package com.nirupam.myapp7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //activePlayer = 0 means Cross and 1 means Circle
    int activePlayer = -1;

    int count = 0;

    boolean gameIsActive = true;

    int[] gameState = {0,0,0,0,0,0,0,0,0};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn (View view){


        ImageView counter  = (ImageView) view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        if(gameState[tappedCounter] == 0 && gameIsActive == true) {

            counter.setTranslationY(1100f);

            gameState[tappedCounter] = activePlayer;

            if (activePlayer == -1 && count<9) {

                counter.setImageResource(R.drawable.cross);

                activePlayer = 1;

                counter.animate().translationYBy(-1100f).setDuration(300);

                count++;

                cpumove();

            }

            for( int[] winningPosition : winningPositions){
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[2]] != 0) {

                    gameIsActive = false;

                    TextView textView = (TextView) findViewById(R.id. winningGameText);

                    if (gameState[winningPosition[0]] == -1) {
                        textView.setText("Cross Won");


                    }

                    else{
                        textView.setText("Circle Won");
                    }



                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);

                    linearLayout.bringToFront();

                    linearLayout.setVisibility(View.VISIBLE);
                }

                else{

                    boolean gameIsOver = true;

                    for (int counterState : gameState){
                        if (counterState == 0){
                            gameIsOver = false;
                        }
                    }

                    if (gameIsOver == true){

                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);

                        TextView textView = (TextView) findViewById(R.id. winningGameText);

                        linearLayout.bringToFront();

                        linearLayout.setVisibility(View.VISIBLE);

                        textView.setText("Its a draw");

                    }

                }
            }

        }


    }

    public void playAgain(View view){


        activePlayer = -1;

        count = 0;

        gameIsActive = true;

        for (int i =0;i<gameState.length; i++){
            gameState[i] = 0;

        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0 ; i<gridLayout.getChildCount() ; i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);



        linearLayout.setVisibility(View.INVISIBLE);


    }



    public int win(int[] board) {
        //determines if a player has won, returns 0 otherwise.
        int[][]  wins = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

        int i;

        for(i = 0; i < 8; ++i) {
            if(board[wins[i][0]] != 0 &&
                    board[wins[i][0]] == board[wins[i][1]] &&
                    board[wins[i][0]] == board[wins[i][2]])
                return board[wins[i][2]];
        }
        return 0;
    }


    public int computerMove(int[] board) {
        int move = -1;
        int score = -2;
        int i;
        for(i = 0; i < 9; ++i) {
            if(board[i] == 0) {
                board[i] = 1;
                int tempScore = -minimax(board, -1);
                board[i] = 0;
                if(tempScore > score) {
                    score = tempScore;
                    move = i;
                }
            }
        }
        //returns a score based on minimax tree at a given node.
        board[move] = 1;
        return move;
    }


    public int minimax(int[] board, int player) {
        //How is the position like for player (their turn) on board?
        int winner = win(board);
        if(winner != 0) return winner*player;

        int move = -1;
        int score = -2;//Losing moves are preferred to no move
        int i;
        for(i = 0; i < 9; ++i) {//For all moves,
            if(board[i] == 0) {//If legal,
                board[i] = player;//Try the move
                int thisScore = -minimax(board, player*-1);
                if(thisScore > score) {
                    score = thisScore;
                    move = i;
                }//Pick the one that's worst for the opponent
                board[i] = 0;//Reset board after try
            }
        }
        if(move == -1) return 0;
        return score;
    }

    public void cpumove(){

        if(count < 9) {

            int move;
            move = computerMove(gameState);
            gameState[move] = 1;
            ImageView i1 = (ImageView) findViewById(R.id.gridLayout).findViewWithTag(Integer.toString(move));
            i1.setTranslationY(1100f);
            i1.setImageResource(R.drawable.circle);
            i1.animate().translationYBy(-1100f).setDuration(300);

            activePlayer = -1;

            count++;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
