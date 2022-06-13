package com.monokoumacorp.p4_myreu.ui.list;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.monokoumacorp.p4_myreu.data.Meeting;
import com.monokoumacorp.p4_myreu.data.MeetingRepository;

import java.util.ArrayList;
import java.util.List;

public class MeetingViewModel extends ViewModel {

    @NonNull
    private final MeetingRepository meetingRepository;

    public MeetingViewModel(@NonNull MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public LiveData<List<MeetingViewStateItem>> getMeetingViewStateItemsLiveData() {
        return Transformations.map(meetingRepository.getMeetingsLiveData(), meetings -> {
            List<MeetingViewStateItem> meetingViewStateItems = new ArrayList<>();

            for (Meeting meeting : meetings) {
                meetingViewStateItems.add(
                    new MeetingViewStateItem(
                        meeting.getId(),
                        meeting.getName(),
                        meeting.getParticipant()
                    )
                );
            }
            return meetingViewStateItems;
        });
    }
    public void onDeleteMeetingClicked(long meetingId) {
        meetingRepository.deleteMeeting(meetingId);
    }
}
