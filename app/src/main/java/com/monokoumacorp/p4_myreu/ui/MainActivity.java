package com.monokoumacorp.p4_myreu.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.monokoumacorp.p4_myreu.R;
import com.monokoumacorp.p4_myreu.data.Meeting;
import com.monokoumacorp.p4_myreu.ui.add.CreateMeetingActivity;
import com.monokoumacorp.p4_myreu.ui.list.MeetingAdapter;
import com.monokoumacorp.p4_myreu.ui.list.MeetingViewModel;
import com.monokoumacorp.p4_myreu.ui.list.MeetingViewStateItem;
import com.monokoumacorp.p4_myreu.ui.list.OnMeetingClickedListenner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LifecycleOwner lifecycleOwner = this;
        FloatingActionButton fab = findViewById(R.id.fab_create_meeting);
        RecyclerView recyclerView = findViewById(R.id.meeting_recyclerView);

        MeetingViewModel viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(MeetingViewModel.class);

        MeetingAdapter adapter = new MeetingAdapter(new OnMeetingClickedListenner() {
            @Override
            public void onMeetingClicked(long meetingId) {

            }

            @Override
            public void onDeleteMeetingClicked(long meetingid) {
                viewModel.onDeleteMeetingClicked(meetingid);
            }
        });
        recyclerView.setAdapter(adapter);

        //Todo: redirect to meeting details
        fab.setOnClickListener(v -> startActivity(CreateMeetingActivity.navigate(this)));
        viewModel.getMeetingViewStateItemsLiveData().observe(lifecycleOwner, meetingViewStateItems ->
            adapter.submitList(meetingViewStateItems)
            );
    }
}