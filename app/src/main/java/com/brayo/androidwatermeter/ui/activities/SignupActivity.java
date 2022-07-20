package com.brayo.androidwatermeter.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.brayo.androidwatermeter.R;

import java.util.Objects;

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
    @BindView(R.id.progressBarRegister)
    ProgressBar creatingAccount;

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
                createUser();

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

    private void createUser() {
        // fetch user input
        String staffIdentity = Objects.requireNonNull(staffID.getText().toString().trim());
        String newEmail = Objects.requireNonNull(newAccountEmail.getText().toString().trim());
        String newPassword = Objects.requireNonNull(newAccountPassword.getText().toString().trim());

        if (staffIdentity.isEmpty()) {
            staffID.setError("Please provide your Staff ID/Number");
            staffID.requestFocus();
            return;
        }
        if (newEmail.isEmpty()) {
            newAccountEmail.setError("Please provide your email");
            newAccountEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()) {
            newAccountEmail.setError("Please provide a valid email");
            newAccountEmail.setText("");
            newAccountEmail.requestFocus();
            return;
        }
        if (newPassword.isEmpty()) {
            newAccountPassword.setError("Please provide a password");
            newAccountPassword.requestFocus();
            return;
        }
        if (newPassword.length() < 6) {
            newAccountPassword.setError("Min password length should be 6 characters");
            newAccountPassword.requestFocus();
            return;
        }
        // call progress bar to show activity
        creatingAccount.setVisibility(View.VISIBLE);


    }
}