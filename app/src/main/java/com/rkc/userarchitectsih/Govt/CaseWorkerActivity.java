package com.rkc.userarchitectsih.Govt;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.rkc.userarchitectsih.Adapter.CaseWorkerAdapter;
import com.rkc.userarchitectsih.JsonClasses.Architect;
import com.rkc.userarchitectsih.R;
import com.rkc.userarchitectsih.dto.ArctitectResponse;
import com.rkc.userarchitectsih.dto.CaseWorkerData;
import com.rkc.userarchitectsih.dto.ProjectDeatilsResponse;
import com.rkc.userarchitectsih.dto.ProjectDetailsJosn;
import com.rkc.userarchitectsih.server.ServerRequestRepository;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaseWorkerActivity extends AppCompatActivity {
    // ArrayList<CaseWorkerData> caseWorkerData;
    private RecyclerView recyclerView;
    CaseWorkerAdapter caseWorkerAdapter;
    private EditText searchingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_worker);
        searchingText =findViewById(R.id.searchText);

        Intent intent = getIntent();
        ArrayList <CaseWorkerData> caseWorkerData = intent.getExtras().getParcelableArrayList("got");
        recyclerView = findViewById(R.id.recyclerView);
        caseWorkerAdapter = new CaseWorkerAdapter(CaseWorkerActivity.this,caseWorkerData);
        recyclerView.setLayoutManager(new LinearLayoutManager(CaseWorkerActivity.this));
        recyclerView.setAdapter(caseWorkerAdapter);
        caseWorkerAdapter.setData(caseWorkerData);
        caseWorkerAdapter.notifyDataSetChanged();

        searchingText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                caseWorkerAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });


//        Handler handler = new Handler();
//        //getting data from room database
//        Thread thread= new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                //getting
//                ServerRequestRepository serverRequestRepository = ServerRequestRepository.getInstance();
//                serverRequestRepository.getServerService().getProjects().enqueue(new Callback<ProjectDeatilsResponse>() {
//                    @Override
//                    public void onResponse(Call<ProjectDeatilsResponse> call, Response<ProjectDeatilsResponse> response) {
//                        ProjectDeatilsResponse projectDeatilsResponse = response.body();
//                        caseWorkerData = new ArrayList<>(Arrays.asList(projectDeatilsResponse.getProject()));
//                    }
//
//                    @Override
//                    public void onFailure(Call<ProjectDeatilsResponse> call, Throwable t) {
//                        Log.e("Error", "on failure");
//                    }
//                });
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        //setting
//                        caseWorkerAdapter = new CaseWorkerAdapter(CaseWorkerActivity.this,caseWorkerData);
//                        recyclerView.setLayoutManager(new LinearLayoutManager(CaseWorkerActivity.this));
//                        recyclerView.setAdapter(caseWorkerAdapter);
//                    }
//                });
//            }
//        });
//
//        thread.start();
//        ServerRequestRepository serverRequestRepository = ServerRequestRepository.getInstance();
//        serverRequestRepository.getServerService().getProjects().enqueue(new Callback<ProjectDeatilsResponse>() {
//            @Override
//            public void onResponse(Call<ProjectDeatilsResponse> call, Response<ProjectDeatilsResponse> response) {
//                ProjectDeatilsResponse projectDeatilsResponse = response.body();
//                caseWorkerData = new ArrayList<>(Arrays.asList(projectDeatilsResponse.getProject()));
//
//
//            }
//
//            @Override
//            public void onFailure(Call<ProjectDeatilsResponse> call, Throwable t) {
//
//            }
//        });

//        recyclerView.setAdapter(caseWorkerAdapter);
//        caseWorkerAdapter.notifyDataSetChanged();

    }
}