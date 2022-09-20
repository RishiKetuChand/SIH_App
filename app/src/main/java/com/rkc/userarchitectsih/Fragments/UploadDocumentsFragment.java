package com.rkc.userarchitectsih.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rkc.userarchitectsih.R;
import com.rkc.userarchitectsih.dto.ProjectDetailsJosn;
import com.rkc.userarchitectsih.server.ServerRequestRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadDocumentsFragment extends Fragment {
    String projectID,gramID, clientID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = this.getArguments();
        projectID = bundle.getString("projectID");
        gramID= bundle.getString("gramID");
        clientID= bundle.getString("clientID");
        View view=inflater.inflate(R.layout.fragment_upload_documents, container, false);

;
        //
        // Toast.makeText(getActivity(), clientID, Toast.LENGTH_SHORT).show()
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
                ProjectDetailsJosn projectDetailsJosn = new ProjectDetailsJosn(projectID,clientID,gramID,"eng001");
        ServerRequestRepository serverRequestRepository = ServerRequestRepository.getInstance();
        serverRequestRepository.getServerService().uploadProject(projectDetailsJosn).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Toast.makeText(getActivity(),response.body().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
}