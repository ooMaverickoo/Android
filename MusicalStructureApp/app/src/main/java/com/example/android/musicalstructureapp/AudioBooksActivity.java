package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class AudioBooksActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String SONG_NUMBER = "songNumber";

    private PlayList myMusic = new PlayList(3);
    private Song song1;
    private Song song2;
    private Song song3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_books);

        song1 = new Song(getString(R.string.first_book_title), getString(R.string.first_author),
                getString(R.string.audio_book), getResources().getInteger(R.integer.first_book_length),
                R.drawable.elon_musk_cover, R.drawable.elon_musk_cover_big);
        song2 = new Song(getString(R.string.second_title), getString(R.string.second_artist),
                getString(R.string.second_album), getResources().getInteger(R.integer.second_book_length),
                R.drawable.stephen_king_cover, R.drawable.stephen_king_cover_big);
        song3 = new Song(getString(R.string.third_title), getString(R.string.third_artist),
                getString(R.string.third_album), getResources().getInteger(R.integer.third_book_length),
                R.drawable.margaret_atwood, R.drawable.margaret_atwood_big);


        Log.e("Song 1 ", String.valueOf(song1.toString()));
        Log.e("Song 2 ", String.valueOf(song2.toString()));
        Log.e("Song 3 ", String.valueOf(song3.toString()));


        myMusic.add(song1);
        myMusic.add(song2);
        myMusic.add(song3);


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
        }

        if (!kindOfButton) {
            Intent playActivityScreen = new Intent(AudioBooksActivity.this, PlayBooksActivity.class);
            playActivityScreen.putExtra(SONG_NUMBER, buttonNumber);
            startActivity(playActivityScreen);
        } else {
            Intent playActivityScreen = new Intent(AudioBooksActivity.this, InfoBooksActivity.class);
            playActivityScreen.putExtra(SONG_NUMBER, buttonNumber);
            startActivity(playActivityScreen);
        }


    }
}
