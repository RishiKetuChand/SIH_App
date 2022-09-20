package com.rkc.userarchitectsih.Fragments;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputLayout;
import com.rkc.userarchitectsih.Database.DraftProjectRoomDataBase;
import com.rkc.userarchitectsih.JsonClasses.BuildingProject;
import com.rkc.userarchitectsih.R;
import com.rkc.userarchitectsih.dto.GramPanchayatDetails;
import com.rkc.userarchitectsih.dto.GramResponse;
import com.rkc.userarchitectsih.dto.StateDistrictJson;
import com.rkc.userarchitectsih.server.ServerRequestRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GramDetailsFragment extends Fragment {

    private AutoCompleteTextView gramAuto, stateAuto, districtAuto ;

    private TextInputLayout gramLayout, stateLayout, districtLayout ;

    private String gram="", state="",zone="", district="";

    private ArrayAdapter<String> gramAdapter, stateAdapter,districtAdapter;

    private Button next;

    private LottieAnimationView lottieAnimationView;

    ArrayList<GramPanchayatDetails> gramPanchayatArray;
    GramPanchayatDetails gramPanchayatDetails;

    public GramDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_gram_details, container, false);
        gramAuto = view.findViewById(R.id.gram_auto);
        gramLayout= view.findViewById(R.id.input_gram_layout);

        stateAuto = view.findViewById(R.id.state_auto);
        stateLayout = view.findViewById(R.id.input_state_layout);

        districtAuto = view.findViewById(R.id.district_auto);
        districtLayout = view.findViewById(R.id.input_district_layout);

        lottieAnimationView = view .findViewById(R.id.searching);
        lottieAnimationView.setVisibility(View.GONE);

        stateAdapter = new ArrayAdapter<>(getActivity(),R.layout.list_item,getResources().getStringArray(R.array.stateAndUnionList));
        districtAdapter = new ArrayAdapter<>(getActivity(),R.layout.list_item,getResources().getStringArray(R.array.district));

        // adapter setting
        stateAuto.setAdapter(stateAdapter);
        districtAuto.setAdapter(districtAdapter);

        next= view.findViewById(R.id.next_Button);
        next.setClickable(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            next.setAllowClickWhenDisabled(false);
        }
        next.setEnabled(false);



         return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stateAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                state = adapterView.getItemAtPosition(i).toString();
            }
        });

        districtAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                district = adapterView.getItemAtPosition(i).toString();
                if(!state.equals("")){
                    if(!district.equals("")){
                        // lottieAnimationView.setVisibility(View.VISIBLE);
                        StateDistrictJson stateDistrictJson = new StateDistrictJson(state,district);
                        ServerRequestRepository serverRequestRepository = ServerRequestRepository.getInstance();
                        serverRequestRepository.getServerService().getGramPanchayat(stateDistrictJson).enqueue(new Callback<GramResponse>() {
                            @Override
                            public void onResponse(Call<GramResponse> call, Response<GramResponse> response) {
                                GramResponse gramResponse = response.body();
                                gramPanchayatArray = new ArrayList<>(Arrays.asList( gramResponse.getGram()));
                                String[] gramList = new String[gramPanchayatArray.size()];
                                for(int i =0; i<gramPanchayatArray.size();i++){
                                    gramList[i]=(gramPanchayatArray.get(i).getGramPanchayatName());
                                }
                                gramAdapter = new ArrayAdapter<>(getActivity(),R.layout.list_item,gramList);
                                gramAuto.setAdapter(gramAdapter);
                                // Toast.makeText(getActivity(),gramPanchayatArray.get(1).getGramPanchayatName(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<GramResponse> call, Throwable t) {

                            }
                        });
                    }
                }
            }
        });

        gramAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                next.setEnabled(true);
                gramPanchayatDetails = gramPanchayatArray.get(i);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String projectID=UUID.randomUUID().toString();
                BuildingProject buildingProject = new BuildingProject(getContext(), projectID,"","","","",null,null,null,null,null,gramPanchayatDetails,"");
                InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
                insertAsyncTask.execute(buildingProject);
                Bundle bundle = new Bundle();
                bundle.putString("projectID",projectID);
                bundle.putString("gramID",gramPanchayatDetails.getGramPanchayatID());
                DeveloperEmailFragment developerEmailFragment = new DeveloperEmailFragment();
                developerEmailFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, developerEmailFragment).commit();
            }
        });
    }

    class InsertAsyncTask extends AsyncTask<BuildingProject, Void, Void> {

        @Override
        protected Void doInBackground(BuildingProject... buildingProjects) {
            DraftProjectRoomDataBase.getInstance(getActivity().getApplicationContext())
                    .draftProjectsDao()
                    .insertDao(buildingProjects[0]);

            return null;
        }
    }
}