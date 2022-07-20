package com.brayo.androidwatermeter.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.brayo.androidwatermeter.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {
    @BindView(R.id.edStaffID)
    EditText staffID;
    @BindView(R.id.edEmailNew)
    EditText newAccountEmail;
    @BindView(R.id.edPasswordNew)
    EditText newAccountPassword;
    @BindView(R.id.bvRegister)
    Button registerUser;
    @BindView(R.id.bvHome)
    Button returnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        // Variables


        // Button Navigation
        // 1. Register User
        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 2. Abort
        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LauncherActivity.class);
                startActivity(intent);

            }
        });



    }
}