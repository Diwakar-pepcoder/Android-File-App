package audiohandler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.filesapp.R;

import java.util.ArrayList;

import videohandle.VideoFullActivity;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.AudioViewHolder> {

    private final Context context;
    private final ArrayList<Audio> allAudio;

    AudioAdapter(Context context, ArrayList<Audio> allAudio) {
        this.context = context;
        this.allAudio = allAudio;
    }


    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_audio_recycler_item, parent, false);
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        final Audio audio = allAudio.get(position);

        holder.duration.setText(String.valueOf(audio.duration));
        holder.name.setText(audio.name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, VideoFullActivity.class);
//                intent.putExtra("path", audio.path);
//                intent.putExtra("name", audio.name);
//                context.startActivity(intent);
                Uri uri = Uri.parse(audio.path);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setDataAndType(uri, "audio/*");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allAudio.size();
    }

    public class AudioViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView duration;
        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.audioname);
            duration = itemView.findViewById(R.id.audioDuration);
        }
    }
}
