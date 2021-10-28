package com.example.filesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    ArrayList<Image> allPictures;
    Context context;

    ImageAdapter(Context context, ArrayList<Image> allPictures){
        this.context = context;
        this.allPictures = allPictures;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_image_recycler_item, parent, false);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Image currentImage = allPictures.get(position);
        Glide.with(context)
                .load(currentImage.imagePath)
                .centerCrop()
                .into(holder.image);
//        holder.itemView.setIma
    }

    @Override
    public int getItemCount() {
        return allPictures.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        ImageViewHolder(View imageView){
            super(imageView);
            image = imageView.findViewById(R.id.rowimage);
        }
    }
}
