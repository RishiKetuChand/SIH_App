package com.rkc.userarchitectsih.Fragments;

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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rkc.userarchitectsih.R;
import com.rkc.userarchitectsih.dto.ClientDataJson;
import com.rkc.userarchitectsih.dto.ClientRequest;
import com.rkc.userarchitectsih.dto.ClientResponse;
import com.rkc.userarchitectsih.server.ServerRequestRepository;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientEmailFragment extends Fragment {

    TextInputEditText emailInput;
    TextInputLayout emailInputLayout;
    TextView askEmail, askEmailSub, askEmailNote;
    Button nextButton;
    String mode;
    String projectID,gramID;
    ArrayList<ClientDataJson> clientDataJsonArrayList;

    public ClientEmailFragment(String mode) {
        this.mode = mode;
    }

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
        askEmailNote = view.findViewById(R.id.email_note);
        nextButton = view.findViewById(R.id.email_next_Button);
        emailInputLayout= view.findViewById(R.id.input_email_layout);

        switch (mode){
            case "signIn":
                askEmail.setText(getResources().getString(R.string.signInAskEmail));
                askEmailSub.setText(getResources().getString(R.string.signInAskEmailSub));
                askEmailNote.setText(getResources().getString(R.string.signInAskEmailNote));
                nextButton.setText(getResources().getString(R.string.signInAskEmailButton));
                break;
            case "project":
                askEmail.setText(getResources().getString(R.string.projectAskEmail));
                askEmailSub.setText(getResources().getString(R.string.projectAskEmailSub));
                askEmailNote.setText(getResources().getString(R.string.projectAskEmailNote));
                nextButton.setText(getResources().getString(R.string.projectAskEmailButton));

                Bundle bundle = this.getArguments();
                projectID = bundle.getString("projectID");
                gramID= bundle.getString("gramID");
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
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
                }
                emailInputLayout.setError(null);
                switch(mode){
                    case "signIn":
                        break;
                    case "project":
                        ClientRequest clientRequest = new ClientRequest(emailInput.getText().toString());
                        ServerRequestRepository serverRequestRepository = ServerRequestRepository.getInstance();
                        serverRequestRepository.getServerService().getClient(clientRequest).enqueue(new Callback<ClientResponse>() {
                            @Override
                            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                                ClientResponse clientResponse = response.body();
                                clientDataJsonArrayList = new ArrayList<>(Arrays.asList(clientResponse.getClient()));
                                // Toast.makeText(getActivity(),architectDataArrayList.get(0).getEngName(), Toast.LENGTH_SHORT).show();
                                // updateDataBase();
                                Bundle bundle = new Bundle();
                                bundle.putString("projectID",projectID);
                                bundle.putString("gramID",gramID);
                                bundle.putString("clientID",clientDataJsonArrayList.get(0).getClientId());
                                UploadDocumentsFragment uploadDocumentsFragment = new UploadDocumentsFragment();
                                uploadDocumentsFragment.setArguments(bundle);
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, uploadDocumentsFragment).commit();

                            }

                            @Override
                            public void onFailure(Call<ClientResponse> call, Throwable t) {

                            }
                        });

                }

            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

//    public void updateDataBase() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                BuildingProject buildingProject = null;
//                try {
//                    buildingProject = DraftProjectRoomDataBase.getInstance(getActivity().getApplicationContext()) .draftProjectsDao().findProjectById(AESCrypt.encrypt(getActivity().getResources().getString(R.string.secretKey),projectID));
//                } catch (GeneralSecurityException e) {
//                    e.printStackTrace();
//                }
//                if (buildingProject != null) {
//                    buildingProject.set(clientDataJsonArrayList.get(0));
//
//                    DraftProjectRoomDataBase.getInstance(getActivity().getApplicationContext())
//                            .draftProjectsDao()
//                            .updateProject(buildingProject);
//
//                }
//            }
//        }).start();
//
//    }

}