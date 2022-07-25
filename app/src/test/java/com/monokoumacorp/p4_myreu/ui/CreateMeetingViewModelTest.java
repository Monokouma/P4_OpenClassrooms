package com.monokoumacorp.p4_myreu.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.monokoumacorp.p4_myreu.R;
import com.monokoumacorp.p4_myreu.data.MeetingRepository;
import com.monokoumacorp.p4_myreu.data.Participant;
import com.monokoumacorp.p4_myreu.ui.create_meeting.CreateMeetingViewModel;
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
public class CreateMeetingViewModelTest {

    private static final String NAME = "NAME";
    private static final LocalTime MEETING_HOUR = LocalTime.of(12, 50);
    private static final String ROOM_NAME = "ROOM_NAME";
    private static final String PARTICIPANT_MAIL_ADDRESS = "PARTICIPANT_MAIL_ADDRESS";
    private static final List<Participant> PARTICIPANTS = getDefaultParticipants();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private Application application;

    @Mock
    private MeetingRepository repository;

    private CreateMeetingViewModel viewModel;

    @Before
    public void setUp() {
        given(repository.addMeeting(eq(NAME), eq(PARTICIPANTS), eq(MEETING_HOUR), eq(ROOM_NAME))).willReturn(true);
        given(application.getString(R.string.create_meeting_error_message)).willReturn("create_meeting_error_message");

        viewModel = new CreateMeetingViewModel(application, repository);
    }

    @Test
    public void onAddButtonClicked_nominal_case() throws InterruptedException {
        // GIVEN
        viewModel.onAddParticipantButtonClicked(PARTICIPANTS.get(0).getParticipantMailAddress());
        viewModel.onAddParticipantButtonClicked(PARTICIPANTS.get(1).getParticipantMailAddress());
        viewModel.onAddParticipantButtonClicked(PARTICIPANTS.get(2).getParticipantMailAddress());

        // WHEN
        viewModel.onAddButtonClicked(NAME, MEETING_HOUR, ROOM_NAME);
        String toastMessageResult = LiveDataTestUtils.getValueForTesting(viewModel.getToastMessageSingleLiveEvent());
        Void aVoid = LiveDataTestUtils.getOrAwaitValue(viewModel.getCloseActivitySingleLiveEvent());

        // THEN
        assertNull(toastMessageResult);
        assertNull(aVoid);
    }

    @Test
    public void onAddButtonClicked_error() {
        // GIVEN
        given(repository.addMeeting(eq(NAME), eq(PARTICIPANTS), eq(MEETING_HOUR), eq(ROOM_NAME))).willReturn(false);

        // WHEN
        viewModel.onAddButtonClicked(NAME, MEETING_HOUR, ROOM_NAME);
        String result = LiveDataTestUtils.getValueForTesting(viewModel.getToastMessageSingleLiveEvent());

        // THEN
        assertEquals("create_meeting_error_message", result);
    }

    private static List<Participant> getDefaultParticipants() {
        List<Participant> participantList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            participantList.add(
                new Participant(
                    i,
                    PARTICIPANT_MAIL_ADDRESS + i
                )
            );
        }

        return participantList;
    }
}
