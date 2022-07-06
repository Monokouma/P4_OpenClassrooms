package com.monokoumacorp.p4_myreu.data;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Participant {
    private final long id;
    @NonNull
    private final String participantMailAdress;

    public Participant(long id, @NonNull String participantMailAdress) {
        this.id = id;
        this.participantMailAdress = participantMailAdress;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getParticipantMailAdress() {
        return participantMailAdress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return id == that.id && participantMailAdress.equals(that.participantMailAdress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, participantMailAdress);
    }

    @Override
    public String toString() {
        return "Participant{" +
            "id=" + id +
            ", participantMailAdress='" + participantMailAdress + '\'' +
            '}';
    }
}
