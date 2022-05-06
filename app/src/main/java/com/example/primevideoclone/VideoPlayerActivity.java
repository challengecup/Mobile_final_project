package com.example.primevideoclone;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class VideoPlayerActivity extends AppCompatActivity {

    private PlayerView videoPlayer;
    private SimpleExoPlayer simpleExoPlayer;
    private static final String FILE_URL="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_player);

        videoPlayer = findViewById(R.id.exo_player);
        setUpExoPlayer(getIntent().getStringExtra("url"));
    }

    private void setUpExoPlayer(String url){
        simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();

        videoPlayer.setPlayer(simpleExoPlayer);
        videoPlayer.setPlayer(simpleExoPlayer);
        MediaItem mediaItem = MediaItem.fromUri(url);
        simpleExoPlayer.addMediaItem(mediaItem);

        simpleExoPlayer.prepare();
        simpleExoPlayer.play();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        simpleExoPlayer.release();
    }
}