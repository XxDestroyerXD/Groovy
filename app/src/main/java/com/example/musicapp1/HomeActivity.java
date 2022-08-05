package com.example.musicapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    public static ArrayList<Song> songList = new ArrayList<Song>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        RecyclerView trendingSongView ;
        trendingSongView = findViewById(R.id.trendingView);


        String url = "https://groovy-0594.restdb.io/rest/Real-Songs?apikey=1d3493a4d4d302a2925b8874f64559d1743bd";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>(){};
                songList = gson.fromJson(response,token.getType());
                SongTestAdapter adapter = new SongTestAdapter(songList);
                trendingSongView.setAdapter(adapter);
                trendingSongView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);


    }
}