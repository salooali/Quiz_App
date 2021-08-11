package com.example.quizapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.adapters.quizAdapter;
import com.example.quizapp.models.quiz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Home extends AppCompatActivity {
    private FirebaseAuth mAuth;

    ActionBarDrawerToggle obj;
    ArrayList<com.example.quizapp.models.quiz> quizList = new ArrayList<>();
    quizAdapter adapter = new quizAdapter(quizList);
    FirebaseFirestore firebaseFirestore;


    RecyclerView recyclerView;

    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setUpFirestore();
        setUpViews();
        setUpRecyclerView();
        setUpDatePicker();


        logout = findViewById(R.id.btnLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Home.this, MainActivity.class));
                finish();
            }


        });


    }

    private void setUpDatePicker() {
        FloatingActionButton date;
        date = findViewById(R.id.btnDatePicker);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker obj = MaterialDatePicker.Builder.datePicker().build();
                obj.show(getSupportFragmentManager(), "DatePicker");

                obj.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        Log.d("DATEPICKER", obj.getHeaderText());

                        String pattern = "DD-MM-yyyy";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                        String date = simpleDateFormat.format(selection);
                        Log.d("DatePicker", date);

                        Intent intent = new Intent(Home.this, questionActivity.class);
                        intent.putExtra("DATE", date);
                        startActivity(intent);
                    }
                });

                obj.addOnNegativeButtonClickListener(V1 -> {
                    Log.d("DATEPICKER", obj.getHeaderText());
                });

                obj.addOnCancelListener(dialog -> {
                    Log.d("DATEPICKER", "Date Picker Cancelled!");
                });

            }
        });

    }


    private void populateDummyData() {
        quizList.add(new quiz("12-01-2021", "MAD"));
        quizList.add(new quiz("12-01-2021", "CNET"));
        quizList.add(new quiz("12-01-2021", "WTECH"));
        quizList.add(new quiz("12-01-2021", "DBMS"));
        quizList.add(new quiz("12-01-2021", "MAD"));
        quizList.add(new quiz("12-01-2021", "CNET"));
        quizList.add(new quiz("12-01-2021", "WTECH"));
        quizList.add(new quiz("12-01-2021", "DBMS"));
        quizList.add(new quiz("12-01-2021", "MAD"));
        quizList.add(new quiz("12-01-2021", "CNET"));
        quizList.add(new quiz("12-01-2021", "WTECH"));
        quizList.add(new quiz("12-01-2021", "DBMS"));


    }


    private void setUpViews() {

        setUpFirestore();
        setUpDrawerLayout();
    }

    private void setUpFirestore() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        String TAG = "WISHA123";

        firebaseFirestore.collection("quizzes").addSnapshotListener((value, error) -> {
            if (value != null) {
                Log.i(TAG, "setUpFirestore: " + value);
                Log.i(TAG, value.toObjects(quiz.class).toString());
                quizList.clear();
                quizList.addAll(value.toObjects(quiz.class));
                adapter.notifyDataSetChanged();

            }
            if (value == null || error != null) {
                Toast.makeText(this, "Cannot retrieve Data", Toast.LENGTH_SHORT);
            }
        });


    }


    private void setUpDrawerLayout() {
        setSupportActionBar(findViewById(R.id.appBar));
        obj = new ActionBarDrawerToggle(Home.this, findViewById(R.id.main_drawer), R.string.app_name, R.string.app_name);
        obj.syncState();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (obj.onOptionsItemSelected(item)) {
            return (true);

        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpRecyclerView() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        RecyclerView recyclerView = findViewById(R.id.quizRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);

    }

}