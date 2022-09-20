package com.rkc.userarchitectsih;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

public class ConnectivityCheck extends BroadcastReceiver {
    Context mContext;
    Intent mIntent;
    Dialog dialog;
    BroadcastReceiver broadcastReceiver;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext=context;
        mIntent=intent;
        if (isConnected(context)){
        }else{
            showDialog();
        }
    }
    public  boolean isConnected(Context context){
        try {
            ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            return (networkInfo!=null && networkInfo.isConnected());
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void showDialog(){
        dialog= new Dialog(mContext);
        dialog.setContentView(R.layout.connectivity_check);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        LottieAnimationView lottieAnimationView=dialog.findViewById(R.id.splash);
        int nightModeFlags =
                dialog.getContext().getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                // lottieAnimationView.setAnimation(R.raw.internet_error_dark_mode);
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                lottieAnimationView.setAnimation(R.raw.internet_error_light_mode);
                break;

//            case Configuration.UI_MODE_NIGHT_UNDEFINED:
//                doStuff();
//                break;
        }
        Button okButton= dialog.findViewById(R.id.exit);
        Button tryButton= dialog.findViewById(R.id.tryAgain);
        dialog.show();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        tryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onReceive(mContext,mIntent);
            }
        });

    }
}
