package android.com.mareu.ui.add;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.com.mareu.R;
import android.com.mareu.di.Di;
import android.com.mareu.model.Meeting;
import android.com.mareu.service.MeetingApiService;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity {

    TextView date;
    TextView timeStart;
    TextView timeEnd;
    EditText mMeetingSubjectET;
    DatePickerDialog mDatePickerDialog;
    Spinner mSpinner;
    private ArrayList<Meeting> meeting;
    private ArrayList<String> mParticipants;
    private MeetingApiService mApiService = Di.getMeetingApiService();




    // meeting Variable for creation
    String meetingRoom;
    String meetingName;
    String meetingDate;
    String meetingBeginningTime;
    String meetingEndingTime;

    // List meetingEmails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        mMeetingSubjectET = (EditText) findViewById(R.id.meetingSubjectET);
        meetingName = mMeetingSubjectET.toString();


        // initiate the date picker and a button
        date = (TextView) findViewById(R.id.dateTV);
        // perform click event on TextView
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                mDatePickerDialog = new DatePickerDialog(AddMeetingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(getString(R.string.meeting_date, dayOfMonth, monthOfYear+1, year));
                            }
                        }, mYear, mMonth, mDay);
                meetingDate = toString();
                mDatePickerDialog.show();
            }
        });

        timeStart = (TextView) findViewById(R.id.beginning_meeting_timeTV);
        timeStart.setOnClickListener(v -> {
            Calendar mCurrentTime = Calendar.getInstance();
            int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mCurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(AddMeetingActivity.this, (timePicker, selectedHour, selectedMinute) -> timeStart.setText(getString(R.string.meeting_start_time,selectedHour, selectedMinute)), hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle(getString(R.string.select_time));
            mTimePicker.show();

        });
        timeEnd = (TextView) findViewById(R.id.ending_meeting_timeTV);
        timeEnd.setOnClickListener(v -> {
            Calendar mCurrentTime = Calendar.getInstance();
            int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mCurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(AddMeetingActivity.this, (timePicker, selectedHour, selectedMinute) -> timeEnd.setText(getString(R.string.meeting_end_time, selectedHour, selectedMinute)), hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle(getString(R.string.select_time));
            mTimePicker.show();

        });
        mSpinner = findViewById(R.id.roomSpinner);
        List<String> roomList = Arrays.asList(getResources().getStringArray(R.array.meeting_room_list));

        meetingRoom = mSpinner.toString();


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, roomList);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        Button mButtonAddMeeting;
        mButtonAddMeeting = findViewById(R.id.validateMeetingBtn);

        mButtonAddMeeting.setOnClickListener(v -> {
            Meeting meeting = new Meeting(meetingRoom, meetingName, meetingDate, meetingBeginningTime, meetingEndingTime);
            mApiService.addMeeting(meeting);
            finish();
        });
    }
}