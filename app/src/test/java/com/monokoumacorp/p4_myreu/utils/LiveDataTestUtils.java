package com.monokoumacorp.p4_myreu.utils;

import androidx.lifecycle.LiveData;

public class LiveDataTestUtils {


    public static <T> T getValueForTesting(final LiveData<T> liveData) {
        liveData.observeForever(ignored -> {
        });
            return liveData.getValue();
    }
}

