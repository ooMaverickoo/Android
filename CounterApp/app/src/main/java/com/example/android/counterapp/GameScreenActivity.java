package com.example.android.counterapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static com.example.android.counterapp.R.id.alien;
import static com.example.android.counterapp.R.id.predator;
import static com.example.android.counterapp.R.string.dmg;

public class GameScreenActivity extends AppCompatActivity {

    final static String ROUND_VALUE = "mRoundValue";
    final static String DRAWS = "mDraws";
    final static String ALIEN_LIFE_VALUE = "mAlienLifeValue";
    final static String PREDATOR_LIFE_VALUE = "mPredatorLifeValue";
    final static String ALIEN_DEAD = "mAlienDead";
    final static String PREDATOR_DEAD = "mPredator";
    final static String ALIEN_HIT_POINTS = "mAlienHitPoints";
    final static String PREDATOR_HIT_POINTS = "mPredatorHitPoints";
    final static String BACKGROUND_IMAGE = "mBackgroundId";
    final static String ALIEN_SELECTED = "mAlienSelected";
    final static String PREDATOR_SELECTED = "mPredatorSelected";
    final static String HIT_BUTTON_VISIBLE = "mHitButtonVisible";
    final static String FIGHT_BUTTON_VISIBLE = "mFightButtonVisible";
    final static String SCREEN_TEXT = "mScreenText";
    final static String ACTION_BUTTON_ALL_VISIBLE = "mActionButtonsAllVisible";
    final static String BUTTON_NEXT = "mButtonNext";


    private final int MAX_DMG = 3;
    private final int MIN_DMG = 1;
    private final int KICK = 2;
    private final int PUNCH = 1;
    private final int BUTTON_DURATION_TIME_1500 = 1500;
    private final int TOAST_DURATION_TIME_1000 = 1000;
    private final int MAX_ENERGY = 5;


    private AnimationDrawable alienAnimation;
    private AnimationDrawable predatorAnimation;
    private int mAlienHitValue;
    private int mPredatorHitValue;
    private int mAlienLifeValue = 5;
    private int mPredatorLifeValue = 5;

    private int mRoundValue = 1;
    private int mDraw = 1;
    private boolean mLastRoundWasADraw = false;

    private boolean mAlienDead = false;
    private boolean mPredatorDead = false;
    private boolean mAlienImageSelected = false;
    private boolean mPredatorImageSelected = false;

