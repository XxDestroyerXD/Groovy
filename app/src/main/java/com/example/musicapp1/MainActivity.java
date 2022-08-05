package com.example.musicapp1;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECORD_AUDIO;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Songcollection songCollection = new Songcollection();
    static ArrayList<Song> playlist = new ArrayList<Song>();
    ImageView profileview ;
    SharedPreferences sharedPreferences;
    private static final int audioRequestCode = 10001;
    private static final int storageRequestCode = 10002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profileview = findViewById(R.id.profileview);
        sharedPreferences = getSharedPreferences("playList", MODE_PRIVATE);
        String albums = sharedPreferences.getString("list", "");
        Log.d("test", albums);
        if(!albums.equals("")){
            TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>(){};
            Gson gson = new Gson();
            playlist = gson.fromJson(albums, token.getType());
        }



        permissonUtil.requestPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO,audioRequestCode);
        permissonUtil.requestPermission(MainActivity.this, READ_EXTERNAL_STORAGE, storageRequestCode);




    }

    //retrieve the song id from the song collection
    public void handleSelection(View view) {
        String resources = getResources().getResourceEntryName(view.getId());
        int currentArrayIndex = songCollection.searchSongById(resources);
        sendDataToActivity(currentArrayIndex);
    }
//sends data to activity
    private void sendDataToActivity(int currentArrayIndex) {
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("index",currentArrayIndex);
        startActivity(intent);

    }

//adds the song to the playlist
    public void addtoplaylist(View view) {
        String songid = view.getContentDescription().toString();
        int songindex = songCollection.searchSongById(songid);
        Song song = songCollection.getCurrentSongs(songindex);
        playlist.add(song);
        Gson gson = new Gson();
        String json = gson.toJson(playlist);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("list",json);
        editor.apply();


    }
//goes to the playlist activity
    public void gotoplaylist(View view) {

        Intent intent = new Intent(this, PlaylistActivity.class);
        startActivity(intent);


    }


    public void gotoprofile(View view) {
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);
    }
}
