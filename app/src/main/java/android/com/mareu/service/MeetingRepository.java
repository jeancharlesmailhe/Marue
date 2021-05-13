package android.com.mareu.service;

import android.com.mareu.model.Meeting;
import android.text.format.DateUtils;
import android.view.MenuInflater;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeetingRepository implements MeetingApiService {
    private List<Meeting> meetings = new ArrayList<>();


    public MeetingRepository() {

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
     *
     * @param meeting
     */
    @Override
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public List<Meeting> filterByDate(Date filterDate) {
        List<Meeting> dateFiltered = new ArrayList<>();
        for (Meeting meeting : meetings) {
            if (meeting.getMeetingDate().compareTo(filterDate) == 0) {
                dateFiltered.add(meeting);
            }
        }
        return dateFiltered;
    }

    @Override
    public List<Meeting> filterByRoom(String room) {
        List<Meeting> roomFiltered = new ArrayList<>();
        for (Meeting meeting : meetings) {
            if (meeting.getMeetingRoom().equals(room)) {
                roomFiltered.add(meeting);
            }
        }
        return roomFiltered;
    }

    @Override
    public boolean isMeetingRoomAvailable(Date date, Time startTime, Time endTime, String room) {
        for (Meeting meeting : meetings) {
            if (meeting.getMeetingRoom().equals((room))) {
                if (meeting.getMeetingDate().compareTo(date) == 0) {
                    long A = (((meeting.getMeetingBeginning().getHours())*60)+(meeting.getMeetingBeginning().getMinutes()));
                    long B = (((meeting.getMeetingEnding().getHours())*60)+(meeting.getMeetingEnding().getMinutes()));
                    long C = (((startTime.getHours())*60)+(startTime.getMinutes()));
                    long D = (((endTime.getHours())*60)+(endTime.getMinutes()));
                    if ((C<=A && D>A) || (C<B && D>=B) || (C<=A && D>=B) || (C>=A && D<=B)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
