package com.monokoumacorp.p4_myreu.ui.list;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class MeetingViewStateItem {
    private final long id;
    @NonNull
    private final String name;
    @NonNull
    private final String participant;

    public MeetingViewStateItem(long id, @NonNull String name, @NonNull String participant) {
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
        MeetingViewStateItem that = (MeetingViewStateItem) o;
        return id == that.id && name.equals(that.name) && participant.equals(that.participant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, participant);
    }

    @Override
    public String toString() {
        return "MeetingViewStateItem{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", participant=" + participant +
            '}';
    }
}
