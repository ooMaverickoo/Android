package com.example.android.musicalstructureapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoBooksActivity extends AppCompatActivity {

    private static final String SONG_NUMBER = "songNumber";

    private PlayList myMusic = new PlayList(3);
    private Song song1;
    private Song song2;
    private Song song3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_books);


        ImageView coverBig = (ImageView) findViewById(R.id.cover_image_play);
        TextView titleView = (TextView) findViewById(R.id.title_view);
        TextView artistView = (TextView) findViewById(R.id.artist_view);
        TextView lengthView = (TextView) findViewById(R.id.length_view);
        TextView albumView = (TextView) findViewById(R.id.album_view);

        Bundle bundle = getIntent().getExtras();

        song1 = new Song(getString(R.string.first_book_title), getString(R.string.first_author),
                getString(R.string.audio_book), getResources().getInteger(R.integer.first_book_length),
                R.drawable.elon_musk_cover, R.drawable.elon_musk_cover_big);
        song2 = new Song(getString(R.string.second_book_title), getString(R.string.second_author),
                getString(R.string.audio_book), getResources().getInteger(R.integer.second_book_length),
                R.drawable.stephen_king_cover, R.drawable.stephen_king_cover_big);
        song3 = new Song(getString(R.string.third_book_title), getString(R.string.third_author),
                getString(R.string.audio_book), getResources().getInteger(R.integer.third_book_length),
                R.drawable.margaret_atwood, R.drawable.margaret_atwood_big);


        Log.e("Song 1 ", String.valueOf(song1.toString()));
        Log.e("Song 2 ", String.valueOf(song2.toString()));
        Log.e("Song 3 ", String.valueOf(song3.toString()));


        myMusic.add(song1);
        myMusic.add(song2);
        myMusic.add(song3);


        if (bundle.getInt(SONG_NUMBER) != 0) {

            coverBig.setImageResource(myMusic.get(bundle.getInt(SONG_NUMBER) - 1).getCoverBig());

            titleView.setText(myMusic.get(bundle.getInt(SONG_NUMBER) - 1).getName());
            artistView.setText(myMusic.get(bundle.getInt(SONG_NUMBER) - 1).getArtist());
            albumView.setText(myMusic.get(bundle.getInt(SONG_NUMBER) - 1).getAlbum());
            lengthView.setText(myMusic.get(bundle.getInt(SONG_NUMBER) - 1).getLengthFormatted());


        }


    }
}