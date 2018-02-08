package com.nirupam.myapp7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    //activePlayer = 0 means Cross and 1 means Circle
    int activePlayer = 0;

    boolean gameIsActive = true;

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn (View view){


        ImageView counter  = (ImageView) view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        if(gameState[tappedCounter] == 2 && gameIsActive == true) {

            counter.setTranslationY(1100f);

            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.cross);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.circle);

                activePlayer = 0;
            }

            counter.animate().translationYBy(-1100f).setDuration(300);

            for( int[] winningPosition : winningPositions){
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[2]] != 2) {

                    gameIsActive = false;

                    TextView textView = (TextView) findViewById(R.id. winningGameText);

                    if (gameState[winningPosition[0]] == 0) {
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
                        if (counterState == 2){
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


        activePlayer = 0;

        gameIsActive = true;

        for (int i =0;i<gameState.length; i++){
            gameState[i] = 2;

        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0 ; i<gridLayout.getChildCount() ; i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);



        linearLayout.setVisibility(View.INVISIBLE);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
