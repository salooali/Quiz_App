package com.example.quizapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.adapters.optionAdapter;
import com.example.quizapp.models.question;
import com.example.quizapp.models.quiz;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class questionActivity extends AppCompatActivity {

    public ArrayList<quiz> quiz = new ArrayList<com.example.quizapp.models.quiz>();
    Map<String, question> quest = new HashMap<>();


    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        bindViews();

    }



    private void bindViews() {
        question quest = new question("Which is the only bird that can fly backward?",
                "Sunbird", "Kingfisher", "Honey eater", "Hummingbird", "Hummingbird");
        TextView description = findViewById(R.id.description);
        description.setText(quest.description);
        optionAdapter optionAdap = new optionAdapter(quest);
        RecyclerView optionList = findViewById(R.id.optionList);
        optionList.setLayoutManager(new LinearLayoutManager(this));
        optionList.setAdapter(optionAdap);
    }


}