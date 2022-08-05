package com.example.musicapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class Song_Lyrics extends AppCompatActivity {

    TextView songName, lyrics ;
    private int currentIndex ;
    Songcollection songCollection = new Songcollection();
    private String songLyrics;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_lyrics);
        songName = findViewById(R.id.songName);
        lyrics = findViewById(R.id.lyrics);
        Bundle songData = this.getIntent().getExtras();
        currentIndex = songData.getInt("key");
        displayLyricsBasedOnIndex();

    }

    private void displayLyricsBasedOnIndex() {
        Song song = songCollection.getCurrentSongs(currentIndex);

        songLyrics = song.getLyrics();
        lyrics.setText(songLyrics);
        songName.setText(song.getTitle());
    }


}