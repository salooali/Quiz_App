package com.example.quizapp.models;

import java.util.HashMap;
import java.util.Map;

public class quiz {

    String id;
    public String title = "";

    public Map<String, question> questions = new HashMap<String, question>();

    public quiz(String id, String title) {
        this.id = id;
        this.title = title;
        this.questions = questions;
    }

    public quiz() {

    }


    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuestions(Map<String, question> questions) {
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Map<String, question> getQuestions() {
        return questions;
    }
}

