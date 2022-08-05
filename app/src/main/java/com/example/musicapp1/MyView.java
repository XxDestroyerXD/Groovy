package com.example.musicapp1;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyView extends RecyclerView.ViewHolder {
    public TextView titletext;
    public TextView artisttext;
    public ImageView image;
    public ImageView imageTest;
    public Button removebtn;
    public ImageView addSong;



    public MyView(@NonNull View itemView) {
        super(itemView);
        titletext = itemView.findViewById(R.id.titletext);
        artisttext = itemView.findViewById(R.id.artistText);
        image = itemView.findViewById(R.id.songImage);
        imageTest = itemView.findViewById(R.id.songimages);
        removebtn = itemView.findViewById(R.id.button2);
        addSong = itemView.findViewById(R.id.addtoplaylist);

    }
}
