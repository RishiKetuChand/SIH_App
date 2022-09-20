package com.rkc.userarchitectsih.Fragments;

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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rkc.userarchitectsih.R;

public class AddressFragment extends Fragment {

    private AutoCompleteTextView stateAuto, districtAuto ;

    TextInputEditText flatInput, areaInput;

    private TextInputLayout stateLayout, districtLayout ;

    private String state="", district="";

    private ArrayAdapter<String> gramAdapter, stateAdapter,districtAdapter;

    private Button next;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        stateAuto = view.findViewById(R.id.state_auto);
        stateLayout = view.findViewById(R.id.input_state_layout);

        districtAuto = view.findViewById(R.id.district_auto);
        districtLayout = view.findViewById(R.id.input_district_layout);

        next= view.findViewById(R.id.next_Button);
        next.setClickable(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            next.setAllowClickWhenDisabled(false);
        }
        next.setEnabled(false);

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
            }
        });
        if(!state.equals("")) {
            if (!district.equals("")) {
                next.setEnabled(true);
            }
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get and pass input
            }
        });
    }
}