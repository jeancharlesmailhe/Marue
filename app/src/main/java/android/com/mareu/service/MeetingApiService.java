package android.com.mareu.service;

import android.com.mareu.model.Meeting;

import java.util.List;

public interface MeetingApiService {

    /**
     * Get all my Meetings
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    /**
     * Deletes a neighbour
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Create a neighbour
     * @param meeting
     */
    void addMeeting(Meeting meeting);

}
