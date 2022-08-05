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

public class PlaylistActivity extends AppCompatActivity {
RecyclerView playlist;
SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        playlist =  findViewById(R.id.recyclerview);

        songAdapter = new SongAdapter(MainActivity.playlist);
        playlist.setAdapter(songAdapter);
        playlist.setLayoutManager(new LinearLayoutManager(this));
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
        MainActivity.playlist.clear();
        SharedPreferences sharedPreferences = getSharedPreferences("playlist",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
        songAdapter.notifyDataSetChanged();
    }
}