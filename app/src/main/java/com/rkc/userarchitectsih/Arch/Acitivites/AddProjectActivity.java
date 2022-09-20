package com.rkc.userarchitectsih.Arch.Acitivites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.rkc.userarchitectsih.Fragments.ClientEmailFragment;
import com.rkc.userarchitectsih.Fragments.GramDetailsFragment;
import com.rkc.userarchitectsih.R;

import java.util.Objects;

public class AddProjectActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        // toolbar
        toolbar =findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.addProject));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new GramDetailsFragment()).commit();
    }
}