package com.monokoumacorp.p4_myreu.ui.create_meeting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.monokoumacorp.p4_myreu.R;
import com.monokoumacorp.p4_myreu.ui.ViewModelFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class CreateMeetingActivity extends AppCompatActivity {

    LifecycleOwner lifecycleOwner = this;
    private static LocalTime meetingHour;
    AutoCompleteTextView roomDropDownMenu;

    public static Intent navigate(Context context) {
        return new Intent(context, CreateMeetingActivity.class);
    }

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
        TextView meetingHourLabel = findViewById(R.id.meetingTimeLabel);

        roomDropDownMenu = findViewById(R.id.room_autocompleteTextView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_items);
        roomDropDownMenu.setAdapter(adapter);



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

                LocalTime localTime = LocalTime.of(picker.getHour(), picker.getMinute());
                long epochSecond = ZonedDateTime.of(LocalDate.now(), localTime, ZoneId.of("UTC")).toEpochSecond();
                StringBuilder stringBuilder = new StringBuilder();
                meetingHourLabel.setVisibility(View.VISIBLE);
                meetingHourLabel.setText(stringBuilder.append("Heure de la réunion, ").append(localTime));
                meetingHour = localTime;
                hideKeyboard(this, view);
            });
        });




        viewModel.getCreateMeetingViewStateLiveData().observe(lifecycleOwner, createMeetingViewState -> {
            addMeetingButton.setEnabled(createMeetingViewState.isSaveButtonEnabled());
            if (addMeetingButton.isEnabled()) {addMeetingButton.setBackgroundColor(getResources().getColor(R.color.tufts_blue));}
            participantAdapter.submitList(createMeetingViewState.getParticipants());
            adapter.clear();
            adapter.addAll(createMeetingViewState.getRoomList());
        });

        bindMeetingName(viewModel, meetingName);
        bindParticipantButton(viewModel, addParticipant, participant);
        addMeetingButton.setOnClickListener(v ->
            viewModel.onAddButtonClicked(
                meetingName.getText().toString(),
                meetingHour,
                roomDropDownMenu.getText().toString()
            )
        );
        viewModel.getCloseActivitySingleLiveEvent().observe(this, aVoid -> finish());
        viewModel.getToastMessageSingleLiveEvent().observe(this, message -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show());
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