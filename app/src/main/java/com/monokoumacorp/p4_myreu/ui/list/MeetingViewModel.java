package com.monokoumacorp.p4_myreu.ui.list;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.monokoumacorp.p4_myreu.data.Meeting;
import com.monokoumacorp.p4_myreu.data.MeetingRepository;
import com.monokoumacorp.p4_myreu.data.Participant;

import java.util.ArrayList;
import java.util.List;

public class MeetingViewModel extends ViewModel {

    @NonNull
    private final MeetingRepository meetingRepository;

    private final MediatorLiveData<List<MeetingViewStateItem>> mediatorLiveData = new MediatorLiveData<>();




    public MeetingViewModel(@NonNull MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;

        final LiveData<List<Meeting>> meetingsLiveData = meetingRepository.getMeetingsLiveData();

        mediatorLiveData.addSource(meetingsLiveData, meetings ->
            combine(meetings)
        );

    }

    private void combine(@Nullable List<Meeting> meetings) {
        if (meetings == null) {
            return;
        }

        List<MeetingViewStateItem> meetingViewStateItems = new ArrayList<>();

        for (Meeting meeting : meetings) {
            StringBuilder participantsStringBuilder = new StringBuilder();
            StringBuilder meetingStringBuilder = new StringBuilder();

            String meetingInfos = meetingStringBuilder.append(meeting.getName()).append(" - ").append(meeting.getMeetingHour()).append(" - ").append(meeting.getRoomName()).toString();

            List<Participant> participants = meeting.getParticipants();
            for (int i = 0, participantsSize = participants.size(); i < participantsSize; i++) {
                Participant participant = participants.get(i);
                participantsStringBuilder.append(participant.getParticipantMailAdress());
                participantsStringBuilder.append("@lamzone.fr");
                if (i < participantsSize - 1) {
                    participantsStringBuilder.append(", ");
                }

            }
            meetingViewStateItems.add(
                new MeetingViewStateItem(
                    meeting.getId(),
                    meetingInfos,
                    participantsStringBuilder.toString()
                )
            );
        }

        mediatorLiveData.setValue(meetingViewStateItems);
    }

    public LiveData<List<MeetingViewStateItem>> getMeetingViewStateItemsLiveData() {
        return mediatorLiveData;
    }

    public void onDeleteMeetingClicked(long meetingId) {
        meetingRepository.deleteMeeting(meetingId);
    }

}
