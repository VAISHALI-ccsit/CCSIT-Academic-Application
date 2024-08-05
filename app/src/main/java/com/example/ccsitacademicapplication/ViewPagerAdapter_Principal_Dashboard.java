package com.example.ccsitacademicapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter_Principal_Dashboard extends FragmentPagerAdapter {


    public ViewPagerAdapter_Principal_Dashboard(@NonNull FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdapter_Principal_Dashboard(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new Principal_Launch_Fragment();
        }else if(position==1){
            return new bca_fragment();
        }else if(position==2){
            return new Principal_Launch_Fragment();
        }else if(position==3){
            return new Principal_Launch_Fragment();
        }else if(position==4){
            return new Principal_Launch_Fragment();
        }else if(position==5){
            return new Principal_Launch_Fragment();
        }else { //7th item
            return new Principal_Launch_Fragment();
        }
    }

    @Override
    public int getCount() {
        return 7; //No of tabs
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Launch";
        }else if(position==1){
            return "BCA";
        }else if(position==2){
            return "MCA";
        }else if(position==3){
            return "B.Sc.";
        }else if(position==4){
            return "M.Sc.";
        }else if(position==5){
            return "B.Tech.";
        }else{
            return "M.tech";
        }
    }
}
