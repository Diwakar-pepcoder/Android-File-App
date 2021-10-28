package com.example.filesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ImageGallary extends AppCompatActivity {

    RecyclerView imageRecycler;
    ProgressBar progressBar;
    ArrayList<Image> allPictures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallary);

        imageRecycler = (RecyclerView) findViewById(R.id.image_recycler);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        imageRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        imageRecycler.setHasFixedSize(true);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 101);
        }

        progressBar.setVisibility(View.VISIBLE);

        allPictures = getAllImages();
        imageRecycler.setAdapter(new ImageAdapter(this, allPictures));
        progressBar.setVisibility(View.GONE);

    }



    private ArrayList<Image> getAllImages() {
        StringBuilder sb = new StringBuilder();
        Button out = (Button)findViewById(R.id.imagecount);
        Context context = this;

        ArrayList<Image> listOfAllImages = new ArrayList<>();
//        out.setText(uri.getPath());
        String[] projection = { MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME };// ,MediaStore.Images.Media.BUCKET_DISPLAY_NAME

//        cursor = this.getCo

        MergeCursor cursor = new MergeCursor(new Cursor[]{
                context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null),
                //context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, null),
                context.getContentResolver().query(MediaStore.Images.Media.INTERNAL_CONTENT_URI, projection, null, null, null),
                //context.getContentResolver().query(MediaStore.Video.Media.INTERNAL_CONTENT_URI, projection, null, null, null)
        });

        while (cursor.moveToNext()) {
            String absolutePathOfImage = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
            String displayName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));

            listOfAllImages.add(new Image(absolutePathOfImage, displayName));
        }

//        out.setText("There are "+size+" images");
        return listOfAllImages;
    }

}