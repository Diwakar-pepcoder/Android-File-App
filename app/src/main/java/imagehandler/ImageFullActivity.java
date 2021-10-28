package imagehandler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.filesapp.R;

public class ImageFullActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_fill);

        Intent intent = getIntent();
        String imagePath = intent.getStringExtra("path");
        String imageName = intent.getStringExtra("name");

        getSupportActionBar().setTitle(imageName);

        ImageView imageView = findViewById(R.id.imageView);

        Glide.with(this).load(imagePath).into(imageView);
    }
}