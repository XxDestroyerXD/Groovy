package com.example.musicapp1;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.sql.Array;
import java.util.ArrayList;

public class Songcollection {

    public static ArrayList<Song> songList = HomeActivity.songList;
    public Song[] songs = new Song[songList.size()];


    //song collection containing all the songs' info
    public Songcollection() {


        for (int i = 0; i < songList.size(); i++) {
            songs[i] = songList.get(i) ;
        }


    }

    //searches the song by using it's id
    public int searchSongById(String id) {

        for (int i = 0; i < songs.length; i++) {
            Song tempSong = songs[i];
            String tempID = tempSong.getId();
            if (tempID.equals(id)) {
                return i;

            }


        }
        return 0;
    }

    //uses the song index to retrieve the corresponding song info
    public Song getCurrentSongs(int index) {
        return songs[index];
    }

    //changes the song to the next song in the list
    public int getNextSong(int currentSongIndex) {
        if (currentSongIndex >= songList.size() - 1) {
            return currentSongIndex;
        } else {
            return currentSongIndex + 1;
        }
    }

    //changes the song to the previous song in the list
    public int getPrevSong(int currentSongIndex) {
        if (currentSongIndex <= 0) {
            return currentSongIndex;
        } else {
            return currentSongIndex - 1;
        }
    }
}
