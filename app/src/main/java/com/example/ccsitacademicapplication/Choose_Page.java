package com.example.ccsitacademicapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Choose_Page extends AppCompatActivity {

    Button principal, faculties, students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_page);

        principal = findViewById(R.id.principal_but);
        faculties = findViewById(R.id.faculty_but);
        students = findViewById(R.id.student_login);

        principal_login();
    }

    private void principal_login() {

        principal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choose_Page.this, Login_Activity.class);
                startActivity(intent);
            }
        });
        faculties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choose_Page.this,Login_Faculties.class);
                startActivity(intent);
            }
        });
        students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choose_Page.this,Students_Dashboard.class);
                startActivity(intent);
            }
        });
    }

    public void Login_Student(View view) {
        Intent intent = new Intent(this, Login_Students.class);
        startActivity(intent);
    }
}