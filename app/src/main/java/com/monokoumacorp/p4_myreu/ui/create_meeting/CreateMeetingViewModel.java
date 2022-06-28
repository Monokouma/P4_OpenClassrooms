package com.monokoumacorp.p4_myreu.ui.create_meeting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.monokoumacorp.p4_myreu.data.MeetingRepository;
import com.monokoumacorp.p4_myreu.data.Room;
import com.monokoumacorp.p4_myreu.data.RoomRepository;
import com.monokoumacorp.p4_myreu.utils.SingleLiveEvent;

import java.util.ArrayList;
import java.util.List;

public class CreateMeetingViewModel extends ViewModel {

    @NonNull
    private final MeetingRepository meetingRepository;

    @NonNull
    private final RoomRepository roomRepository;

    private final MutableLiveData<Boolean> isSaveButtonEnabledMutableLiveData = new MutableLiveData<>(false);

    private final SingleLiveEvent<Void> closeActivitySingleLiveEvent = new SingleLiveEvent<>();


    public CreateMeetingViewModel(@NonNull MeetingRepository meetingRepository, RoomRepository roomRepository) {
        this.meetingRepository = meetingRepository;
        this.roomRepository = roomRepository;
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
        @NonNull String roomName,
        @Nullable String participant
    ) {
        meetingRepository.addMeeting(name, participant);
        closeActivitySingleLiveEvent.call();
    }

    public LiveData<List<CreateMeetingViewStateItem>> getRoomViewStateItemsLiveData() {
        return Transformations.map(roomRepository.getRoomsLiveData(), rooms -> {
            List<CreateMeetingViewStateItem> createMeetingViewStateItems = new ArrayList<>();

            for (Room room : rooms) {
                createMeetingViewStateItems.add(
                    new CreateMeetingViewStateItem(
                        room.getId(),
                        room.getRoomName()
                    )
                );
            }
            return createMeetingViewStateItems;
        });
    }
}
