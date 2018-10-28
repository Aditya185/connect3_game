package com.example.adityaprakash.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0:yellow 1:red 2:empty;
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int [][] winningpositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int activeplayer =0;
    boolean gameactive = true;

    public void dropin(View view) {
        ImageView counter = (ImageView) view;
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter] == 2 && gameactive) {
            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1500);

            if (activeplayer == 0) {

                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;

            }


            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningPosition : winningpositions) {
                if (gamestate[winningPosition[0]] == gamestate[winningPosition[1]] && gamestate[winningPosition[1]] == gamestate[winningPosition[2]] && gamestate[winningPosition[0]] != 2) {
                    //someone has won
                    String winner = "";
                    gameactive=false;
                    if (activeplayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }


                    Toast.makeText(this, winner + " has Won", Toast.LENGTH_SHORT).show();
                    Button playagainButton = (Button) findViewById(R.id.playagainbutton);
                    TextView winnertextView = (TextView) findViewById(R.id.winnertextView);
                    winnertextView.setText(winner+ " has Won");
                    playagainButton.setVisibility(View.VISIBLE);
                    winnertextView.setVisibility((View.VISIBLE));
                    GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
                    for(int i=0;i<gridLayout.getChildCount();i++){
                        counter = (ImageView) gridLayout.getChildAt(i);
                        counter.setImageDrawable(null);

                    }
                    for(int i=0;i<gamestate.length;i++){
                        gamestate[i]=2;
                    }

                     activeplayer =0;
                     gameactive = true;

                }
            }
            int count=0;
            for(int i:gamestate){
                if(i != 2){
                    count++;
                }
            }
            if (count == 9){
                Toast.makeText(this, "Match has Drawn", Toast.LENGTH_SHORT).show();
                Button playagainButton = (Button) findViewById(R.id.playagainbutton);
                TextView winnertextView = (TextView) findViewById(R.id.winnertextView);
                winnertextView.setText("Match has Drawn");
                playagainButton.setVisibility(View.VISIBLE);
                winnertextView.setVisibility((View.VISIBLE));
                GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
                for(int i=0;i<gridLayout.getChildCount();i++){
                    counter = (ImageView) gridLayout.getChildAt(i);
                    counter.setImageDrawable(null);

                }
                for(int i=0;i<gamestate.length;i++){
                    gamestate[i]=2;
                }

                activeplayer =0;
                gameactive = true;

            }
        }
    }
    public void playAgain(View view){
        Button playagainbutton = (Button) findViewById(R.id.playagainbutton);
        TextView winnertextView = (TextView) findViewById(R.id.winnertextView);

        playagainbutton.setVisibility(View.INVISIBLE);
        winnertextView.setVisibility((View.INVISIBLE));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
