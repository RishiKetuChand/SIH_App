package com.rkc.userarchitectsih.Client.Acitvites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rkc.userarchitectsih.R;
import com.rkc.userarchitectsih.dto.Login;
import com.rkc.userarchitectsih.server.ServerRequestRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    EditText name, password;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        name = findViewById(R.id.email);
        password= findViewById(R.id.password);
        submit= findViewById(R.id.button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login login= new Login(name.getText().toString(),password.getText().toString());
                ServerRequestRepository serverRequestRepository = ServerRequestRepository.getInstance();
                serverRequestRepository.getServerService().login(login).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(SignInActivity.this, "LoginDone", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });

    }
}