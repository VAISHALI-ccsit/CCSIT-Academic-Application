package com.example.ccsitacademicapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class bca_dashboard extends FragmentActivity {

    Fragment fragment;//fragment_container_view
    Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bca_dashboard);

        ((LinearLayout) findViewById(R.id.linear)).removeAllViews();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        bca_fragment fnv = new bca_fragment();
        fragmentTransaction.add(R.id.fragment, fnv).commit();
       /* but=findViewById(R.id.but);
        //fragment =findViewById(R.id.fragment_container_view);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ConstraintLayout) findViewById(R.id.searchVehicles)).removeAllViews();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                bca_fragment fnv = new bca_fragment();
                fragmentTransaction.add(R.id.fragment_container_view, fnv).commit();
            }
        });*/
    }
}