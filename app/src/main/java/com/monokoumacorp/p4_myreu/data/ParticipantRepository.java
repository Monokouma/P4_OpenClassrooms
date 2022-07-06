package com.monokoumacorp.p4_myreu.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class ParticipantRepository {
    private final MutableLiveData<List<Participant>> participantMutableLiveData = new MutableLiveData<>(new ArrayList<>());

    private long maxId = 0;

    public void addParticipant(@NonNull String participantEmail) {
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

    public LiveData<List<Participant>> getParticipantsLiveData() { return participantMutableLiveData;}


}
