package com.rkc.userarchitectsih.Client.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rkc.userarchitectsih.Fragments.PendingFragment;
import com.rkc.userarchitectsih.Fragments.SanctionedFragment;

public class ClientViewPagerAdapter extends FragmentPagerAdapter {
    public ClientViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }
    String[] fragmentNames = {"Pending", "Sanctioned"};

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PendingFragment();
            case 1:
                return new SanctionedFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragmentNames.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentNames[position];
    }
}
