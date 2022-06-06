package com.monokoumacorp.p4_myreu.data;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class Meeting {

    private final long id;
    @NonNull
    private final String name;
    @NonNull
    private final String localisation;
    @NonNull
    private final String subject;
    @NonNull
    private final String hour;
    @NonNull
    private final List<String> participant;

    public Meeting(
        long id,
        @NonNull String name,
        @NonNull String localisation,
        @NonNull String subject,
        @NonNull String hour,
        @NonNull List<String> participant) {

        this.id = id;
        this.name = name;
        this.localisation = localisation;
        this.subject = subject;
        this.hour = hour;
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
    public String getLocalisation() {
        return localisation;
    }

    @NonNull
    public String getSubject() {
        return subject;
    }

    @NonNull
    public String getHour() {
        return hour;
    }

    @NonNull
    public List<String> getParticipant() {
        return participant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id == meeting.id && name.equals(meeting.name) && localisation.equals(meeting.localisation) && subject.equals(meeting.subject) && hour.equals(meeting.hour) && participant.equals(meeting.participant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, localisation, subject, hour, participant);
    }

    @Override
    public String toString() {
        return "Meeting{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", localisation='" + localisation + '\'' +
            ", subject='" + subject + '\'' +
            ", hour='" + hour + '\'' +
            ", participant=" + participant +
            '}';
    }
}
