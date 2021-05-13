package android.com.mareu;

import android.com.mareu.di.Di;
import android.com.mareu.model.Meeting;
import android.com.mareu.service.MeetingApiService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(JUnit4.class)
public class MaReuUnitTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = Di.getNewInstanceApiService();
    }

    @Test
    public void addThenRemoveMeeting() {
        assertEquals(0, service.getMeetings().size());
        Meeting meeting = new Meeting("Room A", "meeting Subject", new Date(2022,12,20), new Time(10,20,0),new Time(10,50,0),"test1@gmail.com");
        service.addMeeting(meeting);
        assertEquals(1, service.getMeetings().size());
        service.deleteMeeting(meeting);
        assertEquals(0, service.getMeetings().size());
    }

    @Test
    public void filterByRoom() {
        assertEquals(0, service.getMeetings().size());
        Meeting meeting = new Meeting("Room A", "meeting Subject", new Date(2021,12,20), new Time(10,20,0),new Time(10,50,0),"test1@gmail.com");
        service.addMeeting(meeting);
        Meeting meeting2 = new Meeting("Room B", "meeting Subject", new Date(2021,12,21), new Time(10,20,0),new Time(10,50,0),"test1@gmail.com");
        service.addMeeting(meeting2);
        assertEquals(2, service.getMeetings().size());
        List<Meeting> roomsMeetings = service.filterByRoom("Room A");
        assertEquals(1, roomsMeetings.size());
    }

    @Test
    public void filterByDate(){
        assertEquals(0, service.getMeetings().size());
        Meeting meeting = new Meeting("Room A", "meeting Subject", new Date(2021,12,20), new Time(10,20,0),new Time(10,50,0),"test1@gmail.com");
        service.addMeeting(meeting);
        Meeting meeting2 = new Meeting("Room B", "meeting Subject", new Date(2021,12,21), new Time(10,20,0),new Time(10,50,0),"test1@gmail.com");
        service.addMeeting(meeting2);
        assertEquals(2, service.getMeetings().size());
        List<Meeting> dateMeetings = service.filterByDate(new Date(2021, 12, 20));
        assertEquals(1, dateMeetings.size());
    }
}