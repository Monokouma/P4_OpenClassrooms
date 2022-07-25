package com.monokoumacorp.p4_myreu.ui;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.monokoumacorp.p4_myreu.data.Meeting;
import com.monokoumacorp.p4_myreu.data.MeetingRepository;
import com.monokoumacorp.p4_myreu.data.Participant;
import com.monokoumacorp.p4_myreu.ui.list.MeetingViewModel;
import com.monokoumacorp.p4_myreu.utils.LiveDataTestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MeetingViewModelTest {

    private static final String EXPECTED_FIRST_MAPPED_TITLE = "EXPECTED_FIRST_MAPPED_TITLE";
    private static final String EXPECTED_SECOND_MAPPED_TITLE = "EXPECTED_SECOND_MAPPED_TITLE";
    private static final String EXPECTED_THIRD_MAPPED_TITLE = "EXPECTED_THIRD_MAPPED_TITLE";
    private static final String EXPECTED_FOURTH_MAPPED_TITLE = "EXPECTED_FOURTH_MAPPED_TITLE";

    private static final String FIRST_TOPIC = "Topic A";
    private static final String SECOND_TOPIC = "Topic B";
    private static final String THIRD_TOPIC = "Topic C";
    private static final String FOURTH_TOPIC = "Topic A"; // <-- !! Same topic as first topic !!

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private Resources resources;

    @Mock
    private MeetingRepository meetingRepository;

    private MutableLiveData<List<Meeting>> meetingsMutableLiveData;
    private MeetingViewModel viewModel;

    @Before
    public void setUp() {
        // Reinitialize Livedatas every test
        meetingsMutableLiveData = new MutableLiveData<>();

        // Mock LiveDatas returned from Repositories
        given(meetingRepository.getMeetingsLiveData()).willReturn(meetingsMutableLiveData);

        // Set default values to LiveDatas
        List<Meeting> meetings = get4Meetings();
        meetingsMutableLiveData.setValue(meetings);


        viewModel = new MeetingViewModel(meetingRepository);

        verify(meetingRepository).getMeetingsLiveData();
    }

    @NonNull
    private List<Meeting> get4Meetings() {
        List<Meeting> meetings = new ArrayList<>();

        List<Participant> participants = new ArrayList<>();
        participants.add(new Participant(0, "participant1_1@outlook.com"));
        participants.add(new Participant(1, "participant1_2@outlook.com"));
        participants.add(new Participant(2, "participant1_3@outlook.com"));
        meetings.add(new Meeting(0, FIRST_TOPIC, participants, LocalTime.of(13, 0), "room1"));


        List<Participant> participants2 = new ArrayList<>();
        participants.add(new Participant(0, "participant2_1@outlook.com"));
        participants.add(new Participant(1, "participant2_2@outlook.com"));
        participants.add(new Participant(2, "participant2_3@outlook.com"));
        meetings.add(new Meeting(1, SECOND_TOPIC, participants2, LocalTime.of(18, 30), "room2"));

        List<Participant> participants3 = new ArrayList<>();
        participants.add(new Participant(0, "participant3_1@outlook.com"));
        participants.add(new Participant(1, "participant3_2@outlook.com"));
        participants.add(new Participant(2, "participant3_3@outlook.com"));
        meetings.add(new Meeting(2, THIRD_TOPIC, participants3, LocalTime.of(14, 30), "room3"));

        List<Participant> participants4 = new ArrayList<>();
        participants.add(new Participant(0, "participant4_1@outlook.com"));
        participants.add(new Participant(1, "participant4_2@outlook.com"));
        participants.add(new Participant(2, "participant4_3@outlook.com"));
        meetings.add(new Meeting(3, FOURTH_TOPIC, participants4, LocalTime.of(13, 50), "room4"));

        return meetings;
    }


}
