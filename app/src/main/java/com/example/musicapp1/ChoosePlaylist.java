package com.example.musicapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ChoosePlaylist extends AppCompatActivity {
    private int  playlistdata;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences2;

    public static ArrayList<Song> playlist1 = new ArrayList<Song>();
    public static ArrayList<Song> playlist2 = new ArrayList<Song>();
    ImageView addtoplaylist1;
    Songcollection songcollection = new Songcollection();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = this.getIntent().getExtras();
        setContentView(R.layout.activity_choose_playlist);
        playlistdata = (data.getInt("playlistdata"));
        addtoplaylist1 = findViewById(R.id.playlist1);
        sharedPreferences = getSharedPreferences("playList", MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences("playList2",MODE_PRIVATE);



    }

    public void addsongtoplaylist1(View view) {

        Song song = songcollection.getCurrentSongs(playlistdata);
        playlist1 = HomeActivity.playlist1;
        playlist1.add(song);
        Gson gson = new Gson();
        String json = gson.toJson(playlist1);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("list",json);
        editor.apply();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    public void addsongtoplaylist2(View view) {

        Song song = songcollection.getCurrentSongs(playlistdata);
        playlist2 = HomeActivity.playlist2;
        playlist2.add(song);
        Gson gson = new Gson();
        String json = gson.toJson(playlist2);
        SharedPreferences.Editor editor = sharedPreferences2.edit();
        editor.putString("list",json);
        editor.apply();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}