    private ViewGroup animationPanel;
    private ViewGroup buttonPanelAllButton;
    private ImageButton buttonHit;
    private ImageButton buttonStartFight;
    private ImageButton buttonKick;
    private ImageButton buttonPunch;
    private TextView drawScreen;
    private TextView roundScreen;
    private TextView screen;
    private TextView alienScreen;
    private TextView predatorScreen;
    private int mBackgroundImage;
    private ImageButton buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);


        animationPanel = (ViewGroup) findViewById(R.id.animation_panel);
        buttonPanelAllButton = (ViewGroup) findViewById(R.id.panel_action_buttons);
        buttonStartFight = (ImageButton) findViewById(R.id.button_start_game_fight);
        buttonHit = (ImageButton) findViewById(R.id.button_hit);
        screen = (TextView) findViewById(R.id.status_screen);
        alienScreen = (TextView) findViewById(R.id.alien_screen);
        predatorScreen = (TextView) findViewById(R.id.predator_screen);
        drawScreen = (TextView) findViewById(R.id.draw_screen);
        roundScreen = (TextView) findViewById(R.id.round_screen);
        buttonNext = (ImageButton) findViewById(R.id.button_next);



        //Create Bundle for the transfer of the variables
        Bundle bundle = getIntent().getExtras();

        //Get variables from MainActivity
        mAlienImageSelected = bundle.getBoolean("mChooseAlien");
        mPredatorImageSelected = bundle.getBoolean("mChoosePredator");


        buttonPanelAllButton.setVisibility(View.GONE);
        buttonHit.setVisibility(View.GONE);
        setRoundStatusText(mRoundValue);
        screen.setText(R.string.press_fight_to_start);
        alienScreen.setText(String.valueOf(MAX_ENERGY));
        predatorScreen.setText(String.valueOf(MAX_ENERGY));


        //gets the variables if the orientation is changed
        if (savedInstanceState != null) {
            int vis;
            mRoundValue = savedInstanceState.getInt(ROUND_VALUE);
            mDraw = savedInstanceState.getInt(DRAWS);
            mAlienLifeValue = savedInstanceState.getInt(ALIEN_LIFE_VALUE);
            mPredatorLifeValue = savedInstanceState.getInt(PREDATOR_LIFE_VALUE);
            mAlienDead = savedInstanceState.getBoolean(ALIEN_DEAD);
            mPredatorDead = savedInstanceState.getBoolean(PREDATOR_DEAD);
            mAlienHitValue = (savedInstanceState.getInt(ALIEN_HIT_POINTS));
            mPredatorHitValue = (savedInstanceState.getInt(PREDATOR_HIT_POINTS));
            mBackgroundImage = (savedInstanceState.getInt(BACKGROUND_IMAGE));
            mAlienImageSelected = savedInstanceState.getBoolean(ALIEN_SELECTED);
            mPredatorImageSelected = savedInstanceState.getBoolean(PREDATOR_SELECTED);
            vis = savedInstanceState.getInt(HIT_BUTTON_VISIBLE);
            buttonHit.setVisibility(vis == 0 ? View.VISIBLE : vis == 4 ? View.INVISIBLE : View.GONE);
            vis = savedInstanceState.getInt(FIGHT_BUTTON_VISIBLE);
            buttonStartFight.setVisibility(vis == 0 ? View.VISIBLE : vis == 4 ? View.INVISIBLE : View.GONE);
            screen.setText(savedInstanceState.getString(SCREEN_TEXT));
            vis = savedInstanceState.getInt(ACTION_BUTTON_ALL_VISIBLE);
            buttonPanelAllButton.setVisibility(vis == 0 ? View.VISIBLE : vis == 4 ? View.INVISIBLE : View.GONE);
            vis = savedInstanceState.getInt(BUTTON_NEXT);
            buttonNext.setVisibility(vis == 0 ? View.VISIBLE : vis == 4 ? View.INVISIBLE : View.GONE);
            alienScreen.setText(String.valueOf(mAlienLifeValue));
            predatorScreen.setText(String.valueOf(mPredatorLifeValue));
            drawScreen.setText(String.valueOf(mDraw));
            roundScreen.setText(String.valueOf(mRoundValue));

            switch (mBackgroundImage) {
                case 1:
                    animationPanel.setBackgroundResource(R.drawable.background_spaceship);
                    mBackgroundImage = R.drawable.background_spaceship;
                    break;

                case 2:
                    animationPanel.setBackgroundResource(R.drawable.background_arena);
                    mBackgroundImage = R.drawable.background_arena;
                    break;


                case 3:
                    animationPanel.setBackgroundResource(R.drawable.background_prometheus);
                    mBackgroundImage = R.drawable.background_arena;
                    break;
            }


        }

        if (savedInstanceState == null) {

            switch (createRandomNumber(3, 1)) {
                case 1:
                    animationPanel.setBackgroundResource(R.drawable.background_spaceship);
                    mBackgroundImage = 1;
                    break;

                case 2:
                    animationPanel.setBackgroundResource(R.drawable.background_arena);
                    mBackgroundImage = 2;
                    break;


                case 3:
                    animationPanel.setBackgroundResource(R.drawable.background_prometheus);
                    mBackgroundImage = 3;
                    break;
            }

        }



    }


    //save status of variables in case of orientation change
    //save status of variables in case of orientation change
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putInt(ROUND_VALUE, mRoundValue);
        savedInstanceState.putInt(DRAWS, mDraw);
        savedInstanceState.putInt(ALIEN_LIFE_VALUE, mAlienLifeValue);
        savedInstanceState.putInt(PREDATOR_LIFE_VALUE, mPredatorLifeValue);
        savedInstanceState.putBoolean(ALIEN_DEAD, mAlienDead);
        savedInstanceState.putBoolean(PREDATOR_DEAD, mPredatorDead);
        savedInstanceState.putInt(BACKGROUND_IMAGE, mBackgroundImage);
        savedInstanceState.putBoolean(ALIEN_SELECTED, mAlienImageSelected);
        savedInstanceState.putBoolean(PREDATOR_SELECTED, mPredatorImageSelected);
        savedInstanceState.putInt(ACTION_BUTTON_ALL_VISIBLE, buttonPanelAllButton.getVisibility());
        savedInstanceState.putInt(HIT_BUTTON_VISIBLE, buttonHit.getVisibility());
        savedInstanceState.putInt(FIGHT_BUTTON_VISIBLE, buttonStartFight.getVisibility());
        savedInstanceState.putInt(BUTTON_NEXT, buttonNext.getVisibility());
        savedInstanceState.putString(SCREEN_TEXT, screen.getText().toString());
        savedInstanceState.putInt(ALIEN_HIT_POINTS, mAlienHitValue);
        savedInstanceState.putInt(PREDATOR_HIT_POINTS, mPredatorHitValue);



        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
    }

    //GameLogic
    //-----------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------

    public void buttonStartGameFight(View v) {
        ImageButton buttonStart = (ImageButton) findViewById(R.id.button_start_game_fight);

        showSimpleToastMessage(getString(R.string.round) + " " + mRoundValue);

        buttonStart.setVisibility(View.GONE);

        //delay the creation of the buttons at 2secs
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                createActionButtons(findViewById(R.id.button_start_game_fight));


            }
        }, BUTTON_DURATION_TIME_1500);


    }

    //create the buttons in independence of the selected Hero
    public void createActionButtons(View v) {
        ViewGroup buttonPanelAllButton = (ViewGroup) findViewById(R.id.panel_action_buttons);

        TextView screen = (TextView) findViewById(R.id.status_screen);
        ImageButton buttonHit = (ImageButton) findViewById(R.id.button_hit);
        TextView drawScreen = (TextView) findViewById(R.id.draw_screen);
        TextView roundScreen = (TextView) findViewById(R.id.round_screen);
        TextView alienLifeScreen = (TextView) findViewById(R.id.alien_screen);
        TextView predatorLifeScreen = (TextView) findViewById(R.id.predator_screen);


        //set button panel visible


        buttonHit.setVisibility(View.GONE);
        buttonPanelAllButton.setVisibility(View.VISIBLE);
        screen.setText(R.string.make_your_choice);
        mAlienHitValue = createRandomNumber(MAX_DMG, MIN_DMG);
        mPredatorHitValue = createRandomNumber(MAX_DMG, MIN_DMG);
        drawScreen.setText(String.valueOf(mDraw));
        roundScreen.setText(String.valueOf(mRoundValue));
        alienLifeScreen.setText(String.valueOf(mAlienLifeValue));
        predatorLifeScreen.setText(String.valueOf(mPredatorLifeValue));

    }


    public void buttonWinner(View v) {

        Intent intent = new Intent(this, WinnerScreenActivity.class);
        intent.putExtra("mIsAlienWinner", mPredatorDead);
        intent.putExtra("mIsPredatorWinner", mAlienDead);
        intent.putExtra("mPredatorImageSelected", mPredatorImageSelected);
        intent.putExtra("mAlienImageSelected", mAlienImageSelected);
        intent.putExtra("mRoundValue", mRoundValue);
        startActivity(intent);
    }


    public void buttonKick(View v) {


        ImageView alienHitTail = (ImageView) findViewById(alien);
        alienHitTail.setBackgroundResource(R.drawable.alien_hit_tail);
        alienAnimation = (AnimationDrawable) alienHitTail.getBackground();
        //Set information text about hit points
        if (mAlienImageSelected) {
            setAlienStatusText(mAlienHitValue);
            showSimpleToastMessage(String.valueOf(mAlienHitValue) + " " + getString(dmg));
        } else {
            setPredatorStatusText(mPredatorHitValue);
            showSimpleToastMessage(String.valueOf(mPredatorHitValue) + " " + getString(dmg));
        }

        handleAnimation(v);
        deleteActionButtons();


        //delay the creation of the buttons at 2secs
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //Creates the buttons for the next step
                createHitButton();

            }
        }, BUTTON_DURATION_TIME_1500);


    }


    public void buttonPunch(View v) {
        ImageView alienHitTail = (ImageView) findViewById(alien);
        alienHitTail.setBackgroundResource(R.drawable.alien_hit_tail);
        alienAnimation = (AnimationDrawable) alienHitTail.getBackground();

        //Set the text with an integrated variable
        if (mAlienImageSelected) {
            setAlienStatusText(mAlienHitValue);
            showSimpleToastMessage(String.valueOf(mAlienHitValue) + " " + getString(dmg));
        } else {
            setPredatorStatusText(mPredatorHitValue);
            showSimpleToastMessage(String.valueOf(mPredatorHitValue) + " " + getString(dmg));
        }

        handleAnimation(v);
        //Creates the buttons for the next step
        deleteActionButtons();

        //delay the creation of the buttons at 2secs
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //Creates the buttons for the next step
                createHitButton();

            }
        }, BUTTON_DURATION_TIME_1500);


    }


    public void handleAnimation(View v) {

        if (mAlienImageSelected) {


            //checks the damage dealing
            if (mAlienHitValue > mPredatorHitValue) {

                heroHitEnemy(v);
            }

            if (mAlienHitValue < mPredatorHitValue) {

                heroHitEnemyWithDraw(v);
            }

            if (mAlienHitValue == mPredatorHitValue) {

                enemyDrawWithHero(v);
            }

        } else {

            //checks the damage dealing
            if (mPredatorHitValue > mAlienHitValue) {

                enemyHitHero(v);

            }

            if (mPredatorHitValue < mAlienHitValue) {

                enemyHitHeroWithDraw(v);
            }

            if (mAlienHitValue == mPredatorHitValue) {

                enemyDrawWithHero(v);
            }


        }

    }


    public void buttonHit(View v) {

        final ImageButton BUTTON_NEXT = (ImageButton) findViewById(R.id.button_next);
        final ImageButton BUTTON_WINNER = (ImageButton) findViewById(R.id.button_winner);


        if (mAlienImageSelected) {
            if (!mPredatorDead) {//predator is not dead
                //checks the damage dealing
                if (mPredatorHitValue > mAlienHitValue) {

                    autoEnemyHitHero();
                }

                if (mPredatorHitValue < mAlienHitValue) {

                    autoEnemyHitHeroWithDraw();
                }

                if (mAlienHitValue == mPredatorHitValue) {

                    heroDrawWithEnemy(v);
                }
            }

        } else {
            if (!mAlienDead) {//alien is not dead

                //checks the damage dealing
                if (mAlienHitValue > mPredatorHitValue) {

                    autoEnemyHitHero();
                }

                if (mAlienHitValue < mPredatorHitValue) {

                    autoEnemyHitHeroWithDraw();
                }

                if (mAlienHitValue == mPredatorHitValue) {

                    heroDrawWithEnemy(v);
                }
            }

        }


        if (!mAlienImageSelected) {
            if (!mAlienDead) {
                setAlienStatusText(mAlienHitValue);
                showSimpleToastMessage(String.valueOf(mAlienHitValue) + " " + getString(dmg));
            } else {

                setAlienStatusTextIfDead();
                showSimpleToastMessage(String.valueOf(mAlienHitValue) + " " + getString(dmg));

            }
        } else {
            if (!mPredatorDead) {
                setPredatorStatusText(mPredatorHitValue);
                showSimpleToastMessage(String.valueOf(mPredatorHitValue) + " " + getString(dmg));
            } else {

                setPredatorStatusTextIfDead();
                showSimpleToastMessage(String.valueOf(mPredatorHitValue) + " " + getString(dmg));

            }
        }


        v.setVisibility(View.GONE);//hide button


        //delay the creation of the buttons at 2secs
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //Creates the buttons for the next step
                if (!mAlienDead && !mPredatorDead) {
                    BUTTON_NEXT.setVisibility(View.VISIBLE);//shows next button
                } else {
                    BUTTON_WINNER.setVisibility(View.VISIBLE);//go to winner screen
                }

            }
        }, BUTTON_DURATION_TIME_1500);

    }


    public void heroDrawWithEnemy(View v) {
        autoEnemyHitHeroWithDraw();
    }


    public void buttonNext(View v) {

        final ImageButton BUTTON_FIGHT = (ImageButton) findViewById(R.id.button_start_game_fight);
        TextView alienLifeScreen = (TextView) findViewById(R.id.alien_screen);
        TextView predatorLifeScreen = (TextView) findViewById(R.id.predator_screen);
        TextView drawScreen = (TextView) findViewById(R.id.draw_screen);
        TextView roundScreen = (TextView) findViewById(R.id.round_screen);
        TextView statusScreen = (TextView) findViewById(R.id.status_screen);


        showToastMessageEvaluation(); //shows evaluation


        v.setVisibility(View.GONE);//HIDE Button

        mRoundValue++;//sets the round value
        alienLifeScreen.setText(String.valueOf(mAlienLifeValue));
        predatorLifeScreen.setText(String.valueOf(mPredatorLifeValue));
        drawScreen.setText(String.valueOf(mDraw));
        roundScreen.setText(String.valueOf(mRoundValue));


        switch (createRandomNumber(5, 1)) {
            case 1:
                statusScreen.setText(R.string.help_hit_value);
                break;
            case 2:
                statusScreen.setText(R.string.help_life_increase_by_one_in_case_of_draw);
                break;
            case 3:
                statusScreen.setText(R.string.help_next_hit_after_draw);
                break;
            case 4:
                statusScreen.setText(R.string.help_rules_are_simple);
                break;
            case 5:
                statusScreen.setText(R.string.help_the_higher_value_wins);
                break;
        }

        //delay the creation of the buttons at 2secs
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                BUTTON_FIGHT.setVisibility(View.VISIBLE);

            }
        }, BUTTON_DURATION_TIME_1500);


    }


    public void showToastMessageEvaluation() {

        String hero;
        String message = "";
        int damage;


        if (mAlienHitValue > mPredatorHitValue) {
            damage = mAlienHitValue - mPredatorHitValue;
            hero = getString(R.string.predator);
            //checks if the earlier round was a draw round
            if (mLastRoundWasADraw) {
                damage *= mDraw;
                mPredatorLifeValue -= damage;
                mLastRoundWasADraw = false;
                mDraw = 1;
                message = getString(R.string.evaluation, hero, damage);

            } else {
                mPredatorLifeValue -= damage;
                message = getString(R.string.evaluation, hero, damage);
            }

        }
        if (mAlienHitValue < mPredatorHitValue) {
            damage = mPredatorHitValue - mAlienHitValue;
            hero = getString(R.string.alien);


            //checks if the earlier round was a draw round
            if (mLastRoundWasADraw) {
                damage *= mDraw; //draw multiplier
                mAlienLifeValue -= damage;
                mLastRoundWasADraw = false;
                mDraw = 1;
                message = getString(R.string.evaluation, hero, damage);
            } else {
                mAlienLifeValue -= damage;
                message = getString(R.string.evaluation, hero, damage);
            }

        }
        if (mAlienHitValue == mPredatorHitValue) {
            message = getString(R.string.its_a_draw);
            if (mPredatorLifeValue < MAX_ENERGY) {
                mPredatorLifeValue++;//life will increase by one if smaller then 10 LP
            }
            if (mAlienLifeValue < MAX_ENERGY) {
                mAlienLifeValue++;//life will increase by one if smaller then 10 LP
            }
            mDraw++;//sets the draw counter
            mLastRoundWasADraw = true;//set this round as draw round

        }


        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_message_evaluation,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(message);

        final Toast TOAST = new Toast(getApplicationContext());


        int myInteger = getResources().getInteger(R.integer.toast_vertical);

        TOAST.setDuration(Toast.LENGTH_SHORT);
        TOAST.setGravity(Gravity.CENTER_VERTICAL, 0, myInteger);
        TOAST.setView(layout);
        TOAST.show();

        //shows the toast only one second
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TOAST.cancel();
            }
        }, TOAST_DURATION_TIME_1000);


    }


    public void showSimpleToastMessage(String message) {

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_message,
                (ViewGroup) findViewById(R.id.custom_toast_container));


        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(message);

        final Toast TOAST = new Toast(getApplicationContext());

        int myInteger = getResources().getInteger(R.integer.toast_vertical);

        TOAST.setDuration(Toast.LENGTH_SHORT);
        TOAST.setGravity(Gravity.CENTER_VERTICAL, 0, myInteger);
        TOAST.setView(layout);
        TOAST.show();

        //shows the toast only one second
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TOAST.cancel();
            }
        }, TOAST_DURATION_TIME_1000);


    }


    public void autoEnemyHitHero() {
        int randomHit = createRandomNumber(KICK, PUNCH);
        //checks id of the pressed button
        switch (randomHit) {
            //in case of kick button
            case KICK:
                //checks the chosen hero of the player for playing the right animation
                if (mAlienImageSelected) {
                    if (!doesAlienDieThisRound()) {
                        predatorKickAlienAnim();
                    } else {
                        predatorKickAlienWithWinAnim();
                    }
                } else {
                    if (!doesPredatorDieThisRound()) {
                        alienKickPredatorAnim();//normal hit
                    } else {
                        alienKickPredatorWithWinAnim();//alien wins
                    }


                }

                break;

            //in case of punch button
            case PUNCH:

                if (mAlienImageSelected) {
                    if (!doesAlienDieThisRound()) {
                        predatorPunchAlienAnim();
                    } else {
                        predatorPunchAlienWithWinAnim();
                    }


                } else {
                    if (!doesPredatorDieThisRound()) {
                        alienPunchPredatorAnim();
                    } else {
                        alienKickPredatorWithWinAnim();
                    }

                }

                break;

            default:
                break;


        }

    }

    public void autoEnemyHitHeroWithDraw() {

        int randomHit = createRandomNumber(KICK, PUNCH);
        //checks id of the pressed button
        switch (randomHit) {
            //in case of kick button
            case KICK:
                //checks the chosen hero of the player for playing the right animation
                if (mAlienImageSelected) {
                    predatorKickAlienWithDrawAnim();
                } else {
                    alienKickPredatorWithDrawAnim();

                }

                break;

            //in case of punch button
            case PUNCH:

                if (mAlienImageSelected) {
                    predatorPunchAlienWithDrawAnim();
                } else {
                    alienPunchPredatorWithDrawAnim();
                }

                break;

            default:
                break;


        }


    }


    public void enemyHitHeroWithDraw(View v) {

        //checks id of the pressed button
        switch (v.getId()) {
            //in case of kick button
            case R.id.button_action_kick:
                //checks the chosen hero of the player for playing the right animation
                if (!mAlienImageSelected) {
                    predatorKickAlienWithDrawAnim();
                } else {
                    alienKickPredatorWithDrawAnim();

                }

                break;

            //in case of punch button
            case R.id.button_action_punch:

                if (!mAlienImageSelected) {
                    predatorPunchAlienWithDrawAnim();
                } else {
                    alienPunchPredatorWithDrawAnim();
                }

                break;

            default:
                break;


        }


    }

    public void enemyDrawWithHero(View v) {

        //alienAndPredatorDrawAnim();
        enemyHitHeroWithDraw(v);

    }


    public void enemyHitHero(View v) {

        //checks id of the pressed button
        switch (v.getId()) {
            //in case of kick button
            case R.id.button_action_kick:
                //checks the chosen hero of the player for playing the right animation
                if (!mAlienImageSelected) {
                    if (!doesAlienDieThisRound()) {
                        predatorKickAlienAnim();
                    } else {
                        predatorKickAlienWithWinAnim();
                    }
                } else {
                    if (!doesPredatorDieThisRound()) {
                        alienKickPredatorAnim();
                    } else {
                        alienKickPredatorWithWinAnim();
                    }

                }

                break;

            //in case of punch button
            case R.id.button_action_punch:

                if (!mAlienImageSelected) {
                    if (!doesAlienDieThisRound()) {
                        predatorPunchAlienAnim();
                    } else {
                        predatorPunchAlienWithWinAnim();
                    }

                } else {
                    if (!doesPredatorDieThisRound()) {
                        alienPunchPredatorAnim();
                    } else {
                        alienKickPredatorWithWinAnim();
                    }

                }

                break;

            default:
                break;


        }


    }

    public void heroHitEnemyWithDraw(View v) {

        //checks id of the pressed button
        switch (v.getId()) {
            //in case of kick button
            case R.id.button_action_kick:
                //checks the chosen hero of the player for playing the right animation
                if (mAlienImageSelected) {
                    alienKickPredatorWithDrawAnim();
                } else {
                    predatorKickAlienWithDrawAnim();

                }

                break;

            //in case of punch button
            case R.id.button_action_punch:

                if (mAlienImageSelected) {
                    alienPunchPredatorWithDrawAnim();
                } else {
                    predatorPunchAlienWithDrawAnim();
                }

                break;

            default:
                break;


        }


    }

    public void alienKickPredatorWithDrawAnim() {
        ImageView alienHitTail = (ImageView) findViewById(alien);
        alienHitTail.setBackgroundResource(R.drawable.alien_hit_tail);
        alienAnimation = (AnimationDrawable) alienHitTail.getBackground();

        ImageView predatorDoge = (ImageView) findViewById(predator);
        predatorDoge.setBackgroundResource(R.drawable.predator_doge);
        predatorAnimation = (AnimationDrawable) predatorDoge.getBackground();

        alienAnimation.stop();
        alienAnimation.start();
        predatorAnimation.stop();
        predatorAnimation.start();
        playSound(R.raw.alien_tail_hit);
        playSound(R.raw.predator_doge);

    }

    public void alienAndPredatorDrawAnim() {

        ImageView alienDoge = (ImageView) findViewById(alien);
        alienDoge.setBackgroundResource(R.drawable.alien_doge);
        alienAnimation = (AnimationDrawable) alienDoge.getBackground();

        ImageView predatorDoge = (ImageView) findViewById(predator);
        predatorDoge.setBackgroundResource(R.drawable.predator_doge);
        predatorAnimation = (AnimationDrawable) predatorDoge.getBackground();

        alienAnimation.stop();
        alienAnimation.start();
        predatorAnimation.stop();
        predatorAnimation.start();
        playSound(R.raw.predator_doge);
        playSound(R.raw.alien_doge);

    }

    public void alienPunchPredatorWithDrawAnim() {

        ImageView alienHitTail = (ImageView) findViewById(alien);
        alienHitTail.setBackgroundResource(R.drawable.alien_hit_claw);
        alienAnimation = (AnimationDrawable) alienHitTail.getBackground();

        ImageView predatorDoge = (ImageView) findViewById(predator);
        predatorDoge.setBackgroundResource(R.drawable.predator_doge);
        predatorAnimation = (AnimationDrawable) predatorDoge.getBackground();

        alienAnimation.stop();
        alienAnimation.start();
        predatorAnimation.stop();
        predatorAnimation.start();
        playSound(R.raw.alien_claw_hit);
        playSound(R.raw.predator_doge);

    }

    public void predatorKickAlienWithDrawAnim() {

        ImageView alienHitTail = (ImageView) findViewById(alien);
        alienHitTail.setBackgroundResource(R.drawable.alien_doge);
        alienAnimation = (AnimationDrawable) alienHitTail.getBackground();

        ImageView predatorDoge = (ImageView) findViewById(predator);
        predatorDoge.setBackgroundResource(R.drawable.predator_kick);
        predatorAnimation = (AnimationDrawable) predatorDoge.getBackground();


        predatorAnimation.stop();
        predatorAnimation.start();
        alienAnimation.stop();
        alienAnimation.start();

        playSound(R.raw.predator_kick);
        playSound(R.raw.alien_doge);

    }

    public void predatorPunchAlienWithDrawAnim() {

        ImageView alienHitTail = (ImageView) findViewById(alien);
        alienHitTail.setBackgroundResource(R.drawable.alien_doge);
        alienAnimation = (AnimationDrawable) alienHitTail.getBackground();

        ImageView predatorDoge = (ImageView) findViewById(predator);
        predatorDoge.setBackgroundResource(R.drawable.predator_punch);
        predatorAnimation = (AnimationDrawable) predatorDoge.getBackground();


        predatorAnimation.stop();
        predatorAnimation.start();
        alienAnimation.stop();
        alienAnimation.start();

        playSound(R.raw.predator_hit);
        playSound(R.raw.alien_doge);

    }


    public void heroHitEnemy(View v) {


//checks id of the pressed button
        switch (v.getId()) {
            //in case of kick button
            case R.id.button_action_kick:
                //checks the chosen hero of the player for playing the right animation
                if (mAlienImageSelected) {
                    if (!doesPredatorDieThisRound()) {
                        alienKickPredatorAnim();
                    } else {
                        //if alien wins with kick
                        alienKickPredatorWithWinAnim();
                    }

                } else {
                    if (!doesAlienDieThisRound()) {
                        predatorKickAlienAnim();
                    } else {
                        //if predator wins with kick
                        predatorKickAlienWithWinAnim();
                    }


                }

                break;

            //in case of punch button
            case R.id.button_action_punch:

                if (mAlienImageSelected) {
                    if (!doesPredatorDieThisRound()) {
                        alienPunchPredatorAnim();
                    } else {
                        //if alien wins with punch
                        alienPunchPredatorWithWinAnim();
                    }
                } else {
                    if (!doesAlienDieThisRound()) {
                        predatorPunchAlienAnim();
                    } else {
                        //if predator wins with punch
                        predatorPunchAlienWithWinAnim();
                    }
                }

                break;

            default:
                break;


        }

    }


    public boolean doesAlienDieThisRound() {
        TextView alienLifeScreen = (TextView) findViewById(R.id.alien_screen);

        int dmg;


        if (mPredatorHitValue > mAlienHitValue) {

            if (mLastRoundWasADraw) {

                dmg = (mPredatorHitValue - mAlienHitValue) * mDraw;
                Log.e("dmg of pred with draw", String.valueOf(dmg));
            } else {
                dmg = mPredatorHitValue - mAlienHitValue;
                Log.e("dmg of pred", String.valueOf(dmg));
            }

            if (mAlienLifeValue - dmg > 0) {
                return false;//alien lives
            } else {
                alienLifeScreen.setText(String.valueOf(0));
                mAlienDead = true;
                return true;//alien died
            }

        } else {
            return false;//alien lives
        }

    }


    public boolean doesPredatorDieThisRound() {
        TextView predatorLifeScreen = (TextView) findViewById(R.id.predator_screen);
        int dmg;
        if (mAlienHitValue > mPredatorHitValue) {

            if (mLastRoundWasADraw) {

                dmg = (mAlienHitValue - mPredatorHitValue) * mDraw;
                Log.e("dmg of Alien with draw", String.valueOf(dmg));
            } else {
                dmg = mAlienHitValue - mPredatorHitValue;
                Log.e("dmg of Alien", String.valueOf(dmg));
            }


            if (mPredatorLifeValue - dmg > 0) {
                return false;//predator lives
            } else {
                mPredatorDead = true;
                predatorLifeScreen.setText(String.valueOf(0));
                return true;//predator died
            }

        } else {
            return false;//predator lives
        }

    }


    public void alienKickPredatorWithWinAnim() {

        ImageView alienHitTail = (ImageView) findViewById(alien);
        alienHitTail.setBackgroundResource(R.drawable.alien_hit_tail_win);
        alienAnimation = (AnimationDrawable) alienHitTail.getBackground();


        alienAnimation.stop();
        alienAnimation.start();
        predatorDieAnim();
        playSound(R.raw.alien_tail_hit);
        playSound(R.raw.alien_win);

    }


    public void predatorKickAlienWithWinAnim() {

        ImageView predatorKick = (ImageView) findViewById(predator);
        predatorKick.setBackgroundResource(R.drawable.predator_kick_win);
        predatorAnimation = (AnimationDrawable) predatorKick.getBackground();

        predatorAnimation.stop();
        predatorAnimation.start();
        alienDieAnim();
        playSound(R.raw.predator_kick);
        playSound(R.raw.predator_win);

    }

    public void alienPunchPredatorWithWinAnim() {

        ImageView alienHitClaw = (ImageView) findViewById(alien);
        alienHitClaw.setBackgroundResource(R.drawable.alien_hit_claw_win);
        alienAnimation = (AnimationDrawable) alienHitClaw.getBackground();


        alienAnimation.stop();
        alienAnimation.start();
        predatorDieAnim();
        playSound(R.raw.alien_claw_hit);
        playSound(R.raw.alien_win);

    }

    public void predatorPunchAlienWithWinAnim() {

        ImageView predatorPunch = (ImageView) findViewById(predator);
        predatorPunch.setBackgroundResource(R.drawable.predator_punch_win);
        predatorAnimation = (AnimationDrawable) predatorPunch.getBackground();

        predatorAnimation.stop();
        predatorAnimation.start();
        alienDieAnim();
        playSound(R.raw.predator_hit);
        playSound(R.raw.predator_win);

    }


    public int createRandomNumber(int max, int min) {

        int rand;
        Random r = new Random();
        rand = r.nextInt(max - min + 1) + min;
        Log.e("RandomNumber ", String.valueOf(rand));
        return rand;


    }


    public void alienKickPredatorAnim() {

        ImageView alienHitTail = (ImageView) findViewById(alien);
        alienHitTail.setBackgroundResource(R.drawable.alien_hit_tail);
        alienAnimation = (AnimationDrawable) alienHitTail.getBackground();


        alienAnimation.stop();
        alienAnimation.start();

        predatorGetHitAnim();
        playSound(R.raw.predator_kick);


    }

    public void alienPunchPredatorAnim() {


        ImageView alienHitClaw = (ImageView) findViewById(alien);
        alienHitClaw.setBackgroundResource(R.drawable.alien_hit_claw);
        alienAnimation = (AnimationDrawable) alienHitClaw.getBackground();


        alienAnimation.stop();
        alienAnimation.start();

        predatorGetHitAnim();

        playSound(R.raw.alien_claw_hit);


    }

    public void alienDieAnim() {

        ImageView alienDie = (ImageView) findViewById(alien);
        alienDie.setBackgroundResource(R.drawable.alien_dead);
        alienAnimation = (AnimationDrawable) alienDie.getBackground();

        Drawable startFrame = alienAnimation.getFrame(alienAnimation.getNumberOfFrames() - 1);


        alienAnimation.stop();
        alienAnimation.start();
        mAlienDead = true;

        playSound(R.raw.alien_die);


    }

    public void alienGetHitAnim() {

        ImageView alienGetHit = (ImageView) findViewById(alien);
        alienGetHit.setBackgroundResource(R.drawable.alien_get_hit);
        alienAnimation = (AnimationDrawable) alienGetHit.getBackground();


        alienAnimation.stop();
        alienAnimation.start();

        playSound(R.raw.alien_get_hit);


    }


    public void alienWinAnim() {

        ImageView predatorWin = (ImageView) findViewById(alien);
        predatorWin.setBackgroundResource(R.drawable.alien_win);
        alienAnimation = (AnimationDrawable) predatorWin.getBackground();


        alienAnimation.stop();
        alienAnimation.start();


        playSound(R.raw.alien_win);


    }


    public void playSound(int resId) {

        Context context = this;


        MediaPlayer mp;
        mp = MediaPlayer.create(context, resId);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
            }

        });
        mp.start();

    }


    public void buttonPredatorHit(View v) {

        ImageView predatorHit = (ImageView) findViewById(predator);
        predatorHit.setBackgroundResource(R.drawable.predator_punch);
        predatorAnimation = (AnimationDrawable) predatorHit.getBackground();

        MediaPlayer mediaPlayerPredator = MediaPlayer.create(this, R.raw.predator_hit);
        MediaPlayer mediaPlayerAlien = MediaPlayer.create(this, R.raw.alien_cry_after_hit);


        Drawable startFrame = predatorAnimation.getFrame(predatorAnimation.getNumberOfFrames() - 1);


        if (predatorAnimation.isRunning() && predatorAnimation.getCurrent() == startFrame) {
            predatorAnimation.stop();
            Log.e("Animation1: ", predatorAnimation.getCurrent().toString());
            predatorAnimation.start();
            alienGetHitAnim();
            mediaPlayerPredator.start();
            mediaPlayerAlien.start();

        } else {
            predatorAnimation.stop();
            predatorAnimation.start();
            Log.e("Animation2: ", startFrame.toString());
            alienGetHitAnim();
            mediaPlayerPredator.start();
            mediaPlayerAlien.start();


        }

    }


    public void buttonPredatorKick(View v) {

        ImageView predatorKick = (ImageView) findViewById(predator);
        predatorKick.setBackgroundResource(R.drawable.predator_kick_win);
        predatorAnimation = (AnimationDrawable) predatorKick.getBackground();
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.alien_die);
        Drawable startFrame = predatorAnimation.getFrame(predatorAnimation.getNumberOfFrames() - 1);


        if (predatorAnimation.isRunning() && predatorAnimation.getCurrent() == startFrame) {
            predatorAnimation.stop();
            Log.e("Animation1: ", predatorAnimation.getCurrent().toString());
            predatorAnimation.start();

            alienDieAnim();
            mediaPlayer.start();


        } else {
            predatorAnimation.stop();
            predatorAnimation.start();
            Log.e("Animation2: ", startFrame.toString());
            alienDieAnim();
            mediaPlayer.start();


        }

    }


    public void buttonPredatorDoge(View v) {

        ImageView predatorDoge = (ImageView) findViewById(predator);
        predatorDoge.setBackgroundResource(R.drawable.predator_doge);
        predatorAnimation = (AnimationDrawable) predatorDoge.getBackground();

        Drawable startFrame = predatorAnimation.getFrame(predatorAnimation.getNumberOfFrames() - 1);


        if (predatorAnimation.isRunning() && predatorAnimation.getCurrent() == startFrame) {
            predatorAnimation.stop();
            Log.e("Animation1: ", predatorAnimation.getCurrent().toString());
            predatorAnimation.start();

        } else {
            predatorAnimation.stop();
            predatorAnimation.start();
            Log.e("Animation2: ", startFrame.toString());


        }

    }


    public void predatorDieAnim() {

        ImageView predatorDie = (ImageView) findViewById(predator);
        predatorDie.setBackgroundResource(R.drawable.predator_die);
        predatorAnimation = (AnimationDrawable) predatorDie.getBackground();

        predatorAnimation.stop();
        predatorAnimation.start();
        mPredatorDead = true;
        playSound(R.raw.predator_die);

    }


    public void predatorGetHitAnim() {

        ImageView predatorGetHit = (ImageView) findViewById(predator);
        predatorGetHit.setBackgroundResource(R.drawable.predator_get_hit);
        predatorAnimation = (AnimationDrawable) predatorGetHit.getBackground();


        predatorAnimation.stop();
        predatorAnimation.start();
        playSound(R.raw.predator_get_hit);


    }


    public void predatorWinAnim() {

        ImageView predatorWin = (ImageView) findViewById(predator);
        predatorWin.setBackgroundResource(R.drawable.predator_win);
        predatorAnimation = (AnimationDrawable) predatorWin.getBackground();


        predatorAnimation.stop();
        predatorAnimation.start();
        playSound(R.raw.predator_win);


    }


    public void predatorKickAlienAnim() {
        ImageView predatorKick = (ImageView) findViewById(predator);
        predatorKick.setBackgroundResource(R.drawable.predator_kick);
        predatorAnimation = (AnimationDrawable) predatorKick.getBackground();

        predatorAnimation.stop();
        predatorAnimation.start();
        alienGetHitAnim();

        playSound(R.raw.predator_kick);


    }


    public void predatorPunchAlienAnim() {
        ImageView predatorHit = (ImageView) findViewById(predator);
        predatorHit.setBackgroundResource(R.drawable.predator_punch);
        predatorAnimation = (AnimationDrawable) predatorHit.getBackground();

        predatorAnimation.stop();
        predatorAnimation.start();
        alienGetHitAnim();

        playSound(R.raw.predator_hit);


    }


    public void deleteActionButtons() {

        ViewGroup buttonPanelAllButton = (ViewGroup) findViewById(R.id.panel_action_buttons);
        buttonPanelAllButton.setVisibility(View.GONE);

    }


    public void createHitButton() {

        ImageButton buttonHit = (ImageButton) findViewById(R.id.button_hit);
        buttonHit.setVisibility(View.VISIBLE);
    }


    //Creates the damage information text from chosen hero
    public void setAlienStatusText(int dmg) {
        TextView screenText = (TextView) findViewById(R.id.status_screen);
        screenText.setText(getString(R.string.alien_hit_creates_damage, dmg));
    }

    public void setAlienStatusTextIfDead() {
        TextView screenText = (TextView) findViewById(R.id.status_screen);
        screenText.setText(getString(R.string.alien_beaten));
    }

    //Creates the damage information text from chosen hero
    public void setPredatorStatusText(int dmg) {
        TextView screenText = (TextView) findViewById(R.id.status_screen);
        screenText.setText(getString(R.string.predator_hit_creates_damage, dmg));
    }

    //Creates the damage information text from chosen hero
    public void setPredatorStatusTextIfDead() {
        TextView screenText = (TextView) findViewById(R.id.status_screen);
        screenText.setText(getString(R.string.predator_beaten));
    }


    //Creates the damage information text from chosen hero
    public void setRoundStatusText(int round) {
        TextView screenText = (TextView) findViewById(R.id.round_screen);
        screenText.setText(String.valueOf(round));
    }

}
