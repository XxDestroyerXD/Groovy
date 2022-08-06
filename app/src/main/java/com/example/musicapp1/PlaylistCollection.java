package com.example.musicapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class PlaylistCollection extends AppCompatActivity {
    public static ArrayList<Song> playlist1 = HomeActivity.playlist1;
    public static ArrayList<Song> playlist2 = HomeActivity.playlist2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_collection);
    }

    public void gotoplaylist1 (View view){
        Intent intent = new Intent(this,PlaylistActivity.class);
        intent.putExtra("movetoplaylist",1);
        startActivity(intent);
    }
    public void gotoplaylist2 (View view){
        Intent intent = new Intent(this,PlaylistActivity.class);
        intent.putExtra("movetoplaylist",2);
        startActivity(intent);
    }
    public void gotohomeactivity (View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}