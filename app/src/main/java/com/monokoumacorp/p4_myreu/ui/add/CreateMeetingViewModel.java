package com.monokoumacorp.p4_myreu.ui.add;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.monokoumacorp.p4_myreu.data.MeetingRepository;
import com.monokoumacorp.p4_myreu.utils.SingleLiveEvent;

public class CreateMeetingViewModel extends ViewModel {

    @NonNull
    private final MeetingRepository meetingRepository;

    private final MutableLiveData<Boolean> isSaveButtonEnabledMutableLiveData = new MutableLiveData<>(false);

    private final SingleLiveEvent<Void> closeActivitySingleLiveEvent = new SingleLiveEvent<>();

    public CreateMeetingViewModel(@NonNull MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    // Returns a LiveData, not a MutableLiveData. This is an extra security (ask about "immutability" your mentor)
    //Todo: Qu'est ce que donc que l'immutabilité Nino ?

    public LiveData<Boolean> getIsSaveButtonEnabledLiveData() {
        return isSaveButtonEnabledMutableLiveData;
    }

    public SingleLiveEvent<Void> getCloseActivitySingleLiveEvent() {
        return closeActivitySingleLiveEvent;
    }

    public void onNameChanged(String name) {
        isSaveButtonEnabledMutableLiveData.setValue(!name.isEmpty());
    }

    public void onAddButtonClicked(
        @NonNull String name,
        @Nullable String localisation,
        @Nullable String hour,
        @Nullable String participant
    ) {
        // Add neighbour to the repository...
        meetingRepository.addMeeting(name, localisation, hour, participant);
        // ... and close the Activity !
        closeActivitySingleLiveEvent.call();
    }

}
