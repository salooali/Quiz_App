package com.example.quizapp.utils;

import java.util.Random;

public class colorPicker {

    private int currentColorIndex = 0;
    String[] arrayOfColors = {"#5294DB", "#52DBD1", "#CA52DB", "#DB5252","#DB6F52","#6552DB","#11164E" };


    public String getColor() {


        Random rand = new Random();
        int random_int = rand.nextInt(arrayOfColors.length-1);
        currentColorIndex = random_int;
        //currentColorIndex = (currentColorIndex + 1) % arrayOfColors.length;
        return arrayOfColors[currentColorIndex];
    }


}