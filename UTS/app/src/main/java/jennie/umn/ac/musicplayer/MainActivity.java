package jennie.umn.ac.musicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import jennie.umn.ac.musicplayer.ListLagu.LaguActivity;
import jennie.umn.ac.musicplayer.Login.LoginActivity;
import jennie.umn.ac.musicplayer.Profile.ProfileActivity;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    Button btnProfile, btnLogin;
    static MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permission();
        btnProfile = findViewById(R.id.profile);
        btnLogin = findViewById(R.id.login);

        if(mediaPlayer != null){
            if(mediaPlayer.isPlaying())
            {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginInt = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginInt);
            }
        });
    }

    private void permission(){
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            //req permission if not granted
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
        else
        {
            Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            //read the songs?
            Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}