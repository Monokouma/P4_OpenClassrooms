package com.monokoumacorp.p4_myreu.ui.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.monokoumacorp.p4_myreu.R;
import com.monokoumacorp.p4_myreu.ui.ViewModelFactory;
import com.monokoumacorp.p4_myreu.ui.create_meeting.CreateMeetingActivity;

public class MeetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_activity);

        LifecycleOwner lifecycleOwner = this;
        FloatingActionButton fab = findViewById(R.id.fab_create_meeting);
        RecyclerView recyclerView = findViewById(R.id.meeting_recyclerView);

        MeetingViewModel viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(MeetingViewModel.class);

        MeetingAdapter adapter = new MeetingAdapter(new OnMeetingClickedListenner() {
            @Override
            public void onMeetingClicked(long meetingId) {
                Log.i("Monokouma", "oui");
            }

            @Override
            public void onDeleteMeetingClicked(long meetingid) {
                viewModel.onDeleteMeetingClicked(meetingid);
            }
        });
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(v -> startActivity(CreateMeetingActivity.navigate(this)));
        viewModel.getMeetingViewStateItemsLiveData().observe(lifecycleOwner, meetingViewStateItems ->
            adapter.submitList(meetingViewStateItems)
            );
    }
}