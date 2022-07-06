package com.monokoumacorp.p4_myreu.ui.create_meeting;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.monokoumacorp.p4_myreu.data.MeetingRepository;
import com.monokoumacorp.p4_myreu.data.Participant;
import com.monokoumacorp.p4_myreu.data.ParticipantRepository;
import com.monokoumacorp.p4_myreu.data.Room;
import com.monokoumacorp.p4_myreu.data.RoomRepository;
import com.monokoumacorp.p4_myreu.utils.SingleLiveEvent;

import java.util.ArrayList;
import java.util.List;

public class CreateMeetingViewModel extends ViewModel {

    @NonNull
    private final MeetingRepository meetingRepository;

    @NonNull
    private final ParticipantRepository participantRepository;

    @NonNull
    private final RoomRepository roomRepository;

    private final MutableLiveData<Boolean> isSaveButtonEnabledMutableLiveData = new MutableLiveData<>(false);

    private final SingleLiveEvent<Void> closeActivitySingleLiveEvent = new SingleLiveEvent<>();


    public CreateMeetingViewModel(@NonNull MeetingRepository meetingRepository, @NonNull RoomRepository roomRepository, @NonNull ParticipantRepository participantRepository) {
        this.meetingRepository = meetingRepository;
        this.roomRepository = roomRepository;
        this.participantRepository = participantRepository;
    }

    public LiveData<Boolean> getIsSaveButtonEnabledLiveData() {
        return isSaveButtonEnabledMutableLiveData;
    }

    public SingleLiveEvent<Void> getCloseActivitySingleLiveEvent() {
        return closeActivitySingleLiveEvent;
    }

    public void onAddParticipantButtonClicked(String participantName) {
        participantRepository.addParticipant(participantName);
    }

    public void onNameChanged(String name) {
        isSaveButtonEnabledMutableLiveData.setValue(!name.isEmpty());
    }

    public void onAddButtonClicked(
        @NonNull String name
    ) {
        participantRepository.getParticipantsLiveData();
        meetingRepository.addMeeting(name, participantRepository.getParticipantsLiveData());
        Log.i("Monokouma", meetingRepository.getMeetingsLiveData().getValue().toString());
        closeActivitySingleLiveEvent.call();
    }

    public LiveData<List<CreateMeetingViewStateItem>> getParticipantsViewStateItems() {
        return Transformations.map(participantRepository.getParticipantsLiveData(), participants -> {
            List<CreateMeetingViewStateItem> participantViewStateItems = new ArrayList<>();

            for (Participant participant : participants) {
                participantViewStateItems.add(
                    new CreateMeetingViewStateItem(
                        participant.getId(),
                        participant.getParticipantMailAdress()
                    )
                );
            }
            return participantViewStateItems;
        });
    }





}
