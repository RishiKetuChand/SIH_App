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

import com.rkc.userarchitectsih.Adapter.SanctionedAdapter;
import com.rkc.userarchitectsih.R;
import com.rkc.userarchitectsih.dto.SanctionedRecyclerData;

import java.util.ArrayList;
import java.util.List;

public class SanctionedFragment extends Fragment {
    private RecyclerView recyclerView;
    private SanctionedAdapter sanctionedAdapter;
    private List<SanctionedRecyclerData> recyclerData;
    private EditText searchingText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sanctioned, container, false);
        recyclerView = view.findViewById(R.id.sanctionedRecyclerView);
        searchingText =view.findViewById(R.id.searchText);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerData = new ArrayList<>();
        recyclerData.add(new SanctionedRecyclerData("KA17DVG77693","Sohan S P","Suloyanam","10/09/2002"));
        sanctionedAdapter = new SanctionedAdapter(recyclerData,getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(sanctionedAdapter);

        searchingText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                sanctionedAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}