package com.monokoumacorp.p4_myreu.ui.create_meeting;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.monokoumacorp.p4_myreu.data.MeetingRepository;
import com.monokoumacorp.p4_myreu.data.Participant;
import com.monokoumacorp.p4_myreu.data.RoomRepository;
import com.monokoumacorp.p4_myreu.utils.SingleLiveEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateMeetingViewModel extends ViewModel {

    @NonNull
    private final MeetingRepository meetingRepository;

    @NonNull
    private final RoomRepository roomRepository;

    private final MutableLiveData<Boolean> isSaveButtonEnabledMutableLiveData = new MutableLiveData<>(false);

    private final SingleLiveEvent<Void> closeActivitySingleLiveEvent = new SingleLiveEvent<>();

    private final List<Participant> participants = new ArrayList<>();
    private final MutableLiveData<List<Participant>> participantMutableLiveData = new MutableLiveData<>(participants);
    private long maxId = 0;

    private final MediatorLiveData<CreateMeetingViewState> createMeetingViewStateMediatorLiveData = new MediatorLiveData<>();

    public CreateMeetingViewModel(
            @NonNull MeetingRepository meetingRepository,
            @NonNull RoomRepository roomRepository
    ) {
        this.meetingRepository = meetingRepository;
        this.roomRepository = roomRepository;

        createMeetingViewStateMediatorLiveData.addSource(isSaveButtonEnabledMutableLiveData, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSaveButtonEnabled) {
                combine(isSaveButtonEnabled, participantMutableLiveData.getValue());
            }
        });

        createMeetingViewStateMediatorLiveData.addSource(participantMutableLiveData, new Observer<List<Participant>>() {
            @Override
            public void onChanged(List<Participant> participants) {
                combine(isSaveButtonEnabledMutableLiveData.getValue(), participants);
            }
        });
    }

    private void combine(@Nullable Boolean isSaveButtonEnabled, @Nullable List<Participant> participants) {
        if (isSaveButtonEnabled == null || participants == null) {
            return;
        }

        List<CreateMeetingParticipantViewStateItem> participantViewStateItems = new ArrayList<>();

        for (Participant participant : participants) {
            participantViewStateItems.add(
                    new CreateMeetingParticipantViewStateItem(
                            participant.getId(),
                            participant.getParticipantMailAdress()
                    )
            );
        }

        createMeetingViewStateMediatorLiveData.setValue(
                new CreateMeetingViewState(
                        isSaveButtonEnabled,
                        participantViewStateItems
                )
        );
    }

    public LiveData<CreateMeetingViewState> getCreateMeetingViewStateLiveData() {
        return createMeetingViewStateMediatorLiveData;
    }

    public SingleLiveEvent<Void> getCloseActivitySingleLiveEvent() {
        return closeActivitySingleLiveEvent;
    }

    public void onAddParticipantButtonClicked(String participantName) {
        addParticipant(participantName);
    }

    public void onNameChanged(String name) {
        isSaveButtonEnabledMutableLiveData.setValue(!name.isEmpty());
    }

    public void onAddButtonClicked(
        @NonNull String name
    ) {
        meetingRepository.addMeeting(name, participants);
        Log.i("Monokouma", meetingRepository.getMeetingsLiveData().getValue().toString());
        closeActivitySingleLiveEvent.call();
    }

    private void addParticipant(@NonNull String participantEmail) {
        List<Participant> participants = participantMutableLiveData.getValue();
        if (participants == null) return;

        participants.add(
                new Participant(
                        maxId++,
                        participantEmail
                )
        );
        participantMutableLiveData.setValue(participants);
    }
}
