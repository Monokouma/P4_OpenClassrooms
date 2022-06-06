package com.monokoumacorp.p4_myreu.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.monokoumacorp.p4_myreu.config.BuildConfigResolver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MeetingRepository {

    private final MutableLiveData<List<Meeting>> meetingsLiveData = new MutableLiveData<>(new ArrayList<>());

    private long maxId = 0;

    public MeetingRepository(BuildConfigResolver buildConfigResolver) {
        if (buildConfigResolver.isDebug()) {
            Log.i("Monokouma", "Do a flipo");
        }
    }

    public void addMeeting(
                           @NonNull String name,
                           @NonNull String localisation,
                           @NonNull String hour,
                           @NonNull String participant) {

        List<Meeting> meetings = meetingsLiveData.getValue();

        if (meetings == null) return;

        meetings.add(
            new Meeting(
                maxId++,
                name,
                localisation,
                hour,
                participant
            )
        );
        meetingsLiveData.setValue(meetings);
    }

    public LiveData<List<Meeting>> getMeetingsLiveData() {
        return meetingsLiveData;
    }

    public void deleteMeeting(long meetingId) {
        List<Meeting> meetings = meetingsLiveData.getValue();

        if (meetings == null) return;

        for (Iterator<Meeting> iterator = meetings.iterator(); iterator.hasNext(); ) {
            Meeting meeting = iterator.next();

            if (meeting.getId() == meetingId) {
                iterator.remove();
                break;
            }
        }

        meetingsLiveData.setValue(meetings);
    }
}
