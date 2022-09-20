package com.rkc.userarchitectsih.Client.Acitvites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.rkc.userarchitectsih.Client.Adapter.ClientViewPagerAdapter;
import com.rkc.userarchitectsih.ConnectivityCheck;
import com.rkc.userarchitectsih.R;

import java.util.Objects;

public class ClientMainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ExtendedFloatingActionButton extendedFloatingActionButton;
    private BroadcastReceiver broadcastReceiver;
    private Dialog inputDialog,loadingDialog;
    private String applicationID;
    private LottieAnimationView searchingFileAnimation;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main);

        //toolbar
        toolbar =findViewById(R.id.toolbar);
        toolbar.setTitle("Hello Client");
        setSupportActionBar(toolbar);

        //network connectivity checking
        broadcastReceiver= new ConnectivityCheck();
        registerRequest();

        // initialing the views
        tabLayout = findViewById(R.id.clientTabLayout);
        viewPager = findViewById(R.id.clientViewPager);
        extendedFloatingActionButton= findViewById(R.id.clientFloatingButton);
        searchingFileAnimation = findViewById(R.id.searching);

        ClientViewPagerAdapter clientViewPagerAdapter = new ClientViewPagerAdapter(getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(clientViewPagerAdapter);

        extendedFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDialog= new Dialog(ClientMainActivity.this);
                inputDialog.setContentView(R.layout.building_id_dailog);
                inputDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                inputDialog.setCancelable(false);
                TextInputEditText applicationIDText = inputDialog.findViewById(R.id.et_id);
                Button cancel= inputDialog.findViewById(R.id.cancel);
                Button submit= inputDialog.findViewById(R.id.submit);
                inputDialog.show();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        inputDialog.dismiss();
                    }
                });
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        applicationID = applicationIDText.getText().toString();
                        if(applicationID.equals("")|| applicationID.length()<16){
                            Toast.makeText(ClientMainActivity.this,getString(R.string.please_enter_valid_application_id), Toast.LENGTH_SHORT).show();
                        } else{
                            //close input dialog
                            inputDialog.dismiss();
                            //open loading dialog
                            loadingDialog = new Dialog(ClientMainActivity.this);
                            //creating linear layout
                            LinearLayout linearLayout = new LinearLayout(ClientMainActivity.this);
                            // creating dimensions
                            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, (int) (300 * getResources().getDisplayMetrics().density));
                            // creating view i.e lottieAnimation
                            LottieAnimationView lottieAnimationView = new LottieAnimationView(ClientMainActivity.this);
                            lottieAnimationView.setAnimation(R.raw.searching_file);
                            lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
                            lottieAnimationView.playAnimation();
                            //adding View to layout
                            linearLayout.addView(lottieAnimationView);
                            //passing layout and dimensions to dialog
                            loadingDialog.setContentView(linearLayout,llp);
                            loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                            loadingDialog.setCancelable(false);
                            loadingDialog.show();

                            //TODO Send request for server
                            //searchingFileAnimation.setVisibility(View.VISIBLE);
                        }
                    }
                });
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