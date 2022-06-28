package com.monokoumacorp.p4_myreu.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.monokoumacorp.p4_myreu.R;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository {
    private final MutableLiveData<List<Room>> roomLiveData = new MutableLiveData<>(new ArrayList<>());
    private long maxId = 0;

    public RoomRepository() {
        generateRooms();

        List<Room> rooms = roomLiveData.getValue();
        for (Room room : rooms) {
            Log.i("Monokouma", room.getRoomName());
        }
    }
    public void addRoom(
        @NonNull String roomName
    ) {
        List<Room> rooms = roomLiveData.getValue();
        if (rooms == null) return;
        rooms.add(
            new Room(
                maxId++,
                roomName
            )
        );
        roomLiveData.setValue(rooms);
    }
    public LiveData<List<Room>> getRoomsLiveData() {
        return roomLiveData;
    }

    private void generateRooms() {
        addRoom("Am");
        addRoom("Stram");
        addRoom("Gram");
        addRoom("Pic");
        addRoom("Et");
        addRoom("Pic");
    }
}
