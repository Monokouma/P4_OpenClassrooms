package com.monokoumacorp.p4_myreu.data;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Participant {
    private final long id;
    @NonNull
    private final String participantMailAddress;

    public Participant(long id, @NonNull String participantMailAddress) {
        this.id = id;
        this.participantMailAddress = participantMailAddress;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getParticipantMailAddress() {
        return participantMailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return id == that.id && participantMailAddress.equals(that.participantMailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, participantMailAddress);
    }

    @Override
    public String toString() {
        return "Participant{" +
            "id=" + id +
            ", participantMailAddress='" + participantMailAddress + '\'' +
            '}';
    }
}
