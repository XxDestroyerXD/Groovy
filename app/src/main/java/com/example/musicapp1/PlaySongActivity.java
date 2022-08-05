package com.example.musicapp1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chibde.visualizer.BarVisualizer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlaySongActivity extends AppCompatActivity {

    private int currentIndex ;
    private String title = " ";
    private String artist = " ";
    private String filelink = " ";
    private double songLength;
    private String drawable;
    private Songcollection songCollection = new Songcollection();
    private Songcollection shuffledSongCollection = new Songcollection();
    private MediaPlayer player = new MediaPlayer();
    private Button btnPlayPause = null;
    private Button btnPlayNext = null;
    List<Song> songs = HomeActivity.songList;




    SeekBar seekBar;


    Handler handler = new Handler();


    Boolean shuffleFlag = false;
    Boolean repeatFlag = false;
    Button shuffleBtn;
    List<Song> shuffleList = Arrays.asList(songCollection.songs);
    Button repeatBtn ;


    BarVisualizer audioVisualizeView;
    int audioseesionID = player.getAudioSessionId();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);



        btnPlayPause = findViewById(R.id.btnPlayPause);
        //songdata is the song id
        Bundle songData = this.getIntent().getExtras();
        //currentindex is the position of the current song playing in the array
        currentIndex = songData.getInt("index");
        audioVisualizeView = findViewById(R.id.barVisualizer);
        audioVisualizeView.setColor(ContextCompat.getColor(this,R.color.teal_200));
        audioVisualizeView.setDensity(50);





        displaySongBasedOnIndex();







        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (player != null && player.isPlaying()) {
                    player.seekTo(seekBar.getProgress());
                }

            }


        });

        shuffleBtn = findViewById(R.id.shuffleBtn);
        repeatBtn = findViewById(R.id.repeatBtn);
        playsong(filelink);



    }






    //Changes the display to the corresponding song based on the song index in the array
        public void displaySongBasedOnIndex () {
            Song song = songs.get(currentIndex);
            title = song.getTitle();
            artist = song.getArtiste();
            Log.d("why", artist);
            filelink = song.getFilelink();
            Log.d("why", filelink);
            drawable = song.getCoverArt();
            songLength = song.getSonglength();
            TextView txtTitle = findViewById(R.id.txtSongTitle);
            txtTitle.setText(title);
            TextView txtArtist = findViewById(R.id.txtArtist);
            txtArtist.setText(artist);
            ImageView iCoverArt = findViewById(R.id.imgCoverArt);
            Picasso.get().load(drawable).into(iCoverArt);

        }
//plays the song
        public void playsong (String songUrl){
            try {
                player.reset();
                player.setDataSource(songUrl);
                player.prepare();
                player.start();
                stopmusicwhenends();
                btnPlayPause.setText("PAUSE");
                setTitle(title);
                seekBar.setMax(player.getDuration());
                handler.removeCallbacks(p_bar);
                handler.postDelayed(p_bar, 1);
                audioVisualizeView.setPlayer(audioseesionID);
                // help catch errors in the app and print it in the terminal
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

//Play or Pause the song when touching btnPlayPause
        public void playOrPauseMusic (View view){
            if (player.isPlaying()) {
                player.pause();

                btnPlayPause.setText("PLAY");
            } else {
                player.start();
                //sets the seek bar to only move until it reaches the max
                seekBar.setMax(player.getDuration());
                //when it loops i wont be moving the seek bar twice
                handler.removeCallbacks(p_bar);
                //every seconf
                handler.postDelayed(p_bar, 1);

                btnPlayPause.setText("PAUSE");
            }
        }
//stop the music when it ends
        private void stopmusicwhenends () {
//the code sets a listener to notify when the music ends
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (repeatFlag){

                        playOrPauseMusic(null);

                    }else{

                        btnPlayPause.setText("PLAY");

                    }



                }
            });
        }
//plays the next song in the collection
        public void playnext (View view){
            currentIndex = songCollection.getNextSong(currentIndex);
            Toast.makeText(this, "After clicking playnext, \nthe current index of this song \n" +
                    "in the Songcollection array is now : " + currentIndex, Toast.LENGTH_LONG).show();
            displaySongBasedOnIndex();
            playsong(filelink);

        }
//plays the previous song in the collection
        public void playPrev (View view){
            currentIndex = songCollection.getPrevSong(currentIndex);
            Toast.makeText(this, "After clicking playPrev, \nthe current index of this song \n" +
                    "in the Songcollection array is now : " + currentIndex, Toast.LENGTH_LONG).show();
            displaySongBasedOnIndex();
            playsong(filelink);
        }
//stops the player when pressing the back button
        @Override
        public void onBackPressed () {
            super.onBackPressed();
            handler.removeCallbacks(p_bar);
            player.release();

        }

//runnable is a kind of time interval, for making the seekbar move
        Runnable p_bar = new Runnable() {
            @Override
            public void run() {
                //if the player is playing the seek bar would get the position of the current song
                if (player != null && player.isPlaying()) {
                    seekBar.setProgress(player.getCurrentPosition());

                }
                //makes it smooooooth and every 1 millisecond
                handler.postDelayed(this, 1);
            }
        };




    public void shuffleSong (View view){

        if(shuffleFlag){
            shuffleBtn.setBackgroundResource(R.drawable.shuffle_off);
            songCollection = new Songcollection();
        }


        else{
            shuffleBtn.setBackgroundResource(R.drawable.shuffle_on);
            Collections.shuffle(shuffleList);
            shuffleList.toArray(songCollection.songs);
        }


        shuffleFlag = !shuffleFlag;


    }


    public void repeatSong(View view) {
        if(repeatFlag){
            repeatBtn.setBackgroundResource(R.drawable.repeat_off);

        }else{
            repeatBtn.setBackgroundResource(R.drawable.repeat_on);

        }
        repeatFlag = !repeatFlag;


    }

    public void lyricsView(View view){
        Intent intent = new Intent(this,Song_Lyrics.class);
        intent.putExtra("key", currentIndex);
        startActivity(intent);
    }


}