package android.com.mareu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Meeting {



    /** Room name */
    private String meetingRoom;

    /** Meeting name */
    private String meetingName;

    /** Date */
    private String meetingDate;

    /** Time */
    private String meetingTime;

    /** Duration */
    private String meetingEndingTime;

    /** Participants emails*/
    private List<String> meetingEmails;






    /**
     * Constructor
     */

    public Meeting(String meetingRoom, String meetingName, String meetingDate, String meetingTime, String meetingEndingTime) {

        this.meetingRoom = meetingRoom;
        this.meetingName = meetingName;
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
        this.meetingEndingTime = meetingEndingTime;
        this.meetingEmails = meetingEmails;

    }

    /**
     *Getters and Setters
     */



    public String getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(String meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public String getMeetingName() {
    return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getMeetingEndingTime() {
        return meetingEndingTime;
    }

    public void setMeetingEndingTime(String meetingEndingTime) {
        this.meetingEndingTime = meetingEndingTime;
    }

    public List<String> getEmails() {
        return meetingEmails;
    }

    public void setEmails(List<String> meetingEmails) {
        this.meetingEmails = meetingEmails;
    }


}
