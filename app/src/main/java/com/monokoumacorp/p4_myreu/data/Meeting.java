package com.monokoumacorp.p4_myreu.data;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class Meeting {

    private final long id;
    @NonNull
    private final String name;
    private final List<Participant> participants;
   // @NonNull
   // private final LocalDateTime startTime;
  //  @NonNull
   // private final Period period;


    public Meeting(
        long id,
        @NonNull String name,
        List<Participant> participants
    ) {

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

    public List<Participant> getParticipants() {
        return participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id == meeting.id && name.equals(meeting.name) && Objects.equals(participants, meeting.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, participants);
    }

    @Override
    public String toString() {
        return "Meeting{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", participants=" + participants +
            '}';
    }
}
