package com.example.musicapp1;

import android.media.MediaPlayer;
import android.widget.Button;

public class Song {

    private String id;
    private String title;
    private String artiste;
    private String fileLink;
    private double songlength;
    private int drawable;

    private String coverArt;

    public Song(String id, String title, String artiste, String fileLink, double songlength, int drawable, String coverArt) {
        this.id = id;
        this.title = title;
        this.artiste = artiste;
        this.fileLink = fileLink;
        this.songlength = songlength;
        this.drawable = drawable ;
        this.coverArt = coverArt;
    }



    public String getId(){return id;}
    public String getTitle(){return title;}
    public String getArtiste(){return artiste;}
    public String getFilelink(){return fileLink;}
    public int getDrawable(){return drawable;}
    public double getSonglength(){return songlength;}
    public String getCoverArt(){return coverArt;}




}
