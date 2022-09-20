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

public class PhoneNumberFragment extends Fragment {

    TextInputEditText phoneInput;
    TextInputLayout phoneInputLayout;
    TextView askPhone, askPhoneSub, askPhoneNote;
    Button nextButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_phone_number, container, false);
        phoneInput = view.findViewById(R.id.input_phone_et);
        askPhone = view.findViewById(R.id.ask_phone);
        askPhoneSub = view.findViewById(R.id.ask_phone_sub);
        askPhoneNote = view.findViewById(R.id.phone_note);
        nextButton = view.findViewById(R.id.phone_next_Button);
        phoneInputLayout= view.findViewById(R.id.input_phone_layout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        askPhone.setText(getString(R.string.askPhone));
        askPhoneSub.setText(getString(R.string.askPhoneSub));
        askPhoneNote.setText(getString(R.string.projectAskEmailNote));
        nextButton.setText(getString(R.string.next));

        phoneInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    phoneInputLayout.setError(null);
                    phoneInput.setHint(getString(R.string.phoneExample));
                } else {
                    phoneInput.setHint("");
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phoneInput.getText().toString().equals("")){
                    phoneInputLayout.setError(getString(R.string.thisIsMandatory));
                } else if(!Patterns.PHONE.matcher(phoneInput.getText().toString()).matches()){
                    phoneInputLayout.setError(getString(R.string.invalidPhone));
                } else{
                    phoneInputLayout.setError(null);
                    //TODO reuest for server
                }
            }
        });
    }
}