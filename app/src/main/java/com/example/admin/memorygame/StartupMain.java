package com.example.admin.memorygame;

import android.app.Activity;
import android.app.Fragment;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;

import static java.security.AccessController.getContext;

public class StartupMain extends Activity {

    // create a media player
    private MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup_main);

        //mediaPlayer = MediaPlayer.create(this, R.raw.opening);
        //mediaPlayer.setVolume(0.5f, 0.5f);
        //mediaPlayer.setLooping(true);
        //mediaPlayer.start();
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
        // start the media player
        super.onResume();
        mediaPlayer = MediaPlayer.create(this, R.raw.opening);
        mediaPlayer.setVolume(0.5f, 0.5f);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }
}







