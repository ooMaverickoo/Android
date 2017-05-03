package com.example.android.musicalstructureapp;

import java.util.Locale;

/**
 * Created by Christian on 01.05.2017.
 */

//This class was created with help of stackoverflow website. I modified it for my own use.

public class Song {

    private String name;
    private String artist;
    private String album;
    private int length;

    private int coverSmall;
    private int coverBig;

    public int getCoverSmall() {
        return coverSmall;
    }

    public void setCoverSmall(int coverSmall) {
        this.coverSmall = coverSmall;
    }

    public int getCoverBig() {
        return coverBig;
    }

    public void setCoverBig(int coverBig) {
        this.coverBig = coverBig;
    }


    public Song(String songName, String artistName, String albumName, int trackLength, int imageSmall, int imageBig) {
        this.name = songName;
        this.artist = artistName;
        this.album = albumName;
        this.length = trackLength;
        this.coverSmall = imageSmall;
        this.coverBig = imageBig;
    }

    public void setName(String songName) {
        name = songName;
    }

    public String getName() {
        return name;
    }

    public void setArtist(String artistName) {
        artist = artistName;
    }

    public String getArtist() {
        return artist;
    }

    public void setAlbum(String albumName) {
        album = albumName;
    }

    public String getAlbum() {

        return album;
    }

    public void setLength(int h, int m, int s) {
        length = (h * 3600 + m * 60 + s);
        if (h == 0) {
            length = (m * 60 + s);
        }
    }

    public int getLength() {

        return length;
    }

    public String toString() {
        return "Title: " + getName() + ", Artist: " + getArtist()
                + ", Album: " + getAlbum() + ", Track Length: " + getLength();
    }


    public String getLengthFormatted() {
        String lengthString;
        int min;
        int hour;
        int sec;


        min = length / 60;
        sec = length % 60;
        hour = min / 60;
        min = min % 60;


        lengthString = String.format(Locale.getDefault(), "%02d", hour) + ":" +
                String.format(Locale.getDefault(), "%02d", min) + ":" +
                String.format(Locale.getDefault(), "%02d", sec);


        return lengthString;
    }

}
