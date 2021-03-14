package jennie.umn.ac.w07_27184;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import jennie.umn.ac.w07_27184.GaleriVideo.GaleriVideoActivity;
import jennie.umn.ac.w07_27184.Kamera.KameraActivity;

public class MainActivity extends AppCompatActivity {

    Button btnKamera, btnGaleri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnKamera = findViewById(R.id.kamera);
        btnGaleri = findViewById(R.id.galeri_video);

        btnKamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent KameraInt = new Intent(MainActivity.this, KameraActivity.class);
                startActivity(KameraInt);
            }
        });

        btnGaleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent animDrawableInt = new Intent(MainActivity.this, GaleriVideoActivity.class);
                startActivity(animDrawableInt);
            }
        });
    }

}