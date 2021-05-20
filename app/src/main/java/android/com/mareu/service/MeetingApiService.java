package android.com.mareu.service;

import android.com.mareu.model.Meeting;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface MeetingApiService {


    List<Meeting> getMeetings();


    void deleteMeeting(Meeting meeting);


    void addMeeting(Meeting meeting);

    List<Meeting> filterByDate(Date filterDate);

    List<Meeting> filterByRoom(String room);

    boolean isMeetingRoomAvailable(Date date, Time startTime, Time endTime, String room);
}
