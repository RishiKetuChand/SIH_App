package com.rkc.userarchitectsih.Govt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.rkc.userarchitectsih.Adapter.AssistsntAdapter;
import com.rkc.userarchitectsih.Adapter.CaseWorkerAdapter;
import com.rkc.userarchitectsih.R;
import com.rkc.userarchitectsih.dto.CaseWorkerData;

import java.util.ArrayList;

public class AssistantEngActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    AssistsntAdapter assistsntAdapter;
    private EditText searchingText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant_eng);

        searchingText =findViewById(R.id.searchText);
        Intent intent = getIntent();
        ArrayList<CaseWorkerData> caseWorkerData = intent.getExtras().getParcelableArrayList("got");
        recyclerView = findViewById(R.id.recyclerView);
        assistsntAdapter = new AssistsntAdapter(AssistantEngActivity.this,caseWorkerData);
        recyclerView.setLayoutManager(new LinearLayoutManager(AssistantEngActivity.this));
        recyclerView.setAdapter(assistsntAdapter);
        assistsntAdapter.setData(caseWorkerData);
        assistsntAdapter.notifyDataSetChanged();


        searchingText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                assistsntAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });
    }
}