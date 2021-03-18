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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity {

    TextView date;
    TextView timeStart;
    TextView timeEnd;
    EditText mMeetingSubjectET;
    EditText mParticipantET;
    DatePickerDialog mDatePickerDialog;
    Spinner mSpinner;

    // meeting Variable for creation
    String meetingRoom;
    String meetingName;
    String meetingDate;
    String meetingBeginningTime;
    String meetingEndingTime;
    ArrayList<Meeting> meeting;
    String mParticipants;
    ArrayAdapter<String> arrayAdapter;
    private final MeetingApiService mApiService = Di.getMeetingApiService();
    private TextView listOfParticipantTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        // email participant
        mParticipantET = findViewById(R.id.addEmailET);
        ImageView mAddEmailBtn = findViewById(R.id.addEmailImage);
        listOfParticipantTV = findViewById(R.id.listViewEmail);

        mAddEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddMeetingActivity.this, "hello", Toast.LENGTH_SHORT).show();
                String emailOfParticipant = mParticipantET.getText().toString().trim();
                if (emailOfParticipant.isEmpty()) {
                    return;
                }

                String emails = listOfParticipantTV.getText().toString();
                emails = emails + "; " + emailOfParticipant;
                listOfParticipantTV.setText(emails);
                mParticipants = emails;
                mParticipantET.setText("");
            }
        });


        // Meeting Room selection
        mSpinner = findViewById(R.id.roomSpinner);
        List<String> roomList = Arrays.asList(getResources().getStringArray(R.array.meeting_room_list));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roomList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        // Date picker Meeting
        date = (TextView) findViewById(R.id.dateTV);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddMeetingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        // Starting Time of Meeting
        timeStart = (TextView) findViewById(R.id.beginning_meeting_timeTV);
        timeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCurrentTime = Calendar.getInstance();
                int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddMeetingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timeStart.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Selection heure");
                mTimePicker.show();
            }
        });

        // Ending Time Meeting
        timeEnd = (TextView) findViewById(R.id.ending_meeting_timeTV);
        timeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCurrentTime = Calendar.getInstance();
                int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddMeetingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timeEnd.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Selection heure");
                mTimePicker.show();
            }
        });

        // Meeting Add Button
        Button mButtonAddMeeting;
        mButtonAddMeeting = findViewById(R.id.validateMeetingBtn);
        mButtonAddMeeting.setOnClickListener(view4 -> {

            // Meeting Name subject
            mMeetingSubjectET = (EditText) findViewById(R.id.meetingSubjectET);
            meetingName = mMeetingSubjectET.getText().toString();

            // Room to string
            meetingRoom = mSpinner.getSelectedItem().toString();

            // Date to String
            meetingDate = date.getText().toString();

            // Beginning time to String
            meetingBeginningTime = timeStart.getText().toString();

            // Ending time to String
            meetingEndingTime = timeEnd.getText().toString();


            Meeting meeting = new Meeting(meetingRoom, meetingName, meetingDate, meetingBeginningTime, meetingEndingTime, mParticipants);
            mApiService.addMeeting(meeting);
            finish();
        });


    }
}