package com.example.musicapp1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeSongAdapter extends RecyclerView.Adapter<MyView> implements Filterable {
    Songcollection songCollection = new Songcollection();
    List<Song> songsFiltered ;
    List<Song> songs;
    Context context;
    public HomeSongAdapter(List<Song> songs) {
        this.songs = songs;
        this.songsFiltered = songs;
    }
    public static ArrayList<Song> playlist = new ArrayList<Song>();
    SharedPreferences sharedPreferences = HomeActivity.sharedPreferences;


    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songview = inflater.inflate(R.layout.item_songhome, parent,false);
        MyView viewholder =  new MyView(songview);
        return viewholder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, @SuppressLint("RecyclerView") int position) {
        Song song = songsFiltered.get(position);
        TextView artist = holder.artisttext;
        String artistname = song.getArtiste();
        artist.setText(artistname);
        TextView title = holder.titletext;
        title.setText(song.getTitle());
        title.setSelected(true);
        ImageView imageView = holder.imageTest;
        String imageId = song.getCoverArt();
        Picasso.get().load(imageId).into(holder.imageTest);
        Log.d("why", String.valueOf(position));
        ImageView addsong = holder.addSong;
        Log.d("lol", song.getId());
        addsong.setContentDescription(song.getId());







        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Songcollection songCollection = new Songcollection();
                String songId = song.getId();
                int currentArrayIndex = songCollection.searchSongById(songId);
                sendDataToActivty(currentArrayIndex);
            }
        });

        addsong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playlist = HomeActivity.playList;
                playlist.add(song);
                Gson gson = new Gson();
                String json = gson.toJson(playlist);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("list",json);
                editor.apply();
            }
        });



    }
    private void sendDataToActivty (int index){

        Intent intent = new Intent(context, PlaySongActivity.class);
        intent.putExtra("index", index);
        context.startActivity(intent);
    }




    @Override
    public int getItemCount() {
        return songsFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();


                if(charString.isEmpty()){

                    songsFiltered = songs;

                }

                else{


                    List<Song> filteredSongs = new ArrayList<>();
                    for (int i = 0; i < songs.size()  ;i++) {
                        if (songs.get(i).getTitle().toLowerCase().contains(charString.toLowerCase())){
                            filteredSongs.add(songs.get(i));

                        }

                    }
                    songsFiltered = filteredSongs;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = songsFiltered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {


                songsFiltered = (List<Song>) results.values;
                notifyDataSetChanged();
            }
        };



    }


}
