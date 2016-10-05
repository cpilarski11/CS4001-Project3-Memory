package com.example.admin.memorygame;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by admin on 10/2/16.
 */
public class StartupFragment extends Fragment {
    // instance variables
    Button newGame = null;
    //Button continueGame = null;
    Button about = null;
    private AlertDialog mDialog;
    MediaPlayer mediaPlayer = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // mediaPlayer = MediaPlayer.create(getContext(), R.raw.opening);
       // mediaPlayer.setVolume(0.5f, 0.5f);
       // mediaPlayer.setLooping(true);
        //mediaPlayer.start();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.startup_fragment, container, false);
        // find the buttons
        this.newGame = (Button) rootView.findViewById(R.id.newGame);
        //this.continueGame = (Button) rootView.findViewById(R.id.continueGame);
        this.about = (Button) rootView.findViewById(R.id.aboutGame);
        //mediaPlayer = MediaPlayer.create(getContext(), R.raw.opening);
        //mediaPlayer.setVolume(0.5f, 0.5f);
        //mediaPlayer.setLooping(true);
        //mediaPlayer.start();

        // set onclicklistener for all the buttons and make them do something
        // new game will start a new game and transitio to the MainGame
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GameMain.class);
                getActivity().startActivity(intent);
                //mediaPlayer.stop();
            }
        });
        /*continue will continue a previous game
        continueGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        // about will display a dialog message
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.About);
                builder.setCancelable(false);
                builder.setPositiveButton(R.string.ok_label,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // nothing
                            }
                        });
                // show the dialog
                mDialog = builder.show();

            }
        });
        // return root view
        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}


