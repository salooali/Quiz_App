package com.example.quizapp.utils;

import com.example.quizapp.R;

import java.util.Random;

public class iconPicker {

    private int currentIconIndex = 0;
    int[] arrayOfIcons = {
            R.drawable.ic_quiz1,
            R.drawable.ic_quiz2,
            R.drawable.ic_quiz3,
            R.drawable.ic_quiz4,
            R.drawable.ic_quiz5,
            R.drawable.ic_quiz6,
            R.drawable.ic_quiz7,
            R.drawable.ic_quiz8,
            R.drawable.ic_quiz5,
            R.drawable.ic_quiz6,
            R.drawable.ic_quiz7,
            R.drawable.ic_quiz8,
    };


    public int getIcon() {

        Random rand = new Random();
        int random_int = rand.nextInt(arrayOfIcons.length-1);
        currentIconIndex = random_int;
        //currentIconIndex = (currentIconIndex + 1) % arrayOfIcons.length;
        return arrayOfIcons[currentIconIndex];
    }


}