package com.rkc.userarchitectsih.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.rkc.userarchitectsih.Adapter.DraftAdapter;
import com.rkc.userarchitectsih.R;
import com.rkc.userarchitectsih.dto.DraftRecyclerData;

import java.util.ArrayList;
import java.util.List;

public class DraftFragment extends Fragment {
    private RecyclerView recyclerView;
    private DraftAdapter draftAdapter;
    private List<DraftRecyclerData> recyclerData;
    private EditText searchingText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_draft, container, false);
        recyclerView = view.findViewById(R.id.draftRecyclerView);
        searchingText =view.findViewById(R.id.searchText);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerData = new ArrayList<>();
        recyclerData.add(new DraftRecyclerData("TEMP65839201","Harish N","Bhuimkalaya"));
        recyclerData.add(new DraftRecyclerData("TEMP88434890","Shashank B","Aashrayam"));
        recyclerData.add(new DraftRecyclerData("TEMP99601345","Shrilaxmi P S","Ambar"));
        draftAdapter = new DraftAdapter(recyclerData,getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(draftAdapter);

        //KA17HR567850  KA17DV987356  KA17CT008734

        searchingText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                draftAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}