package com.example.admin.memorygame;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import static java.security.AccessController.getContext;

/**
 * Created by admin on 9/27/16.
 */
public class GameMain extends Activity{

    // instance variables
    TextView clickScore = null;
    public MediaPlayer mediaPlayer = null;
    Handler h = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);
        //find click text view, create handler
        this.clickScore = (TextView) findViewById(R.id.clicks);
        h = new Handler();

        //mediaPlayer = MediaPlayer.create(this, R.raw.battle);
        //mediaPlayer.setVolume(0.5f, 0.5f);
        //mediaPlayer.setLooping(true);
        //mediaPlayer.start();

    }
    // start thinking method
    public void startThinking() {
        View thinkingView = findViewById(R.id.thinking);
        thinkingView.setVisibility(View.VISIBLE);
    }
    // stop thinking method
    public void stopThinking() {
        View thinkingView = findViewById(R.id.thinking);
        thinkingView.setVisibility(View.GONE);
    }

    public void updateClicks(int num) {
        clickScore.setText(num);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // stop media player on pause
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // start media player on resume
        mediaPlayer = MediaPlayer.create(this, R.raw.battle);
        mediaPlayer.setVolume(0.5f, 0.5f);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mediaPlayer.stop();
    }
    // stop media method
    public void stopMedia() {
        this.mediaPlayer.stop();
        this.mediaPlayer.reset();
        this.mediaPlayer.release();
    }
    // update textView method
    public void update(final int number){
        h.post(new Runnable() {
            @Override
            public void run() {
                clickScore.setText(Integer.toString(number));
            }
        });
    }



}
