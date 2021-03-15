package android.com.mareu.service;

import android.com.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MeetingRepository implements MeetingApiService{
    private List<Meeting> meetings = new ArrayList<>();
    Meeting meetingTest = new Meeting("rooma", "name", "16/05", "15h", "16h");

    public MeetingRepository() {
       this.addMeeting(meetingTest);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    /**
     * {@inheritDoc}
     * @param meeting
     */
    @Override
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }
}
