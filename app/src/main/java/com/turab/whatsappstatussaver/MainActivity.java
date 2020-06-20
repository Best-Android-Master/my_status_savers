package com.turab.whatsappstatussaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.turab.whatsappstatussaver.adapters.PagerAdapter;

public class MainActivity extends AppCompatActivity {


    ViewPager viewPager;
    Toolbar toolbarMainActivity;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        castIngIntItViews();

        setSupportActionBar(toolbarMainActivity);

        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);


    }

    public void castIngIntItViews()
    {
        viewPager = findViewById(R.id.viewPager);
        toolbarMainActivity = findViewById(R.id.toolbarMainActivity);
        tabLayout = findViewById(R.id.tabsLayout);
    }
}
