package com.example.filesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import audiohandler.AudioActivity;
import downloadhandler.DownloadActivity;
import downloadhandler.DownloadFile;
import imagehandler.ImageGallary;
import videohandle.VideoGallary;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView music = findViewById(R.id.musiccount);
        music.setText(String.valueOf(AudioActivity.getCount(this)));

        TextView image = findViewById(R.id.imagecount);
        image.setText(String.valueOf(ImageGallary.getCount(this)));

        TextView video = findViewById(R.id.videocount);
        video.setText(String.valueOf(VideoGallary.getCount(this)));
    }

    public void imageGallary(View view){
        Intent intent = new Intent(this, ImageGallary.class);
        startActivity(intent);
    }
    public void videoGallary(View view){
        Intent intent = new Intent(this, VideoGallary.class);
        startActivity(intent);
    }

    public void audioGallary(View view){
        Intent intent = new Intent(this, AudioActivity.class);
        startActivity(intent);
    }

    public void downloadGallary(View view){
        Intent intent = new Intent(this, DownloadActivity.class);
        startActivity(intent);
    }



}