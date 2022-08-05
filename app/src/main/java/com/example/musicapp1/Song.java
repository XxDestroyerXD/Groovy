package com.example.musicapp1;

import android.media.MediaPlayer;
import android.widget.Button;

public class Song {

    private String id;
    private String title;
    private String artiste;
    private String filelink;
    private double songlength;
    private int drawable;
    private String lyrics;
    private String coverArt;

    public Song(String id, String title, String artiste, String filelink, double songlength, int drawable, String lyrics, String coverArt) {
        this.id = id;
        this.title = title;
        this.artiste = artiste;
        this.filelink = filelink;
        this.songlength = songlength;
        this.drawable = drawable ;
        this.lyrics = lyrics;
        this.coverArt = coverArt;
    }



    public String getId(){return id;}
    public String getTitle(){return title;}
    public String getArtiste(){return artiste;}
    public String getFilelink(){return filelink;}
    public int getDrawable(){return drawable;}
    public double getSonglength(){return songlength;}
    public String getLyrics(){return lyrics;}
    public String getCoverArt(){return coverArt;}




}
