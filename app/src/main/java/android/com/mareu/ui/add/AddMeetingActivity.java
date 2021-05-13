package android.com.mareu.ui.add;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.com.mareu.R;
import android.com.mareu.di.Di;
import android.com.mareu.model.Meeting;
import android.com.mareu.service.MeetingApiService;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static java.lang.String.format;

public class AddMeetingActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mMeetingSubjectET, mParticipantET;
    TextView date, timeStart, timeEnd, listOfParticipantTV;
    ImageView mAddEmailBtn;
    Button mButtonAddMeeting;
    Date meetingDate;
    Time meetingBeginningTime, meetingEndingTime;
    Spinner mSpinner;
    String meetingRoom, meetingName, mParticipants;
    int mStartYear, mStartMonth, mStartDay, mStartHour, mStartMinute, mEndingHour, mEndingMinute;
    private final MeetingApiService mApiService = Di.getMeetingApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        mMeetingSubjectET = findViewById(R.id.meetingSubjectET);
        mParticipantET = findViewById(R.id.addEmailET);
        listOfParticipantTV = findViewById(R.id.listViewEmail);
        mAddEmailBtn = findViewById(R.id.addEmailImage);
        mSpinner = findViewById(R.id.roomSpinner);
        date = (TextView) findViewById(R.id.dateTV);
        timeStart = (TextView) findViewById(R.id.beginning_meeting_timeTV);
        timeEnd = (TextView) findViewById(R.id.ending_meeting_timeTV);
        mButtonAddMeeting = findViewById(R.id.validateMeetingBtn);
        // Listener
        mAddEmailBtn.setOnClickListener(this);
        mMeetingSubjectET.setOnClickListener(this);
        date.setOnClickListener(this);
        timeStart.setOnClickListener(this);
        timeEnd.setOnClickListener(this);
        mButtonAddMeeting.setOnClickListener(this);
        mSpinner = findViewById(R.id.roomSpinner);
        List<String> roomList = Arrays.asList(getResources().getStringArray(R.array.meeting_room_list));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roomList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        if (v == mAddEmailBtn) {
            String emailOfParticipant = mParticipantET.getText().toString().trim();
            if (emailOfParticipant.isEmpty()) {
                return;
            }
            String emails = listOfParticipantTV.getText().toString();
            emails = emails + emailOfParticipant + ",\n" ;
            listOfParticipantTV.setText(emails);
            mParticipants = emails;
            mParticipantET.setText(" ");
            enableCreateButtonIfReady();
        }

        if (v == date) {
            final Calendar c = Calendar.getInstance();
            mStartYear = c.get(Calendar.YEAR);
            mStartMonth = c.get(Calendar.MONTH);
            mStartDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        date.setText(format(Locale.getDefault(), "%02d/%02d/%02d", dayOfMonth, monthOfYear + 1, year));
                        meetingDate = new Date(year, monthOfYear, dayOfMonth);
                    }, mStartYear, mStartMonth, mStartDay);
            enableCreateButtonIfReady();
            datePickerDialog.show();
        }

        if (v == timeStart) {
            Calendar c = Calendar.getInstance();
            mStartHour = c.get(Calendar.HOUR_OF_DAY);
            mStartMinute = c.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(AddMeetingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    timeStart.setText(format(Locale.getDefault(),"%02d:%02d", selectedHour , selectedMinute));
                    meetingBeginningTime = new Time(selectedHour, selectedMinute, 0);
                }
            }, mStartHour, mStartMinute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
            enableCreateButtonIfReady();
        }

        if (v == timeEnd) {
            Calendar c = Calendar.getInstance();
            mEndingHour = c.get(Calendar.HOUR_OF_DAY);
            mEndingMinute = c.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(AddMeetingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    timeEnd.setText(format(Locale.getDefault(),"%02d:%02d", selectedHour , selectedMinute));
                    meetingEndingTime = new Time(selectedHour, selectedMinute, 0);
                }
            }, mEndingHour, mEndingMinute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
            enableCreateButtonIfReady();
        }

        if (v == mButtonAddMeeting) {
            mMeetingSubjectET = (EditText) findViewById(R.id.meetingSubjectET);
            meetingName = mMeetingSubjectET.getText().toString();
            meetingRoom = mSpinner.getSelectedItem().toString();
            if (checkMeetingRoomAvailable()) {
                Meeting meeting = new Meeting(meetingRoom, meetingName, meetingDate, meetingBeginningTime, meetingEndingTime, mParticipants);
                mApiService.addMeeting(meeting);
                finish();
            } else {
                Toast.makeText(this, R.string.rooms_not_available, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkMeetingRoomAvailable() {
        return mApiService.isMeetingRoomAvailable(meetingDate, meetingBeginningTime, meetingEndingTime, meetingRoom);
    }

    public void enableCreateButtonIfReady() {
        boolean isReady = (mMeetingSubjectET.getText().length() > 0 && date.getText().length() > 0 && timeStart.getText().length() > 0 && timeEnd.getText().length() > 0);
        if (isReady){
            mButtonAddMeeting.setEnabled(true);
        }
    }
}


