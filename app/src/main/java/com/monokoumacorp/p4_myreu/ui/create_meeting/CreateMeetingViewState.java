package com.monokoumacorp.p4_myreu.ui.create_meeting;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class CreateMeetingViewState {

    private final boolean isSaveButtonEnabled;

    private final List<CreateMeetingParticipantViewStateItem> participants;

    public CreateMeetingViewState(boolean isSaveButtonEnabled, List<CreateMeetingParticipantViewStateItem> participants) {
        this.isSaveButtonEnabled = isSaveButtonEnabled;
        this.participants = participants;
    }

    public boolean isSaveButtonEnabled() {
        return isSaveButtonEnabled;
    }

    public List<CreateMeetingParticipantViewStateItem> getParticipants() {
        return participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateMeetingViewState that = (CreateMeetingViewState) o;
        return isSaveButtonEnabled == that.isSaveButtonEnabled && Objects.equals(participants, that.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isSaveButtonEnabled, participants);
    }

    @NonNull
    @Override
    public String toString() {
        return "CreateMeetingViewState{" +
                "isSaveButtonEnabled=" + isSaveButtonEnabled +
                ", participants=" + participants +
                '}';
    }
}
