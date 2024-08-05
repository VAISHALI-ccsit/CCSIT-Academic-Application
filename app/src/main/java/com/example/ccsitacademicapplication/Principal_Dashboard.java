package com.example.ccsitacademicapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class Principal_Dashboard extends AppCompatActivity {

    TabLayout tablayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_dashboard);

        tablayout=findViewById(R.id.tab);
        viewPager=findViewById(R.id.view_Pager);

        ViewPagerAdapter_Principal_Dashboard adaptter = new ViewPagerAdapter_Principal_Dashboard(getSupportFragmentManager());
        viewPager.setAdapter(adaptter);

        tablayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Choose_Page.class);
        startActivity(intent);
        super.onBackPressed();
    }
}