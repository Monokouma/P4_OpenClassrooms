package com.monokoumacorp.p4_myreu.ui.create_meeting;

import androidx.annotation.NonNull;

import java.util.Objects;

public class CreateMeetingViewStateItem {
    private final long id;
    @NonNull
    private final String participantEmail;

    public CreateMeetingViewStateItem(long id, @NonNull String participantEmail) {
        this.id = id;
        this.participantEmail = participantEmail;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getParticipantEmail() {
        return participantEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateMeetingViewStateItem that = (CreateMeetingViewStateItem) o;
        return id == that.id && participantEmail.equals(that.participantEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, participantEmail);
    }

    @Override
    public String toString() {
        return "CreateMeetingViewStateItem{" +
            "id=" + id +
            ", participantEmail='" + participantEmail + '\'' +
            '}';
    }
}
