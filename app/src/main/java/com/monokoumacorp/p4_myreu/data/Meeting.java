package com.monokoumacorp.p4_myreu.data;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class Meeting {

    private final long id;
    @NonNull
    private final String name;
    @NonNull
    private final String participant;

    public Meeting(
        long id,
        @NonNull String name,
        @NonNull String participant) {

        this.id = id;
        this.name = name;
        this.participant = participant;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getParticipant() {
        return participant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id == meeting.id && name.equals(meeting.name) && participant.equals(meeting.participant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, participant);
    }

    @Override
    public String toString() {
        return "Meeting{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", participant=" + participant +
            '}';
    }
}
