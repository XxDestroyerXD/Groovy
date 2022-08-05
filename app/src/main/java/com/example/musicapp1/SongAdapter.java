package com.example.musicapp1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<MyView> implements Filterable {
    Songcollection songCollection = new Songcollection();
    List<Song> songsFiltered ;
    List<Song> songs;
    Context context;
    public SongAdapter(List<Song> songs) {
        this.songs = songs;
        this.songsFiltered = songs;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View songview = inflater.inflate(R.layout.item_song,parent,false);
        MyView viewholder =  new MyView(songview);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, @SuppressLint("RecyclerView") int position) {
        Song song = songsFiltered.get(position);
        TextView artist = holder.artisttext;
        artist.setText(song.getArtiste());
        TextView title = holder.titletext;
        title.setText(song.getTitle());
        ImageView imageView = holder.image;
        int imagedraw = song.getDrawable();
        holder.image.setImageResource(imagedraw);



        holder.removebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.playlist.remove(position);


                Gson gson = new Gson();
                String json = gson.toJson(MainActivity.playlist);
                SharedPreferences sharedPreferences = context.getSharedPreferences("playlist", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear().putString("list", json).apply();



                notifyDataSetChanged();

            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Songcollection songCollection = new Songcollection();
                String songId = song.getId();
                int currentArrayIndex = songCollection.searchSongById(songId);
                sendDataToActivty(currentArrayIndex);
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
