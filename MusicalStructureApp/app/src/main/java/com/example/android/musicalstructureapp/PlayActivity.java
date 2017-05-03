package com.example.android.musicalstructureapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

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
        setContentView(R.layout.activity_play);

        ImageView coverBig = (ImageView) findViewById(R.id.cover_image_play);
        TextView titleView = (TextView) findViewById(R.id.title_view);
        TextView artistView = (TextView) findViewById(R.id.artist_view);
        TextView lengthView = (TextView) findViewById(R.id.length_view);


        //Set Buttons for Toast Messages, cause there is no function for this buttons
        ImageButton playButton = (ImageButton) findViewById(R.id.button_play);
        playButton.setOnClickListener(this);

        ImageButton pauseButton = (ImageButton) findViewById(R.id.button_pause);
        pauseButton.setOnClickListener(this);

        ImageButton rewindButton = (ImageButton) findViewById(R.id.button_rewind);
        rewindButton.setOnClickListener(this);

        ImageButton forwardButton = (ImageButton) findViewById(R.id.button_forward);
        forwardButton.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();

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


        if (bundle.getInt(SONG_NUMBER) != 0) {

            coverBig.setImageResource(myMusic.get(bundle.getInt(SONG_NUMBER) - 1).getCoverBig());
            titleView.setText(myMusic.get(bundle.getInt(SONG_NUMBER) - 1).getName());
            artistView.setText(myMusic.get(bundle.getInt(SONG_NUMBER) - 1).getArtist());
            lengthView.setText(myMusic.get(bundle.getInt(SONG_NUMBER) - 1).getLengthFormatted());

        }


    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.button_play:
                Toast.makeText(
                        this, getText(R.string.play_button_pressed), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_pause:
                Toast.makeText(
                        this, getText(R.string.pause_button_pressed), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_rewind:
                Toast.makeText(
                        this, getText(R.string.rewind_button_pressed), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_forward:
                Toast.makeText(
                        this, getText(R.string.forward_button_pressed), Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
