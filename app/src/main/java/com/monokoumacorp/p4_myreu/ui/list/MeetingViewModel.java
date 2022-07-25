package com.monokoumacorp.p4_myreu.ui.list;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.monokoumacorp.p4_myreu.data.Meeting;
import com.monokoumacorp.p4_myreu.data.MeetingRepository;
import com.monokoumacorp.p4_myreu.data.Participant;

import java.util.ArrayList;
import java.util.List;

public class MeetingViewModel extends ViewModel {

    @NonNull
    private final MeetingRepository meetingRepository;

    private final LiveData<List<MeetingViewStateItem>> meetingViewStatesLiveData;

    public MeetingViewModel(@NonNull MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;

        final LiveData<List<Meeting>> meetingsLiveData = meetingRepository.getMeetingsLiveData();

        meetingViewStatesLiveData = Transformations.map(meetingsLiveData, meetings -> {
            List<MeetingViewStateItem> meetingViewStateItems = new ArrayList<>();

            for (Meeting meeting : meetings) {
                String meetingInfos = meeting.getName() + " - " + meeting.getMeetingHour() + " - " + meeting.getRoomName();

                StringBuilder participantsStringBuilder = new StringBuilder();
                List<Participant> participants = meeting.getParticipants();
                for (int i = 0, participantsSize = participants.size(); i < participantsSize; i++) {
                    Participant participant = participants.get(i);
                    participantsStringBuilder.append(participant.getParticipantMailAddress());
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

            return meetingViewStateItems;
        });
    }

    public LiveData<List<MeetingViewStateItem>> getMeetingViewStateItemsLiveData() {
        return meetingViewStatesLiveData;
    }

    public void onDeleteMeetingClicked(long meetingId) {
        meetingRepository.deleteMeeting(meetingId);
    }

}
