package com.example.admin.memorygame;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by cwpila14 on 10/3/2016.
 */

public class ButtonsFragment extends Fragment {

    // instance variables
    Button restartGame = null;
    Button quitGame = null;
    TextView clicks = null;
    private AlertDialog mDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.buttons_fragment, container, false);

        // find the buttons and textView
        this.restartGame = (Button) rootView.findViewById(R.id.restartGame);
        this.quitGame = (Button) rootView.findViewById(R.id.quitGame);
        this.clicks = (TextView) rootView.findViewById(R.id.clicks);

        // set on click listener on restart button
        restartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((GameMain) getActivity()).stopMedia();
                // create new intent to restart GameMain
                Intent intent = new Intent(getActivity(), GameMain.class);
                getActivity().startActivity(intent);

            }
        });

        // set on click listener to quit button
        quitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a dialog popup
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.Ending);
                builder.setCancelable(false);
                builder.setPositiveButton(R.string.ok_label,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // nothing

                                // on click send user back to home screen
                                //((GameMain)getActivity()).stopMedia();
                                Intent intent = new Intent(getActivity(), StartupMain.class);
                                getActivity().startActivity(intent);
                            }
                        });
                builder.setNegativeButton(R.string.cancel_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // nothing
                    }
                });
                // show dialog
                mDialog = builder.show();

            }
        });

        // return root
        return rootView;

    }
}
