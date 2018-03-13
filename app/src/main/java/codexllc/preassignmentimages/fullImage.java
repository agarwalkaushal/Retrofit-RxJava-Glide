package codexllc.preassignmentimages;

import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class fullImage extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        Intent intent = getIntent();
        String url = intent.getExtras().getString("url");

        imageView = (ImageView) findViewById(R.id.fullImage);

        Glide.with(this).load(url).into(imageView);

    }
}
