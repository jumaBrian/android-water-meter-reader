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

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.etEmailLogin)
    EditText userEmailLogin;
    @BindView(R.id.etPasswordLogin)
    EditText userPasswordLogin;
    @BindView(R.id.bvLogin)
    Button login;
    @BindView(R.id.bv_create_account)
    Button createAccount;
    @BindView(R.id.bv_forgot_password)
    Button forgotPassword;
    @BindView(R.id.progressBarLogin)
    ProgressBar launchingMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // button navigation
        // 1. Log in button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();

            }
        });

        // 2. Create account button
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);

            }
        });

        // 3. Forgot password button
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void userLogin() {
        // fetch user input
        String accountEmail = Objects.requireNonNull(userEmailLogin.getText().toString().trim());
        String accountPassword = Objects.requireNonNull(userPasswordLogin.getText().toString().trim());

        // login logic
        if (accountEmail.isEmpty()) {
            userEmailLogin.setError("User Email is required");
            userEmailLogin.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(accountEmail).matches()) {
            userEmailLogin.setError("Please provide a valid email");
            userEmailLogin.setText("");
            userEmailLogin.requestFocus();
            return;
        }
        if (accountPassword.isEmpty()) {
            userPasswordLogin.setError("Enter password to continue");
            userPasswordLogin.requestFocus();
            return;
        }
        // call progress bar to show activity

        launchingMainActivity.setVisibility(View.VISIBLE);


    }
}