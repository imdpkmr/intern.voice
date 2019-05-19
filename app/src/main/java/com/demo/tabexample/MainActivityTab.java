package com.demo.tabexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class  MainActivityTab extends AppCompatActivity{
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        viewPager=findViewById(R.id.viewPager);
        tabLayout=findViewById(R.id.tabLayout);

        adapter=new TabAdapter(getSupportFragmentManager());
        //adapter.addFragment(new Tab1Fragment(),"Tab 1");
        //adapter.addFragment(new Tab2Fragment(),"TAb 2");
        adapter.addFragment(new MainActivity(),"Record");
        adapter.addFragment(new RecordingListActivity(),"List");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}