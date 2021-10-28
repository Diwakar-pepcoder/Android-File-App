package downloadhandler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.database.MergeCursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.TextView;

import com.example.filesapp.R;

import java.util.ArrayList;

public class DownloadActivity extends AppCompatActivity {

    RecyclerView downloadRecycler;
    ArrayList<DownloadFile> allFiles;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

//        downloadRecycler = findViewById(R.id.downloadrecycler);

        allFiles = getAllFiles();
//        downloadRecycler.setAdapter(new DownloadFileAdapter(this, allFiles));
        TextView textView = findViewById(R.id.textView);

        textView.setText(allFiles.size() + "");
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private ArrayList<DownloadFile> getAllFiles(){

        ArrayList<DownloadFile> allFiles = new ArrayList<>();

        Context context = this;
        String[] projection = { MediaStore.DownloadColumns.DATA, MediaStore.DownloadColumns.DISPLAY_NAME };// ,MediaStore.Images.Media.BUCKET_DISPLAY_NAME

        MergeCursor cursor = new MergeCursor(new Cursor[]{
                context.getContentResolver().query(MediaStore.Downloads.INTERNAL_CONTENT_URI, projection, null, null, null),
                context.getContentResolver().query(MediaStore.Downloads.EXTERNAL_CONTENT_URI, projection, null, null, null),
        });

        int pathIndex = cursor.getColumnIndexOrThrow(MediaStore.DownloadColumns.DATA);
        int nameIndex = cursor.getColumnIndexOrThrow(MediaStore.DownloadColumns.DISPLAY_NAME);

        while(cursor.moveToNext()){
            String path = cursor.getString(pathIndex);
            String name = cursor.getString(nameIndex);

            DownloadFile file = new DownloadFile(path, name);
            allFiles.add(file);
        }

        return allFiles;
    }

}