package com.rkc.userarchitectsih.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rkc.userarchitectsih.Arch.Acitivites.BuildingDetailsActivity;
import com.rkc.userarchitectsih.Database.DraftProjectRoomDataBase;
import com.rkc.userarchitectsih.JsonClasses.Architect;
import com.rkc.userarchitectsih.JsonClasses.BuildingDetails;
import com.rkc.userarchitectsih.JsonClasses.BuildingProject;
import com.rkc.userarchitectsih.R;
import com.rkc.userarchitectsih.dto.ArchitectRquest;
import com.rkc.userarchitectsih.dto.ArctitectResponse;
import com.rkc.userarchitectsih.server.ServerRequestRepository;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StructuralEmailFragment extends Fragment {
    TextInputEditText emailInput;
    TextInputLayout emailInputLayout;
    TextView askEmail, askEmailSub;
    Button nextButton;
    String mode;
    String projectID;
    ArrayList<Architect> architectDataArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_email_client, container, false);
        emailInput = view.findViewById(R.id.input_email_et);
        askEmail = view.findViewById(R.id.ask_email);
        askEmailSub = view.findViewById(R.id.ask_email_sub);
        nextButton = view.findViewById(R.id.email_next_Button);
        emailInputLayout= view.findViewById(R.id.input_email_layout);

        askEmail.setText(getResources().getString(R.string.structuralEmail));
        askEmailSub.setText(getResources().getString(R.string.askStructuralSub));
        nextButton.setText(getResources().getString(R.string.projectAskEmailButton));

        Bundle bundle = this.getArguments();
        projectID = bundle.getString("projectID");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    emailInputLayout.setError(null);
                    emailInput.setHint(getString(R.string.emailExample));
                } else {
                    emailInput.setHint("");
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emailInput.getText().toString().equals("")){
                    emailInputLayout.setError(getString(R.string.thisIsMandatory));
                } else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput.getText().toString()).matches()){
                    emailInputLayout.setError(getString(R.string.invalidEmail));
                } else{
                    emailInputLayout.setError(null);
                    ArchitectRquest architectRquest = new ArchitectRquest(emailInput.getText().toString());
                    ServerRequestRepository serverRequestRepository = ServerRequestRepository.getInstance();
                    serverRequestRepository.getServerService().getArchitect(architectRquest).enqueue(new Callback<ArctitectResponse>() {
                        @Override
                        public void onResponse(Call<ArctitectResponse> call, Response<ArctitectResponse> response) {
                            ArctitectResponse arctitectResponse = response.body();
                            architectDataArrayList = new ArrayList<>(Arrays.asList(arctitectResponse.getEng()));
                            // Toast.makeText(getActivity(),architectDataArrayList.get(0).getEngName(), Toast.LENGTH_SHORT).show();
                            updateDataBase();
//                            Bundle bundle = new Bundle();
//                            bundle.putString("projectID",projectID);
//                            StructuralEmailFragment structuralEmailFragment = new StructuralEmailFragment();
//                            structuralEmailFragment.setArguments(bundle);
//                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, structuralEmailFragment).commit();
                            Intent intent = new Intent(getActivity(), BuildingDetailsActivity.class);
                            intent.putExtra("projectID",projectID);
                            startActivity(intent);
                        }
                        @Override
                        public void onFailure(Call<ArctitectResponse> call, Throwable t) {
                        }
                    });

                }

            }
        });
    }

    public void updateDataBase() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BuildingProject buildingProject = null;
                try {
                    buildingProject = DraftProjectRoomDataBase.getInstance(getActivity().getApplicationContext()) .draftProjectsDao().findProjectById(AESCrypt.encrypt(getActivity().getResources().getString(R.string.secretKey),projectID));
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }
                if (buildingProject != null) {
                    buildingProject.setStructural(architectDataArrayList.get(0));

                    DraftProjectRoomDataBase.getInstance(getActivity().getApplicationContext())
                            .draftProjectsDao()
                            .updateProject(buildingProject);

                }
            }
        }).start();

    }
}