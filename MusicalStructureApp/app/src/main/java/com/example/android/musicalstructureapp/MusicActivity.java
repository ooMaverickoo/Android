package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String SONG_NUMBER = "songNumber";

    private PlayList myMusic = new PlayList(7);
    private Song song1;
    private Song song2;
    private Song song3;
    private Song song4;
    private Song song5;
    private Song song6;
    private Song song7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        song1 = new Song(getString(R.string.first_title), getString(R.string.first_artist),
                getString(R.string.first_album), getResources().getInteger(R.integer.first_title_time),
                R.drawable.shape_on_you_cover, R.drawable.shape_on_you_cover_big);
        song2 = new Song(getString(R.string.second_title), getString(R.string.second_artist),
                getString(R.string.second_album), getResources().getInteger(R.integer.second_title_time),
                R.drawable.thats_what_i_like_cover, R.drawable.thats_what_i_like_cover_big);
        song3 = new Song(getString(R.string.third_title), getString(R.string.third_artist),
                getString(R.string.third_album), getResources().getInteger(R.integer.third_title_time),
                R.drawable.i_feel_it_coming_cover, R.drawable.i_feel_it_coming_cover_big);
        song4 = new Song(getString(R.string.fourth_title), getString(R.string.fourth_artist),
                getString(R.string.fourth_album), getResources().getInteger(R.integer.fourth_title_time),
                R.drawable.rock_a_bye_cover, R.drawable.rock_a_bye_cover_big);
        song5 = new Song(getString(R.string.fifth_title), getString(R.string.fifth_artist), getString(R.string.fifth_album),
                getResources().getInteger(R.integer.fifth_title_time), R.drawable.paris_cover,
                R.drawable.paris_cover_big);
        song6 = new Song(getString(R.string.sixth_title), getString(R.string.sixth_artist),
                getString(R.string.sixth_album), getResources().getInteger(R.integer.sixth_title_time),
                R.drawable.it_aint_me_cover, R.drawable.it_aint_me_cover_big);
        song7 = new Song(getString(R.string.seventh_title), getString(R.string.seventh_artist),
                getString(R.string.seventh_album), getResources().getInteger(R.integer.seventh_title_time),
                R.drawable.chained_to_the_rhythm_cover, R.drawable.chained_to_the_rhythm_cover_big);

        Log.e("Song 1 ", String.valueOf(song1.toString()));
        Log.e("Song 2 ", String.valueOf(song2.toString()));
        Log.e("Song 3 ", String.valueOf(song3.toString()));
        Log.e("Song 4 ", String.valueOf(song4.toString()));
        Log.e("Song 5 ", String.valueOf(song5.toString()));
        Log.e("Song 6 ", String.valueOf(song6.toString()));
        Log.e("Song 7 ", String.valueOf(song7.toString()));

        myMusic.add(song1);
        myMusic.add(song2);
        myMusic.add(song3);
        myMusic.add(song4);
        myMusic.add(song5);
        myMusic.add(song6);
        myMusic.add(song7);


        ImageButton firstButton = (ImageButton) findViewById(R.id.first_button);
        firstButton.setOnClickListener(this);
        ImageButton firstButtonSettings = (ImageButton) findViewById(R.id.first_button_settings);
        firstButtonSettings.setOnClickListener(this);
        ImageButton secondButton = (ImageButton) findViewById(R.id.second_button);
        secondButton.setOnClickListener(this);
        ImageButton secondButtonSettings = (ImageButton) findViewById(R.id.second_button_settings);
        secondButtonSettings.setOnClickListener(this);
        ImageButton thirdButton = (ImageButton) findViewById(R.id.third_button);
        thirdButton.setOnClickListener(this);
        ImageButton thirdButtonSettings = (ImageButton) findViewById(R.id.third_button_settings);
        thirdButtonSettings.setOnClickListener(this);
        ImageButton fourthButton = (ImageButton) findViewById(R.id.fourth_button);
        fourthButton.setOnClickListener(this);
        ImageButton fourthButtonSettings = (ImageButton) findViewById(R.id.fourth_button_settings);
        fourthButtonSettings.setOnClickListener(this);
        ImageButton fifthButton = (ImageButton) findViewById(R.id.fifth_button);
        fifthButton.setOnClickListener(this);
        ImageButton fifthButtonSettings = (ImageButton) findViewById(R.id.fifth_button_settings);
        fifthButtonSettings.setOnClickListener(this);
        ImageButton sixthButton = (ImageButton) findViewById(R.id.sixth_button);
        sixthButton.setOnClickListener(this);
        ImageButton sixthButtonSettings = (ImageButton) findViewById(R.id.sixth_button_settings);
        sixthButtonSettings.setOnClickListener(this);
        ImageButton seventhButton = (ImageButton) findViewById(R.id.seventh_button);
        seventhButton.setOnClickListener(this);
        ImageButton seventhButtonSettings = (ImageButton) findViewById(R.id.seventh_button_settings);
        seventhButtonSettings.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        int buttonNumber = 0;
        boolean kindOfButton = false;

        switch (v.getId()) {
            case R.id.first_button:
                buttonNumber = 1;
                kindOfButton = false;
                break;
            case R.id.first_button_settings:
                buttonNumber = 1;
                kindOfButton = true;
                break;
            case R.id.second_button:
                buttonNumber = 2;
                kindOfButton = false;
                break;
            case R.id.second_button_settings:
                buttonNumber = 2;
                kindOfButton = true;
                break;
            case R.id.third_button:
                buttonNumber = 3;
                kindOfButton = false;
                break;
            case R.id.third_button_settings:
                buttonNumber = 3;
                kindOfButton = true;
                break;
            case R.id.fourth_button:
                buttonNumber = 4;
                kindOfButton = false;
                break;
            case R.id.fourth_button_settings:
                buttonNumber = 4;
                kindOfButton = true;
                break;
            case R.id.fifth_button:
                buttonNumber = 5;
                kindOfButton = false;
                break;
            case R.id.fifth_button_settings:
                buttonNumber = 5;
                kindOfButton = true;
                break;
            case R.id.sixth_button:
                buttonNumber = 6;
                kindOfButton = false;
                break;
            case R.id.sixth_button_settings:
                buttonNumber = 6;
                kindOfButton = true;
                break;
            case R.id.seventh_button:
                buttonNumber = 7;
                kindOfButton = false;
                break;

            case R.id.seventh_button_settings:
                buttonNumber = 7;
                kindOfButton = true;
                break;


        }

        if (!kindOfButton) {
            Intent playActivityScreen = new Intent(MusicActivity.this, PlayActivity.class);
            playActivityScreen.putExtra(SONG_NUMBER, buttonNumber);
            startActivity(playActivityScreen);
        } else {
            Intent playActivityScreen = new Intent(MusicActivity.this, InfoActivity.class);
            playActivityScreen.putExtra(SONG_NUMBER, buttonNumber);
            startActivity(playActivityScreen);
        }


    }
}
