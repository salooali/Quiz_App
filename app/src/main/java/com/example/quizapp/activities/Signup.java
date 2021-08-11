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
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;

    TextView editEmail, editPassword, editConfirmPassword, editLogin;
    Button editSignUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        editEmail = findViewById(R.id.suEmailAddress);
        editPassword = findViewById(R.id.suPassword);
        editConfirmPassword = findViewById(R.id.suConfirmPassword);

        editSignUp = findViewById(R.id.btnSuSignUp);
        editSignUp.setOnClickListener(this);

        editLogin = findViewById(R.id.btnSuLogin);
        editLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSuSignUp:
                signUp();
                break;

            case R.id.btnSuLogin:
                startActivity(new Intent(this, login.class));
                finish();
        }

    }

    private void signUp() {
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String confirmPassword = editConfirmPassword.getText().toString().trim();

        if(email.isEmpty()){
            editEmail.setError("Email Address is Required!");
            editEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editPassword.setError("Password is Required!");
            editPassword.requestFocus();
            return;
        }

        if(confirmPassword.isEmpty()){
            editConfirmPassword.setError("Please Re-enter the Password!");
            editConfirmPassword.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Please provide valid email!");
            editEmail.requestFocus();
            return;
        }


        if(password.length() < 6){
            editPassword.setError("Password length must be greater than 6");
            editPassword.requestFocus();
            return;
        }


        if(password != confirmPassword){
            editConfirmPassword.setError("Please Re-enter the correct password");
            editConfirmPassword.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(email,password);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Signup.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(Signup.this, -5.+"Failed to registered. Try Again!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }

                    }
                });



    }
}