package com.example.filesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void fetchImageVideoCount(View view){
//        parseAllImages();
//        parseAllVideos();
    }

    public void imageGallary(View view){
        Intent intent = new Intent(this, ImageGallary.class);
        startActivity(intent);
    }
    public void videoGallary(View view){
        Intent intent = new Intent(this, VideoGallary.class);
        startActivity(intent);
    }
}