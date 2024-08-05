package com.example.ccsitacademicapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Registration_Students extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_students);
    }

    public void Login_Student(View view) {
        Intent intent = new Intent(this, Login_Students.class);
        startActivity(intent);
    }
}