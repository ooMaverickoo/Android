package com.example.android.counterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WinnerScreenActivity extends AppCompatActivity {

    private int mRounds;
    private boolean mIsAlienWinner;
    private boolean mIsPredatorWinner;
    private boolean mPredatorImageSelected;
    private boolean mAlienImageSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_screen);


        TextView winnerScreen = (TextView) findViewById(R.id.winner_screen);
        TextView infoScreen = (TextView) findViewById(R.id.info_screen);
        ImageView alienImage = (ImageView) findViewById(R.id.alien_win_image);
        ImageView predatorImage = (ImageView) findViewById(R.id.predator_win_image);


        //Create Bundle for the transfer of the variables
        Bundle bundle = getIntent().getExtras();

        //Get variables from MainActivity
        mIsAlienWinner = bundle.getBoolean("mIsAlienWinner");
        mIsPredatorWinner = bundle.getBoolean("mIsPredatorWinner");
        mRounds = bundle.getInt("mRoundValue");
        mAlienImageSelected = bundle.getBoolean("mAlienImageSelected");
        mPredatorImageSelected = bundle.getBoolean("mPredatorImageSelected");


        if (mIsPredatorWinner) {
            if (!mPredatorImageSelected) {
                winnerScreen.setText(R.string.you_lost);
                infoScreen.setText(getString(R.string.rounds_to_win,
                        getString(R.string.predator_needs), mRounds));
            } else {
                winnerScreen.setText(R.string.you_win);
                infoScreen.setText(getString(R.string.rounds_to_win,
                        getString(R.string.you_need), mRounds));
            }

            infoScreen.setText(getString(R.string.rounds_to_win,
                    getString(R.string.predator_needs), mRounds));
            predatorImage.setVisibility(View.VISIBLE);
            alienImage.setVisibility(View.GONE);

        } else {

            if (!mAlienImageSelected) {
                winnerScreen.setText(R.string.you_lost); //shows the enemy win
                infoScreen.setText(getString(R.string.rounds_to_win,
                        getString(R.string.alien_needs), mRounds));
            } else {
                winnerScreen.setText(R.string.you_win);//shows you win
                infoScreen.setText(getString(R.string.rounds_to_win,
                        getString(R.string.you_need), mRounds));
            }


            predatorImage.setVisibility(View.GONE);
            alienImage.setVisibility(View.VISIBLE);
        }






    }


    public void buttonRestartGame(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}





