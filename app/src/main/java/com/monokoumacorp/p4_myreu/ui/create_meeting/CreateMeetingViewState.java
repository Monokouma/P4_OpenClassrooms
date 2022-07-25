package com.monokoumacorp.p4_myreu.ui.create_meeting;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CreateMeetingViewState {

    private final boolean isSaveButtonEnabled;

    private final List<CreateMeetingParticipantViewStateItem> participants;

    private final String[] roomList;


    public CreateMeetingViewState(boolean isSaveButtonEnabled, List<CreateMeetingParticipantViewStateItem> participants, String[] roomList) {
        this.isSaveButtonEnabled = isSaveButtonEnabled;
        this.participants = participants;
        this.roomList = roomList;
    }

    public boolean isSaveButtonEnabled() {
        return isSaveButtonEnabled;
    }

    public List<CreateMeetingParticipantViewStateItem> getParticipants() {
        return participants;
    }

    public String[] getRoomList() {
        return roomList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateMeetingViewState that = (CreateMeetingViewState) o;
        return isSaveButtonEnabled == that.isSaveButtonEnabled && Objects.equals(participants, that.participants) && Arrays.equals(roomList, that.roomList);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(isSaveButtonEnabled, participants);
        result = 31 * result + Arrays.hashCode(roomList);
        return result;
    }

    @Override
    public String toString() {
        return "CreateMeetingViewState{" +
            "isSaveButtonEnabled=" + isSaveButtonEnabled +
            ", participants=" + participants +
            ", roomList=" + Arrays.toString(roomList) +
            '}';
    }
}
