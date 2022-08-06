package com.example.musicapp1;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import java.util.ArrayList;

public class PlaylistActivity extends AppCompatActivity {
RecyclerView playlistView;
PlaylistAdapter songAdapter;
int playlistcheck;
    public static ArrayList<Song> playlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        playlistView =  findViewById(R.id.recyclerview);

        Bundle playlistData = this.getIntent().getExtras();
        playlistcheck = playlistData.getInt("movetoplaylist");


        if(playlistcheck == 1){
            songAdapter = new PlaylistAdapter(HomeActivity.playlist1);
            playlist = HomeActivity.playlist1;
        }

        else{
            songAdapter = new PlaylistAdapter(HomeActivity.playlist2);
            playlist = HomeActivity.playlist2;
        }

        playlistView.setAdapter(songAdapter);
        playlistView.setLayoutManager(new LinearLayoutManager(this));
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                songAdapter.getFilter().filter(newText);
                return false;
            }
        });


    }

    public void removeAll(View view) {
        if(playlistcheck == 1){
            playlist.clear();
            SharedPreferences sharedPreferences = getSharedPreferences("playList",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear().apply();
            songAdapter.notifyDataSetChanged();

        }

        else{
            playlist.clear();
            SharedPreferences sharedPreferences = getSharedPreferences("playList2",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear().apply();
            songAdapter.notifyDataSetChanged();
        }

    }
}