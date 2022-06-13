package com.monokoumacorp.p4_myreu.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.monokoumacorp.p4_myreu.config.BuildConfigResolver;
import com.monokoumacorp.p4_myreu.data.MeetingRepository;
import com.monokoumacorp.p4_myreu.ui.create_meeting.CreateMeetingViewModel;
import com.monokoumacorp.p4_myreu.ui.list.MeetingViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private static ViewModelFactory factory;

    public static ViewModelFactory getInstance() {
        if (factory == null) {
            //Lock to 1 thread only ? Protecting data ?
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory(
                        new MeetingRepository(
                            new BuildConfigResolver()
                        )
                    );
                }
            }
        }
        return factory;
    }

    @NonNull
    private final MeetingRepository meetingRepository;

    private ViewModelFactory(@NonNull MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MeetingViewModel.class)) {
            return (T) new MeetingViewModel(
                meetingRepository
            );
        } else if (modelClass.isAssignableFrom(CreateMeetingViewModel.class)) {
            return (T) new CreateMeetingViewModel(
                meetingRepository
            );
        }
        throw new IllegalArgumentException("Unknow ViewModel");
    }
}
