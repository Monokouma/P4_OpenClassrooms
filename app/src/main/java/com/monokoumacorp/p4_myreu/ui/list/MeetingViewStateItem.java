package com.monokoumacorp.p4_myreu.ui.list;

import androidx.annotation.NonNull;

import java.util.Objects;

public class MeetingViewStateItem {
    private final long id;
    @NonNull
    private final String name;
    private final String participants;



    public MeetingViewStateItem(long id, @NonNull String name, String participants) {
        this.id = id;
        this.name = name;
        this.participants = participants;

    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public String getParticipants() {
        return participants;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingViewStateItem that = (MeetingViewStateItem) o;
        return id == that.id && name.equals(that.name) && Objects.equals(participants, that.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, participants);
    }

    @Override
    public String toString() {
        return "MeetingViewStateItem{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", participants='" + participants + '\'' +

            '}';
    }
}
