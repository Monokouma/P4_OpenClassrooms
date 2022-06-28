package com.monokoumacorp.p4_myreu.ui.create_meeting;

import androidx.annotation.NonNull;

import java.util.Objects;

public class CreateMeetingViewStateItem {
    private final long id;
    @NonNull
    private final String roomName;

    public CreateMeetingViewStateItem(long id, @NonNull String roomName) {
        this.id = id;
        this.roomName = roomName;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getRoomName() {
        return roomName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateMeetingViewStateItem that = (CreateMeetingViewStateItem) o;
        return id == that.id && roomName.equals(that.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomName);
    }

    @Override
    public String toString() {
        return "CreateMeetingViewStateItem{" +
            "id=" + id +
            ", roomName='" + roomName + '\'' +
            '}';
    }
}
