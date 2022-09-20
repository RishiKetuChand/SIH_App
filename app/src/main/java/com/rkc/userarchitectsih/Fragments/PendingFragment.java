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

import com.rkc.userarchitectsih.Adapter.PendingAdapter;
import com.rkc.userarchitectsih.R;
import com.rkc.userarchitectsih.dto.PendingRecyclerData;

import java.util.ArrayList;
import java.util.List;

public class PendingFragment extends Fragment {
    private RecyclerView recyclerView;
    private PendingAdapter pendingAdapter;
    private List<PendingRecyclerData> recyclerData;
    private EditText searchingText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending, container, false);
        recyclerView = view.findViewById(R.id.pendingRecyclerView);
        searchingText =view.findViewById(R.id.searchText);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerData = new ArrayList<>();
        recyclerData.add(new PendingRecyclerData("KA17HR567850","Abhishek N","Nirvana",
                "Document verification","24/09/2022","18/09/2022","Document Submission",
                "18/09/2022","#045256","100"));
        recyclerData.add(new PendingRecyclerData("KA17DV987356","Naveen R L","Bodhi Graha",
                "Ground verification","19/09/2022","16/09/2022","Document Verification",
                "16/09/2022","#FB3737","15"));
        pendingAdapter = new PendingAdapter(recyclerData,getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(pendingAdapter);

        searchingText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pendingAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}