package jennie.umn.ac.musicplayer.ListLagu;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import jennie.umn.ac.musicplayer.R;

import static jennie.umn.ac.musicplayer.ListLagu.LaguActivity.laguSource;

public class DetailLaguActivity extends AppCompatActivity {

    TextView title, artist, durationPlayed, durationTotal;
    ImageView thumbnailLagu, nextBtn, prevBtn;
    FloatingActionButton playPause;
    SeekBar seekBar;
    int pos = -1;
    ArrayList<LaguSource> listLagu = new ArrayList<>();
    static Uri uri;
    static MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private Thread playThread, nextThread, prevThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lagu);
        initVariable();
        getIntentMethod();
        title.setText(listLagu.get(pos).getTitle());
        artist.setText(listLagu.get(pos).getArtist());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer != null && fromUser){
                    mediaPlayer.seekTo(progress * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        DetailLaguActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null)
                {
                    int mCurrPos = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrPos);
                    durationPlayed.setText(formattedTime(mCurrPos));
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    private String formattedTime(int mCurrPos)
    {
        String totalout = "";
        String totalNew = "";
        String seconds = String.valueOf(mCurrPos % 60);
        String minutes = String.valueOf(mCurrPos / 60);
        totalout = minutes + ":" + seconds;
        totalNew = minutes + ":" + "0" + seconds;
        if(seconds.length() == 1)
        {
            return totalNew;
        }
        else {
            return totalout;
        }
    }

    private void getIntentMethod() {
        pos = getIntent().getIntExtra("position", -1);
        listLagu = laguSource;

        if(listLagu != null)
        {
            playPause.setImageResource(R.drawable.pause);
            uri = Uri.parse(listLagu.get(pos).getPath());
        }
        if(mediaPlayer != null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
        }
        else
        {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            mediaPlayer.start();
        }
        seekBar.setMax(mediaPlayer.getDuration() / 1000);
        metaData(uri);
    }

    private void initVariable()
    {
        title = findViewById(R.id.judulDetailLagu);
        artist = findViewById(R.id.artistDetailLagu);
        durationPlayed = findViewById(R.id.durationPlay);
        durationTotal = findViewById(R.id.durationTotal);
        thumbnailLagu = findViewById(R.id.thumbnailDetailLagu);
        nextBtn = findViewById(R.id.next);
        prevBtn = findViewById(R.id.prev);
        playPause = findViewById(R.id.playpause);
        seekBar = findViewById(R.id.seekbar);
    }

    private void metaData(Uri uri)
    {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri.toString());
        int duration_total = Integer.parseInt(listLagu.get(pos).getDuration()) / 1000;
        durationTotal.setText(formattedTime(duration_total));
        byte[] art = retriever.getEmbeddedPicture();
        if(art != null){
            Glide.with(this).asBitmap().load(art).into(thumbnailLagu);
        }
        else{
            Glide.with(this).asBitmap().load(R.drawable.songthumbnail).into(thumbnailLagu);
        }
    }

    @Override
    protected void onResume() {
        playThreadBtn();
        nextThreadBtn();
        prevThreadBtn();
        super.onResume();
    }

    private void playThreadBtn() {
        playThread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                playPause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playPauseBtnClick();
                    }
                });
            }
        };
        playThread.start();
    }

    private void playPauseBtnClick() {
        if(mediaPlayer.isPlaying())
        {
            playPause.setImageResource(R.drawable.play);
            mediaPlayer.pause();
            seekBar.setMax(mediaPlayer.getDuration() / 1000);
            DetailLaguActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer != null)
                    {
                        int mCurrPos = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrPos);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
        }
        else
        {
            playPause.setImageResource(R.drawable.pause);
            mediaPlayer.start();
            seekBar.setMax(mediaPlayer.getDuration() / 1000);
            DetailLaguActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer != null)
                    {
                        int mCurrPos = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrPos);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
        }
    }

    private void nextThreadBtn() {
        nextThread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                nextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nextBtnClick();
                    }
                });
            }
        };
        nextThread.start();
    }

    private void prevThreadBtn() {
        prevThread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                prevBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        prevBtnClick();
                    }
                });
            }
        };
        prevThread.start();
    }

    private void nextBtnClick() {
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            pos = ((pos + 1) % listLagu.size());
            uri = Uri.parse(listLagu.get(pos).getPath());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            metaData(uri);
            title.setText(listLagu.get(pos).getTitle());
            artist.setText(listLagu.get(pos).getArtist());
            seekBar.setMax(mediaPlayer.getDuration() / 1000);
            DetailLaguActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer != null)
                    {
                        int mCurrPos = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrPos);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
            playPause.setImageResource(R.drawable.pause);
            mediaPlayer.start();
        }
        else
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            pos = ((pos + 1) % listLagu.size());
            uri = Uri.parse(listLagu.get(pos).getPath());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            metaData(uri);
            title.setText(listLagu.get(pos).getTitle());
            artist.setText(listLagu.get(pos).getArtist());
            seekBar.setMax(mediaPlayer.getDuration() / 1000);
            DetailLaguActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer != null)
                    {
                        int mCurrPos = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrPos);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
            playPause.setImageResource(R.drawable.play);
        }
    }

    private void prevBtnClick() {
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            pos = ((pos - 1) % listLagu.size());
            uri = Uri.parse(listLagu.get(pos).getPath());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            metaData(uri);
            title.setText(listLagu.get(pos).getTitle());
            artist.setText(listLagu.get(pos).getArtist());
            seekBar.setMax(mediaPlayer.getDuration() / 1000);
            DetailLaguActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer != null)
                    {
                        int mCurrPos = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrPos);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
            playPause.setImageResource(R.drawable.pause);
            mediaPlayer.start();
        }
        else
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            pos = ((pos - 1) % listLagu.size());
            uri = Uri.parse(listLagu.get(pos).getPath());
            mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
            metaData(uri);
            title.setText(listLagu.get(pos).getTitle());
            artist.setText(listLagu.get(pos).getArtist());
            seekBar.setMax(mediaPlayer.getDuration() / 1000);
            DetailLaguActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer != null)
                    {
                        int mCurrPos = mediaPlayer.getCurrentPosition() / 1000;
                        seekBar.setProgress(mCurrPos);
                    }
                    handler.postDelayed(this, 1000);
                }
            });
            playPause.setImageResource(R.drawable.play);
        }
    }
}