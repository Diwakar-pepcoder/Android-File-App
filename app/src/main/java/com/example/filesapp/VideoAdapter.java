package com.example.filesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    Context context;
    ArrayList<Video> allVideos;

    VideoAdapter(Context context, ArrayList<Video> allVideos){
        this.context = context;
        this.allVideos = allVideos;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_video_recycler_item, parent, false);

        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Video video = allVideos.get(position);
        Glide.with(context)
                .load(video.thumbnail)
                .centerCrop()
                .into(holder.thubmnailView);
    }

    @Override
    public int getItemCount() {
        return allVideos.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        ImageView thubmnailView;
        VideoViewHolder(View videoView){
            super(videoView);
            thubmnailView = videoView.findViewById(R.id.rowthumbnail);
        }
    }
}
