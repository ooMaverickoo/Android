package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView myMusic = (TextView) findViewById(R.id.my_music_dir);
        final TextView myAudioBooks = (TextView) findViewById(R.id.audio_books_dir);


        myMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myMusicDir = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(myMusicDir);
            }
        });


        myAudioBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myAudioBooksDir = new Intent(MainActivity.this, AudioBooksActivity.class);
                startActivity(myAudioBooksDir);
            }
        });


    }


}
