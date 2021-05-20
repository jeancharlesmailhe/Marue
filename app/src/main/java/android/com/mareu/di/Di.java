package android.com.mareu.di;

import android.com.mareu.service.MeetingApiService;
import android.com.mareu.service.MeetingRepository;

public class Di {

    private static final MeetingApiService service = new MeetingRepository();

    public static MeetingApiService getMeetingApiService() {
        return service;
    }

    public static MeetingApiService getNewInstanceApiService() {
        return new MeetingRepository();
    }
}

