package com.example.android.counterapp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AnimationDrawable alienAnimation;
    private AnimationDrawable predatorAnimation;
    private boolean mChooseAlien = false;
    private boolean mChoosePredator = false;
    private boolean mAlienImageSelected = false;
    private boolean mPredatorImageSelected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton buttonStart = (ImageButton) findViewById(R.id.button_start);

        buttonStart.setVisibility(View.INVISIBLE);
        buttonStart.isEnabled();


    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        ImageButton buttonChooseAlien = (ImageButton) findViewById(R.id.button_choose_alien);
        ImageButton buttonChoosePredator = (ImageButton) findViewById(R.id.button_choose_predator);
        ImageButton buttonStart = (ImageButton) findViewById(R.id.button_start);
        TextView statusScreen = (TextView) findViewById(R.id.status_screen);
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("mChooseAlien", mChooseAlien);
        savedInstanceState.putBoolean("mChoosePredator", mChoosePredator);
        savedInstanceState.putBoolean("mPredatorImageSelected", buttonChoosePredator.isSelected());
        savedInstanceState.putBoolean("mAlienImageSelected", buttonChooseAlien.isSelected());
        savedInstanceState.putInt("mStartButtonIsVisible", buttonStart.getVisibility());
        savedInstanceState.putString("statusScreenText", statusScreen.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        int vis;
        ImageButton buttonChooseAlien = (ImageButton) findViewById(R.id.button_choose_alien);
        ImageButton buttonChoosePredator = (ImageButton) findViewById(R.id.button_choose_predator);
        ImageButton buttonStart = (ImageButton) findViewById(R.id.button_start);
        TextView statusScreen = (TextView) findViewById(R.id.status_screen);
        super.onRestoreInstanceState(savedInstanceState);
        mChooseAlien = savedInstanceState.getBoolean("mChooseAlien");
        mChoosePredator = savedInstanceState.getBoolean("mChoosePredator");
        buttonChooseAlien.setSelected(savedInstanceState.getBoolean("mAlienImageSelected"));
        buttonChoosePredator.setSelected(savedInstanceState.getBoolean("mPredatorImageSelected"));
        vis = savedInstanceState.getInt("mStartButtonIsVisible");
        buttonStart.setVisibility(vis == 0 ? View.VISIBLE : vis == 4 ? View.INVISIBLE : View.GONE);
        statusScreen.setText(savedInstanceState.getString("statusScreenText"));

    }


    public void buttonChooseAlien(View button) {
        ImageButton buttonStart = (ImageButton) findViewById(R.id.button_start);
        TextView statusScreen = (TextView) findViewById(R.id.status_screen);
        ImageButton buttonChoosePredator = (ImageButton) findViewById(R.id.button_choose_predator);


        if (mChoosePredator) {
            mChoosePredator = false;
            mChooseAlien = true;
            button.setSelected(true);
            buttonChoosePredator.setSelected(false);
            statusScreen.setText(R.string.alien);

        } else {
            mChooseAlien = true;
            button.setSelected(true);
            buttonChoosePredator.setSelected(false);
            buttonStart.setVisibility(View.VISIBLE);
            statusScreen.setText(R.string.alien);
        }


    }

    public void buttonChoosePredator(View button) {
        ImageButton buttonStart = (ImageButton) findViewById(R.id.button_start);
        TextView statusScreen = (TextView) findViewById(R.id.status_screen);
        ImageButton buttonChooseAlien = (ImageButton) findViewById(R.id.button_choose_alien);

        if (mChooseAlien) {
            mChooseAlien = false;
            mChoosePredator = true;
            button.setSelected(true);
            buttonChooseAlien.setSelected(false);
            statusScreen.setText(R.string.predator);
        } else {
            mChoosePredator = true;
            button.setSelected(true);
            buttonChooseAlien.setSelected(false);
            statusScreen.setText(R.string.predator);
            buttonStart.setVisibility(View.VISIBLE);
        }

    }


    public void buttonStartGame(View v) {

        Intent intent = new Intent(this, GameScreenActivity.class);
        intent.putExtra("mChooseAlien", mChooseAlien);
        intent.putExtra("mChoosePredator", mChoosePredator);
        startActivity(intent);

    }


}
