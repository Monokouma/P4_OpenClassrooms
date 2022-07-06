package com.monokoumacorp.p4_myreu.ui.list;


import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.monokoumacorp.p4_myreu.data.Meeting;
import com.monokoumacorp.p4_myreu.data.MeetingRepository;
import com.monokoumacorp.p4_myreu.data.Participant;
import com.monokoumacorp.p4_myreu.data.ParticipantRepository;
import com.monokoumacorp.p4_myreu.ui.create_meeting.CreateMeetingViewStateItem;

import java.util.ArrayList;
import java.util.List;

public class MeetingViewModel extends ViewModel {

    @NonNull
    private final MeetingRepository meetingRepository;
    @NonNull
    private final ParticipantRepository participantRepository;

    private final MutableLiveData<List<CreateMeetingViewStateItem>> anotherLiveData = new MediatorLiveData<>();

    private final MediatorLiveData<List<MeetingViewStateItem>> mediatorLiveData = new MediatorLiveData<>();

    public MeetingViewModel(@NonNull MeetingRepository meetingRepository, @NonNull ParticipantRepository participantRepository) {
        this.meetingRepository = meetingRepository;
        this.participantRepository = participantRepository;

        final LiveData<List<Meeting>> meetingsLiveData = meetingRepository.getMeetingsLiveData();
        final LiveData<List<Participant>> participantsLiveData = participantRepository.getParticipantsLiveData();

        mediatorLiveData.addSource(meetingsLiveData, meetings -> {
            combine(meetings, participantsLiveData.getValue());
        });
        mediatorLiveData.addSource(participantsLiveData, anotherValue -> {
            combine(meetingsLiveData.getValue(), anotherValue);
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void combine(@Nullable List<Meeting> meetings, @Nullable List<Participant> participants) {
        if (meetings == null) {
            return;
        }

        List<MeetingViewStateItem> meetingViewStateItems = new ArrayList<>();
//Est ce qu'il faut combiner la liste de participants et celle des meetings pour avoir le participant name ?
        for (Meeting meeting : meetings) {
            for (Participant participant: participants) {
                meetingViewStateItems.add(
                    new MeetingViewStateItem(
                        meeting.getId(),
                        meeting.getName(),
                        participant.getParticipantMailAdress()
                    )
                );
            }

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
