package jennie.umn.ac.musicplayer.ListLagu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.zip.Inflater;

import jennie.umn.ac.musicplayer.Login.LoginActivity;
import jennie.umn.ac.musicplayer.MainActivity;
import jennie.umn.ac.musicplayer.Profile.ProfileActivity;
import jennie.umn.ac.musicplayer.R;

public class LaguActivity extends AppCompatActivity {

    static ArrayList<LaguSource> laguSource;
    RecyclerView rvDaftarLagu;
    DaftarLaguAdapter daftarLaguAdapter;
    ImageView popupBtn;
    public static ArrayList<LaguSource> getAllAudio(Context context)
    {
        ArrayList<LaguSource> listLaguTemp = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA //for path
        };

        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

        if(cursor != null)
        {
            while(cursor.moveToNext())
            {
                String title = cursor.getString(0);
                String artist = cursor.getString(1);
                String album = cursor.getString(2);
                String duration = cursor.getString(3);
                String path = cursor.getString(4);

                LaguSource lagusource = new LaguSource(path, title, artist, duration, album);
                listLaguTemp.add(lagusource);
            }
            cursor.close();
        }
        return listLaguTemp;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lagu);
        dialogWelcome();
        laguSource = getAllAudio(this);
        rvDaftarLagu = (RecyclerView) findViewById(R.id.recyclerView);
        daftarLaguAdapter = new DaftarLaguAdapter(this, laguSource);
        rvDaftarLagu.setAdapter(daftarLaguAdapter);
        rvDaftarLagu.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        popupBtn = findViewById(R.id.popup);
        popupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(LaguActivity.this, v);
                popup.inflate(R.menu.popup_menu);
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.profileMenu:
                                // do your
                                startActivity(new Intent(LaguActivity.this, ProfileActivity.class));
                                return true;
                            case R.id.logoutMenu:
                                startActivity(new Intent(LaguActivity.this, MainActivity.class));
                                return true;
                            default:
                                return false;
                        }
                    }
                });
            }
        });
    }

    private void dialogWelcome()
    {
        DialogWelcome dialogWelcome = new DialogWelcome();
        dialogWelcome.show(getSupportFragmentManager(), "welcome message");
    }
}