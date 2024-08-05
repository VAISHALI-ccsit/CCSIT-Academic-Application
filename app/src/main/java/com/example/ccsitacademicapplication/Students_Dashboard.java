package com.example.ccsitacademicapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Students_Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_dashboard);
    }

    public void BCA_Login(View view) {
        Intent intent = new Intent(this, BCA_Login.class);
        startActivity(intent);
    }

    public void MCA_Login(View view) {
        Intent intent = new Intent(this, MCA_Login.class);
        startActivity(intent);
    }

    public void BTech_Login(View view) {
        Intent intent = new Intent(this, BTech_Login.class);
        startActivity(intent);
    }

    public void MTech_Login(View view) {
        Intent intent = new Intent(this, MTech_Login.class);
        startActivity(intent);
    }

    public void BSc_Login(View view) {
        Intent intent = new Intent(this, BSc_Login.class);
        startActivity(intent);
    }

    public void MSc_Login(View view) {
        Intent intent = new Intent(this, MSc_Login.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Choose_Page.class);
        startActivity(intent);
        super.onBackPressed();
    }
}