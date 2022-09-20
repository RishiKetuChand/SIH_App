package com.rkc.userarchitectsih.Govt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import com.rkc.userarchitectsih.R;
import com.rkc.userarchitectsih.dto.CaseWorkerData;
import com.rkc.userarchitectsih.dto.ProjectDeatilsResponse;
import com.rkc.userarchitectsih.server.ServerRequestRepository;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SampleActivity extends AppCompatActivity {
    ArrayList<CaseWorkerData> caseWorkerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        ServerRequestRepository serverRequestRepository = ServerRequestRepository.getInstance();
        serverRequestRepository.getServerService().getProjects().enqueue(new Callback<ProjectDeatilsResponse>() {
            @Override
            public void onResponse(Call<ProjectDeatilsResponse> call, Response<ProjectDeatilsResponse> response) {
                ProjectDeatilsResponse projectDeatilsResponse = response.body();
                caseWorkerData = new ArrayList<>(Arrays.asList(projectDeatilsResponse.getProject()));
                Intent activityIntent = new Intent(SampleActivity.this,CaseWorkerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("got", caseWorkerData);
                activityIntent.putExtras(bundle);
                startActivity(activityIntent);

            }

            @Override
            public void onFailure(Call<ProjectDeatilsResponse> call, Throwable t) {
                Toast.makeText(SampleActivity.this, "FAiled", Toast.LENGTH_SHORT).show();
            }
        });


    }
}