package com.example.quizapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private TextView getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getStarted = findViewById(R.id.btnGetStarted);
        getStarted.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGetStarted:
                if(FirebaseAuth.getInstance().getCurrentUser()!=null)
                {
                    Toast.makeText(MainActivity.this, "You are already logged in",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this, Home.class));
                }
                else {
                    startActivity(new Intent(this, login.class));
                    break;
                }
        }
    }
}