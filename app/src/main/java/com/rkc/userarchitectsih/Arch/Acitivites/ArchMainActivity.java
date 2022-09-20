package com.rkc.userarchitectsih.Arch.Acitivites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.rkc.userarchitectsih.Arch.Adapter.ArchViewPageAdapter;
import com.rkc.userarchitectsih.ConnectivityCheck;
import com.rkc.userarchitectsih.R;

public class ArchMainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ExtendedFloatingActionButton extendedFloatingActionButton;
    private BroadcastReceiver broadcastReceiver;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arch_main);

        //toolbar
        toolbar =findViewById(R.id.toolbar);
        toolbar.setTitle("Hello Architect");
        setSupportActionBar(toolbar);

        //network connectivity checking
        broadcastReceiver= new ConnectivityCheck();
        registerRequest();

        // initialing the views
        tabLayout = findViewById(R.id.archTabLayout);
        viewPager = findViewById(R.id.archViewPager);
        extendedFloatingActionButton= findViewById(R.id.archFloatingButton);

        ArchViewPageAdapter archViewPageAdapter = new ArchViewPageAdapter(getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(archViewPageAdapter);

        extendedFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArchMainActivity.this, AddProjectActivity.class);
                startActivity(intent);
            }
        });
    }
    public void registerRequest(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }
    protected void unregisterRequest(){
        try {
            unregisterReceiver(broadcastReceiver);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterRequest();
    }
}