package audiohandler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.database.MergeCursor;
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
        audiorecycler.setLayoutManager(new GridLayoutManager(this, 2));
        audiorecycler.setAdapter(new AudioAdapter(this, allAudio));

//        Log.d( "Audio Main Acitivity", allAudio.toString());
    }

    private ArrayList<Audio> getAllAudio(){

        Context context = this;

        ArrayList<Audio> allAudio = new ArrayList<>();

        String[]projection = {
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION
        };

        MergeCursor cursor = new MergeCursor(new Cursor[]{
                context.getContentResolver().query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, projection, null, null, null),
                context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, null, null, null),
        });

        int dataIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        int nameIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
        int durationIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);

        while (cursor.moveToNext()){
            String path = cursor.getString(dataIndex);
            String name = cursor.getString(nameIndex);
            double duration = cursor.getDouble(durationIndex);

            Audio audio = new Audio(path, name, duration);
            allAudio.add(audio);
        }

        return allAudio;
    }

}