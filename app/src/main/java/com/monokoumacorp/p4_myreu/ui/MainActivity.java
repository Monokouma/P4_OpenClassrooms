package com.monokoumacorp.p4_myreu.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.monokoumacorp.p4_myreu.R;
import com.monokoumacorp.p4_myreu.ui.add.CreateMeetingActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab_create_meeting);

        //Todo: redirect to meeting details
        fab.setOnClickListener(v -> startActivity(CreateMeetingActivity.navigate(this)));
    }
}