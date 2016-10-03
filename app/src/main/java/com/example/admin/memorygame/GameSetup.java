package com.example.admin.memorygame;

import java.util.Random;

/**
 * Created by admin on 10/2/16.
 */
public class GameSetup {

    private int[] tiles = null;

    public GameSetup(int NumberOfImages) {
        tiles = new int[NumberOfImages * 2];
        for(int i = 0; i < NumberOfImages; ++i){
            tiles[i*2] = i;
            tiles[i*2+1] = i;
        }
        shuffleTiles(tiles);
    }

    // http://stackoverflow.com/a/1520212
    private static void shuffleTiles(int[] arr) {
        Random rnd = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
    }
}
