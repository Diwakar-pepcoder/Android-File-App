package videohandle;

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
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.filesapp.R;

import java.util.ArrayList;

public class VideoGallary extends AppCompatActivity {

    RecyclerView videoRecyclerView;
    ProgressBar progressBar;
    ArrayList<Video> allVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_gallary);

        videoRecyclerView = findViewById(R.id.videorecyclerview);
        progressBar = findViewById(R.id.progressBar2);

        videoRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        videoRecyclerView.setHasFixedSize(true);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 101);
        }

        progressBar.setVisibility(View.VISIBLE);

        allVideos = getAllVideos();
        videoRecyclerView.setAdapter(new VideoAdapter(this, allVideos));
        progressBar.setVisibility(View.GONE);
    }

    private static MergeCursor getCursor(Context context){
        String[] projection = {
                MediaStore.MediaColumns.DATA,
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Video.Media._ID,
                MediaStore.Video.Thumbnails.DATA
        };// ,MediaStore.Images.Media.BUCKET_DISPLAY_NAME

        MergeCursor cursor = new MergeCursor(new Cursor[]{
//                context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null),
                context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, null),
//                context.getContentResolver().query(MediaStore.Images.Media.INTERNAL_CONTENT_URI, projection, null, null, null),
                context.getContentResolver().query(MediaStore.Video.Media.INTERNAL_CONTENT_URI, projection, null, null, null)
        });
        return cursor;
    }

    public static int getCount(Context context){
        return getCursor(context).getCount();
    }

    private ArrayList<Video> getAllVideos() {

        ArrayList<Video> listOfAllVideos = new ArrayList<>();
//        out.setText(uri.getPath());

        MergeCursor cursor = getCursor(this);

        int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        int thumb = cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA);
        int nameIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);

        while (cursor.moveToNext()) {
            String path = cursor.getString(column_index_data);
            String thumbnail = cursor.getString(thumb);
            String name = cursor.getString(nameIndex);

            Video video = new Video(path, thumbnail, name);

            listOfAllVideos.add(video);
        }

        return listOfAllVideos;
    }
}