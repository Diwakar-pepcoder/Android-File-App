package audiohandler;

import androidx.annotation.RequiresApi;
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
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.example.filesapp.R;

import java.util.ArrayList;

public class AudioActivity extends AppCompatActivity {

    RecyclerView audiorecycler;
    ArrayList<Audio> allAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        allAudio = getAllAudio();
        audiorecycler = findViewById(R.id.audiorecycler);

        getSupportActionBar().setTitle("media");

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 101);
        }

        audiorecycler.setLayoutManager(new GridLayoutManager(this, 2));
        audiorecycler.setAdapter(new AudioAdapter(this, allAudio));

//        Log.d( "Audio Main Acitivity", allAudio.toString());
    }

    private static MergeCursor getCursor(Context context){
        String[]projection = {
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION
        };

        MergeCursor cursor = new MergeCursor(new Cursor[]{
                context.getContentResolver().query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, projection, null, null, null),
                context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, null, null, null),
        });
        return cursor;
    }

    public static int getCount(Context context){
        return getCursor(context).getCount();
    }

    private ArrayList<Audio> getAllAudio(){

        MergeCursor cursor = getCursor(this);
        int dataIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        int nameIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
        int durationIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);

        ArrayList<Audio> allAudio = new ArrayList<>(cursor.getCount());

        for(int i=cursor.getCount();i>=1;i--)allAudio.add(null);

        int i=allAudio.size()-1;
        while (cursor.moveToNext()){
            String path = cursor.getString(dataIndex);
            String name = cursor.getString(nameIndex);
            double duration = cursor.getDouble(durationIndex);

            Audio audio = new Audio(path, name, duration);
            allAudio.set(i--, audio);
        }

        return allAudio;
    }

}