package com.example.quizapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    TextView addEmail, addPassword, addSignUp;
    Button addLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        addEmail = findViewById(R.id.lEmail);
        addPassword = findViewById(R.id.lPassword);


        addSignUp = findViewById(R.id.lSignup);
        addSignUp.setOnClickListener(this);

        addLogin = findViewById(R.id.lLogin);
        addLogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lLogin:
                loginPage();
                break;

            case R.id.lSignup:
                startActivity(new Intent(this, Signup.class));
                finish();
        }

    }

    private void loginPage() {

        String email = addEmail.getText().toString().trim();
        String password = addPassword.getText().toString().trim();

        if(email.isEmpty()){
            addEmail.setError("Email Address is Required!");
            addEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            addPassword.setError("Password is Required!");
            addPassword.requestFocus();
            return;
        }


        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            addEmail.setError("Please provide valid email!");
            addEmail.requestFocus();
            return;
        }



        if(password.length() < 6){
            addPassword.setError("Password length must be greater than 6");
            addPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    //redirect to the home
                        startActivity(new Intent(login.this, Home.class));

                    }
                else{
                    Toast.makeText(login.this, "Failed to login. Please check your credentials", Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}