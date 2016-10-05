package com.example.admin.memorygame;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by admin on 10/2/16.
 */
public class TilesFragment extends Fragment {

    // instance variables

    private SoundPool s = null;
    private int correctSound, wrongSound, tadaSound;
    //MediaPlayer mediaPlayer = null;
    ArrayList<Drawable> picsArray = new ArrayList<>();
    //ArrayList<Drawable> gameArray = new ArrayList<>();
    ArrayList<Drawable> compared = new ArrayList<>();
    ArrayList<ImageButton> imageButton = new ArrayList<>();
    ArrayList<Drawable> gameList = new ArrayList<>();
    Handler h;
    int n = 1;
    int score = 0;
    int count = 0;
    //Drawable pokeball;
    Drawable whosThatPokemon;
    Drawable pokeballPic;
    TextView clickScore = null;
    private AlertDialog mDialog;
    //Boolean again = false;

    // load the ids of all of the tiles into an array
    private static int idList[] = {R.id.zero_zero, R.id.zero_one, R.id.zero_two, R.id.zero_three,
            R.id.one_zero, R.id.one_one, R.id.one_two, R.id.one_three,
            R.id.two_zero, R.id.two_one, R.id.two_two, R.id.two_three,
            R.id.three_zero, R.id.three_one, R.id.three_two, R.id.three_three};

    // shuffle method
    private void shuffle() {
        // add all my images into my array from resources
        Resources resources = getResources();
        for (int i = 1; i < 17; i++) {
            int identifier = resources.getIdentifier("pok" + i, "drawable", getActivity().getPackageName());
            Drawable drawable = getResources().getDrawable(identifier, null);
            picsArray.add(drawable);
        }

        // grab my pokeball pic
        int id = resources.getIdentifier("pokeballz", "drawable", getActivity().getPackageName());
        pokeballPic = getResources().getDrawable(id,null);

        // shuffle up the array of Images
        Collections.shuffle(picsArray);

        // create a new smaller array to play the game with
        for(int i = 0; i < 8; i++){
            Drawable pic = picsArray.get(i);
            gameList.add(pic);
            gameList.add(pic);
            picsArray.remove(i);
        }
        // shuffle the images again
        Collections.shuffle(gameList);

    }

    // onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.tiles_fragment, container, false);
        // get the click textView
        this.clickScore = (TextView) root.findViewById(R.id.clicks);

        // call shuffle to start the game
        shuffle();

        // create a new handler
        h = new Handler();

        // soundpool stuff - load files
        s = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        correctSound = s.load(getActivity(), R.raw.beep6, 1);
        wrongSound = s.load(getActivity(), R.raw.beep9, 1);
        tadaSound = s.load(getActivity(), R.raw.tada, 1);

        // grab the image buttons
        for (int ind = 0; ind < 16; ind++) {
            final int temporary = ind;
            final ImageButton imageButton = (ImageButton) root.findViewById(idList[ind]);

            // set on click listener to image buttons
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                // on click, increment count, update the textfield
                public void onClick(final View v) {
                    count++;
                    //clickScore.setText(score);
                    ((GameMain) getActivity()).update(count);

                    // flip animation
                    flip(v);

                    // set the image button background to the correct image
                    imageButton.setBackground(gameList.get(temporary));
                    compared.add(gameList.get(temporary));
                    // add the image button
                    TilesFragment.this.imageButton.add(imageButton);
                    // set clickable to false so you cant click twice
                    imageButton.setClickable(false);

                    // if n%2, this means that you have 2 tiles flipped
                    if (n % 2 == 0) {
                        //add thinking icon
                        ((GameMain)getActivity()).startThinking();
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                //compare the tiles
                                if (compareTo(compared) == true) {

                                    // play correct sound when true
                                    s.play(correctSound, 1f, 1f, 1, 0, 1f);

                                    //set the views to stay face up
                                    ImageButton imageButton1 = TilesFragment.this.imageButton.get(1);
                                    imageButton1.setBackground(compared.get(1));

                                    // not clickable anymore
                                    imageButton1.setEnabled(false);
                                    ImageButton imageButton2 = TilesFragment.this.imageButton.get(0);

                                    // not clickable anymore
                                    imageButton1.setEnabled(false);
                                    imageButton2.setBackground(compared.get(0));

                                    //empty lists
                                    compared.clear();
                                    TilesFragment.this.imageButton.clear();

                                    //increment score
                                    score++;

                                    // when score = 8, game is over
                                    if (score == 8) {

                                        //((GameMain) getActivity()).stopMedia();
                                        s.play(tadaSound, 1f, 1f, 1, 0, 1f);

                                        //mediaPlayer = MediaPlayer.create(getContext(), R.raw.victory);
                                        //mediaPlayer.setVolume(0.5f, 0.5f);
                                        //mediaPlayer.setLooping(true);
                                        //mediaPlayer.start();

                                        // display a message to the user at the end of the game
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                        builder.setMessage(R.string.winGame);
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

                                // if the images arent the same
                                } else {
                                    // play the wrong sound
                                    s.play(wrongSound, 1f, 1f, 1, 0, 1f);

                                    //set the images back to hockeypucks
                                    ImageButton imageButton1 = TilesFragment.this.imageButton.get(1);
                                    imageButton1.setBackground(pokeballPic);
                                    // reset the image buttons to clickable
                                    imageButton1.setClickable(true);

                                    ImageButton imageButton2 = TilesFragment.this.imageButton.get(0);
                                    imageButton2.setBackground(pokeballPic);
                                    // reset the image buttons to clickable
                                    imageButton2.setClickable(true);

                                    //empty lists
                                    compared.clear();
                                    TilesFragment.this.imageButton.clear();

                                }
                                // stop thinking
                                ((GameMain) getActivity()).stopThinking();
                            }
                            // flip back delay
                        }, 800);
                    }
                    // increment n
                    n++;
                }
            });
        }

        // return root view
        return root;
    }


    // found on the android developer website
    //https://developer.android.com/training/animation/cardflip.html#views
    public void flip(View view){
        // load
        Animator leftIn = AnimatorInflater.loadAnimator(getContext(), R.animator.card_flip_left_in);
        Animator leftOut = AnimatorInflater.loadAnimator(getContext(), R.animator.card_flip_left_out);
        Animator rightIn = AnimatorInflater.loadAnimator(getContext(), R.animator.card_flip_right_in);
        Animator rightOut = AnimatorInflater.loadAnimator(getContext(), R.animator.card_flip_right_out);
        // set
        leftIn.setTarget(view);
        leftOut.setTarget(view);
        rightIn.setTarget(view);
        rightOut.setTarget(view);
        // start
        leftIn.start();
        leftOut.start();
        rightIn.start();
        leftOut.start();
    }

    // compare method - compares the drawables at an instance
    private Boolean compareTo(ArrayList<Drawable> drawable){
        Drawable.ConstantState A = drawable.get(0).getConstantState();
        Drawable.ConstantState B = drawable.get(1).getConstantState();
        if (A.equals(B)){
            return true;
        }
        return false;
    }


}
