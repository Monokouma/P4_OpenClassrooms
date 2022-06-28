package com.monokoumacorp.p4_myreu.data;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Room {

    private final long id;

    @NonNull
    private final String roomName;

    public Room(long id, @NonNull String roomName) {
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
        Room room = (Room) o;
        return id == room.id && roomName.equals(room.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomName);
    }

    @Override
    public String toString() {
        return "Room{" +
            "id=" + id +
            ", roomName='" + roomName + '\'' +
            '}';
    }
}
