package android.com.mareu.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Meeting implements Parcelable {

    private String meetingRoom;
    private String meetingName;
    private Date meetingDate;
    private Time meetingBeginning;
    private Time meetingEnding;
    private String meetingEmails;

    public Meeting(String meetingRoom, String meetingName, Date meetingDate, Time meetingBeginning, Time meetingEnding, String meetingEmails) {
        this.meetingRoom = meetingRoom;
        this.meetingName = meetingName;
        this.meetingDate = meetingDate;
        this.meetingBeginning = meetingBeginning;
        this.meetingEnding = meetingEnding;
        this.meetingEmails = meetingEmails;
    }

    protected Meeting(Parcel in) {
        meetingRoom = in.readString();
        meetingName = in.readString();
        meetingDate = new Date(in.readLong());
        meetingBeginning = new Time(in.readLong());
        meetingEnding = new Time(in.readLong());
        meetingEmails = in.readString();
    }

    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };

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
    public String getMeetingEmails() {
        return meetingEmails;
    }
    public void setMeetingEmails(String meetingEmails) {
        this.meetingEmails = meetingEmails;
    }
    public Date getMeetingBeginning() {
        return meetingBeginning;
    }
    public void setMeetingBeginning(Time meetingBeginning) {
        this.meetingBeginning = meetingBeginning;
    }
    public Date getMeetingDate() {
        return meetingDate;
    }
    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }
    public Date getMeetingEnding() {
        return meetingEnding;
    }
    public void setMeetingEnding(Time meetingEnding) {
        this.meetingEnding = meetingEnding;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(meetingRoom);
        dest.writeString(meetingName);
        dest.writeLong(meetingDate.getTime());
        dest.writeLong(meetingBeginning.getTime());
        dest.writeLong(meetingEnding.getTime());
        dest.writeString(meetingEmails);
    }
}