package com.brayo.androidwatermeter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.brayo.androidwatermeter.ui.activities.LauncherActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvLauncher)
    TextView launcher;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        launcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opens Signup activity
                Intent intent = new Intent(MainActivity.this, LauncherActivity.class);
                startActivity(intent);
            }
        });


    }
}