package com.monokoumacorp.p4_myreu.data;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

import android.util.Log;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.monokoumacorp.p4_myreu.data.Meeting;
import com.monokoumacorp.p4_myreu.data.MeetingRepository;
import com.monokoumacorp.p4_myreu.data.Participant;
import com.monokoumacorp.p4_myreu.utils.LiveDataTestUtils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class MeetingRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MeetingRepository meetingRepository;

    @Before
    public void setup() {
        meetingRepository = new MeetingRepository();

    }


    @Test
    public void shouldAddMeeting() {
        String meetingTopic = getTopic(0);
        LocalTime time = getTime();
        List<Participant> meetingParticipants = getParticipants(0);
        String room = getRoom(0);

        meetingRepository.addMeeting(
            meetingTopic,
            meetingParticipants,
            time,
            room
        );
        List<Meeting> results = LiveDataTestUtils.getValueForTesting(meetingRepository.getMeetingsLiveData());
        assertEquals(1, results.size());

        Meeting result = results.get(0);
        assertEquals(
            result,
            getMeeting(
                0,
                getTime(),
                getRoom(0)
            )
        );
    }

    @Test
    public void shouldAdd2MeetingsAndIncrementId() {
        // Given
        String meetingTopic0 = getTopic(0);
        LocalTime time0 = getTime();
        List<Participant> meetingParticipants0 = getParticipants(0);
        String room0 = getRoom(0);

        String meetingTopic1 = getTopic(1);
        LocalTime time1 = getTime();
        List<Participant> meetingParticipants1 = getParticipants(1);
        String room1 = getRoom(1);

        // When
        meetingRepository.addMeeting(
            meetingTopic0,
            meetingParticipants0,
            time0,
            room0
        );
        meetingRepository.addMeeting(
            meetingTopic1,
            meetingParticipants1,
            time1,
            room1
        );
        List<Meeting> results = LiveDataTestUtils.getValueForTesting(meetingRepository.getMeetingsLiveData());

        // Then
        assertEquals(2, results.size());
        Meeting result0 = results.get(0);
        assertEquals(
            result0,
            getMeeting(
                0,
                getTime(),
                room0
            )
        );
        Meeting result1 = results.get(1);
        assertEquals(
            result1,
            getMeeting(
                1,
                time1,
                room1
            )
        );
    }

    @Test
    public void shouldRemoveMeeting() {
        // Given
        String meetingTopic0 = getTopic(0);
        LocalTime time0 = getTime();
        List<Participant> meetingParticipants0 = getParticipants(0);
        String room0 = getRoom(0);

        // When
        meetingRepository.addMeeting(
            meetingTopic0,
            meetingParticipants0,
            time0,
            room0
        );
        meetingRepository.deleteMeeting(0);
        List<Meeting> results = LiveDataTestUtils.getValueForTesting(meetingRepository.getMeetingsLiveData());

        // Then
        assertEquals(0, results.size());
    }

    @Test
    public void shouldRemoveMeetingEvenIfListIsEmpty() {
        // When
        meetingRepository.deleteMeeting(0);
        List<Meeting> results = LiveDataTestUtils.getValueForTesting(meetingRepository.getMeetingsLiveData());

        // Then
        assertEquals(0, results.size());
    }

    @Test
    public void dontRemoveUnexistingMeeting() {
        // Given
        String meetingTopic0 = getTopic(0);
        LocalTime time0 = getTime();
        List<Participant> meetingParticipants0 = getParticipants(0);
        String room0 = getRoom(0);


        // When
        meetingRepository.addMeeting(
            meetingTopic0,
            meetingParticipants0,
            time0,
            room0
        );
        meetingRepository.deleteMeeting(666);
        List<Meeting> results = LiveDataTestUtils.getValueForTesting(meetingRepository.getMeetingsLiveData());

        // Then
        assertEquals(1, results.size());
    }

    @Test
    public void shouldAddMeetingWithIncrementIdAfterDelete() {
        // Given
        String meetingTopic0 = getTopic(0);
        LocalTime time0 = getTime();
        List<Participant> meetingParticipants0 = getParticipants(0);
        String room0 = getRoom(0);

        String meetingTopic1 = getTopic(1);
        LocalTime time1 = getTime();
        List<Participant> meetingParticipants1 = getParticipants(1);
        String room1 = getRoom(1);

        // When
        meetingRepository.addMeeting(
            meetingTopic0,
            meetingParticipants0,
            time0,
            room0
        );
        meetingRepository.deleteMeeting(0);
        meetingRepository.addMeeting(
            meetingTopic1,
            meetingParticipants1,
            time1,
            room1
        );
        List<Meeting> results = LiveDataTestUtils.getValueForTesting(meetingRepository.getMeetingsLiveData());

        // Then
        assertEquals(1, results.size());
        Meeting result0 = results.get(0);
        assertEquals(
            result0,
            getMeeting(
                1,
                time1,
                room1
            )
        );

        System.out.println(results);
    }

    private String getTopic(int index) {
        return "meetingTopic" + index;
    }

    private LocalTime getTime() {
        return LocalTime.of(14, 45);
    }

    private List<Participant> getParticipants(int index) {
        List<Participant> meetingParticipants = new ArrayList<>();
        Participant participant = new Participant(index, "partcipantAdress" + index);
        meetingParticipants.add(participant);
        return meetingParticipants;
    }

    private String getRoom(int index) {
        return "room" + index;
    }

    private Meeting getMeeting(int index, LocalTime time, String room) {
        return new Meeting(
            index,
            getTopic(index),
            getParticipants(index),
            time,
            room
        );
    }
}