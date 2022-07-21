package com.brayo.androidwatermeter.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.brayo.androidwatermeter.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

    // Firebase Class
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // firebase object
        mAuth = FirebaseAuth.getInstance();

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
        // hide keyboard from screen
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(login.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

        // call progress bar to show activity
        launchingMainActivity.setVisibility(View.VISIBLE);

        // firebase function
        mAuth.signInWithEmailAndPassword(accountEmail, accountPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            launchingMainActivity.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(LoginActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseUser user = mAuth.getCurrentUser();
//
//        if (user != null){
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//        }
//
//    }
}