package com.monokoumacorp.p4_myreu.ui.create_meeting;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.monokoumacorp.p4_myreu.R;
import com.monokoumacorp.p4_myreu.ui.ViewModelFactory;

public class CreateMeetingActivity extends AppCompatActivity {

    String meetingLabel;

    public static Intent navigate(Context context) {
        return new Intent(context, CreateMeetingActivity.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_metting_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CreateMeetingViewModel viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(CreateMeetingViewModel.class);

        TextInputEditText meetingName = findViewById(R.id.add_meeting_name_textField);
        String meetingLocalisation = "Bruh";
        TextInputEditText participant = findViewById(R.id.add_participant_textField);
        TimePicker meetingHourTimePicker = findViewById(R.id.add_meeting_hour_timePicker);
        Button addMeetingButton = findViewById(R.id.add_meeting_button);
        meetingHourTimePicker.setIs24HourView(true);
        String meetingHour = String.valueOf(meetingHourTimePicker.getHour());
        String meetingMinute = String.valueOf(meetingHourTimePicker.getMinute());



        bindMeetingName(viewModel, meetingName);
        bindAddButton(viewModel, meetingName, participant, addMeetingButton, meetingLocalisation, meetingHour, meetingMinute);
        viewModel.getCloseActivitySingleLiveEvent().observe(this, aVoid -> finish());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindMeetingName(CreateMeetingViewModel viewModel, TextInputEditText meetingName) {
        meetingName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.onNameChanged(s.toString());
            }
        });
    }

    private void bindAddButton(CreateMeetingViewModel viewModel,
                               TextInputEditText meetingName,
                               TextInputEditText participant,
                               Button addMeetingButton,
                               String meetingLocalisation,
                               String meetingHour,
                               String meetingMinutes) {
        //noinspection ConstantConditions

        addMeetingButton.setOnClickListener(v -> viewModel.onAddButtonClicked(
            meetingLabel = meetingName.getText().toString() + " - " + meetingHour + ":" + meetingMinutes + " - " + meetingLocalisation,
            participant.getText().toString()
        ));
        viewModel.getIsSaveButtonEnabledLiveData().observe(this, isSaveButtonEnabled -> addMeetingButton.setEnabled(isSaveButtonEnabled));
    }
}