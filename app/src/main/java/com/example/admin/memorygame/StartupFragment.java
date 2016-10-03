package com.example.admin.memorygame;

import android.app.Fragment;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;

/**
 * Created by admin on 10/2/16.
 */
public class StartupFragment extends Fragment {

    SoundPool s = null;
    int opening = 0;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.s = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        this.opening = this.s.load(getContext(), R.raw.opening, 1);
        s.play(opening, 1f, 1f, 1, 0, 1f);
    }
}


