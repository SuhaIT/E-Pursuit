package com.jerucloud.epursuit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.viewpager);
        tabLayout=findViewById(R.id.tabs);
        ViewPagerAdapter viewpageradapter=new ViewPagerAdapter(MainActivity.this.getSupportFragmentManager());
        viewpageradapter.AddFragment(new loginseekers(),"Job Seeker");
        viewpageradapter.AddFragment(new loginrecuritor(),"Recruiter");
        viewPager.setAdapter(viewpageradapter);
        tabLayout.setupWithViewPager(viewPager);
    }


}