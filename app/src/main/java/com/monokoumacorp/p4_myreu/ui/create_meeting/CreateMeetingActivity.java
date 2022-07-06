package com.monokoumacorp.p4_myreu.ui.create_meeting;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.monokoumacorp.p4_myreu.R;
import com.monokoumacorp.p4_myreu.ui.ViewModelFactory;

import java.util.List;

public class CreateMeetingActivity extends AppCompatActivity {

    LifecycleOwner lifecycleOwner = this;
    List<CreateMeetingParticipantViewStateItem> createMeetingViewStateItemList;

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
        TextInputEditText participant = findViewById(R.id.add_participant_textField);
        Button addMeetingButton = findViewById(R.id.add_meeting_button);
        FloatingActionButton addParticipant = findViewById(R.id.add_participant_button);
        RecyclerView participantRecyclerView = findViewById(R.id.add_participant_list);
        ParticipantAdapter participantAdapter = new ParticipantAdapter();
        ImageButton addHourMetting = findViewById(R.id.add_meetingHour);
        participantRecyclerView.setAdapter(participantAdapter);
        MaterialTimePicker picker = new MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setTitleText("Choisissez une heure pour la réunion")
            .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
            .setPositiveButtonText("Choisir")
            .setNegativeButtonText("Annuler")
            .build();

        addHourMetting.setOnClickListener(v -> {
            picker.show(getSupportFragmentManager(), "timePicker");
            picker.addOnPositiveButtonClickListener(view -> {
                Log.i("Monokouma", String.valueOf(picker.getHour() + ":" + picker.getMinute()));
                hideKeyboard(this, view);
            });
        });


        viewModel.getCreateMeetingViewStateLiveData().observe(lifecycleOwner, createMeetingViewState -> {
            addMeetingButton.setEnabled(createMeetingViewState.isSaveButtonEnabled());
            participantAdapter.submitList(createMeetingViewState.getParticipants());
        });

        bindMeetingName(viewModel, meetingName);
        bindParticipantButton(viewModel, addParticipant, participant);
        addMeetingButton.setOnClickListener(v ->
            viewModel.onAddButtonClicked(
                meetingName.getText().toString()
            )
        );
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

    private void bindParticipantButton(CreateMeetingViewModel viewModel, FloatingActionButton addParticipant, TextInputEditText participant) {

        addParticipant.setOnClickListener(v -> {
            if (!participant.getText().toString().equals("")) {
                viewModel.onAddParticipantButtonClicked(participant.getText().toString());
                participant.getText().clear();
            } else {
                participant.setError("Champ vide !");
            }

        });
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

    public void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}