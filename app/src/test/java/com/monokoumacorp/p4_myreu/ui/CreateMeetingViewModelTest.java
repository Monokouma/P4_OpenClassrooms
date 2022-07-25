package com.monokoumacorp.p4_myreu.ui;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import android.app.Application;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.monokoumacorp.p4_myreu.R;
import com.monokoumacorp.p4_myreu.data.MeetingRepository;
import com.monokoumacorp.p4_myreu.ui.create_meeting.CreateMeetingViewModel;
import com.monokoumacorp.p4_myreu.ui.create_meeting.CreateMeetingViewState;
import com.monokoumacorp.p4_myreu.utils.LiveDataTestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@RunWith(MockitoJUnitRunner.class)
public class CreateMeetingViewModelTest {

    private static final String EXPECTED_TOPIC_USER_INPUT_ERROR = "EXPECTED_TOPIC_USER_INPUT_ERROR";
    private static final String EXPECTED_PARTICIPANTS_USER_INPUT_ERROR = "EXPECTED_PARTICIPANTS_USER_INPUT_ERROR";
    private static final String EXPECTED_ROOM_USER_INPUT_ERROR = "EXPECTED_ROOM_USER_INPUT_ERROR";

    private static final String EXPECTED_TIME = "13:00";

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private MeetingRepository repository;

    private Clock clock;

    private CreateMeetingViewModel viewModel;

    @Before
    public void setUp() {
        // Useful for unit testing with time
        clock = getDefaultClock(getDefaultLocalDate(), getDefaultLocalTime());

        viewModel = new CreateMeetingViewModel(new Application(), repository);
    }

    @NonNull
    private LocalTime getDefaultLocalTime() {
        return LocalTime.of(12, 50);
    }

    @NonNull
    private LocalDate getDefaultLocalDate() {
        return LocalDate.of(1991, 2, 26);
    }

    @NonNull
    private Clock getDefaultClock(LocalDate localDate, LocalTime localTime) {
        return Clock.fixed(
            LocalDateTime
                .of(
                    localDate,
                    localTime
                )
                .toInstant(ZoneOffset.UTC),
            ZoneOffset.UTC
        );
    }



}
