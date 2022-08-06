package com.example.musicapp1;

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


import com.chibde.visualizer.BarVisualizer;
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
    private ImageView imagePlayPause = null;
    List<Song> shuffelist = Arrays.asList(songCollection.songs);






    SeekBar seekBar;


    Handler handler = new Handler();


    Boolean shuffleFlag = false;
    Boolean repeatFlag = false;
    ImageView shuffleBtn;
    ImageView repeatBtn ;


    BarVisualizer audioVisualizeView;
    int audioseesionID = player.getAudioSessionId();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);



        imagePlayPause = findViewById(R.id.playandpauseImage);
        //songdata is the song id
        Bundle songData = this.getIntent().getExtras();
        //currentindex is the position of the current song playing in the array
        currentIndex = songData.getInt("index");
        audioVisualizeView = findViewById(R.id.barVisualizer);
        audioVisualizeView.setColor(ContextCompat.getColor(this,R.color.purple_500));
        audioVisualizeView.setDensity(50);
        audioVisualizeView.setAlpha((float) 0.1);





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

        shuffleBtn = findViewById(R.id.shufflebtn);
        repeatBtn = findViewById(R.id.repeatBtn);
        playsong(filelink);



    }






    //Changes the display to the corresponding song based on the song index in the array
        public void displaySongBasedOnIndex () {
            Song song = songCollection.getCurrentSongs(currentIndex);
            title = song.getTitle();
            artist = song.getArtiste();
            filelink = song.getFilelink();
            drawable = song.getCoverArt();
            songLength = song.getSonglength();
            TextView txtTitle = findViewById(R.id.txtSongTitle);
            txtTitle.setText(title);
            txtTitle.setSelected(true);
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
                imagePlayPause.setImageResource(R.drawable.ic_baseline_pause_24);
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

                imagePlayPause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
            } else {
                player.start();
                //sets the seek bar to only move until it reaches the max
                seekBar.setMax(player.getDuration());
                //when it loops i wont be moving the seek bar twice
                handler.removeCallbacks(p_bar);
                //every milisecond the bar moves
                handler.postDelayed(p_bar, 1);

                imagePlayPause.setImageResource(R.drawable.ic_baseline_pause_24);
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

                        imagePlayPause.setImageResource(R.drawable.ic_baseline_play_arrow_24);

                    }



                }
            });
        }
//plays the next song in the collection
        public void playnext (View view){
            currentIndex = songCollection.getNextSong(currentIndex);
            displaySongBasedOnIndex();
            playsong(filelink);

        }
//plays the previous song in the collection
        public void playPrev (View view){
            currentIndex = songCollection.getPrevSong(currentIndex);
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
            songCollection = new Songcollection() ;
        }


        else{
            shuffleBtn.setBackgroundResource(R.drawable.shuffle_on);
            Collections.shuffle(shuffelist);
            shuffelist.toArray(songCollection.songs);
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



}