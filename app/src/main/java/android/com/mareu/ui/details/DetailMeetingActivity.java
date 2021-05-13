package android.com.mareu.ui.details;

import androidx.appcompat.app.AppCompatActivity;

import android.com.mareu.R;
import android.com.mareu.model.Meeting;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.lang.String.format;

public class DetailMeetingActivity extends AppCompatActivity {
    TextView title, date, dateDetails, beginningTime, beginningTimeDetails, endingTime, endingTimeDetails, room, roomDetails, participants, participantsDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_meeting);
        Meeting meeting = getIntent().getParcelableExtra("MY_DATA_MEETING");

        // connect views
        title = findViewById(R.id.title_details_tv);
        date = findViewById(R.id.date_tv);
        dateDetails = findViewById(R.id.date_details_tv);
        beginningTime = findViewById(R.id.beginning_time_tv);
        beginningTimeDetails = findViewById(R.id.beginning_time_details_tv);
        endingTime = findViewById(R.id.ending_time_tv);
        endingTimeDetails = findViewById(R.id.ending_time_details_tv);
        room = findViewById(R.id.room_tv);
        roomDetails = findViewById(R.id.room_details_tv);
        participants = findViewById(R.id.participants_tv);
        participantsDetails = findViewById(R.id.participants_details_tv);

        // connect data
        title.setText(meeting.getMeetingName());
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        String date = df.format(meeting.getMeetingDate());
        dateDetails.setText(date);
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
        String bT = tf.format(meeting.getMeetingBeginning());
        beginningTimeDetails.setText(bT);
        String eT = tf.format(meeting.getMeetingEnding());
        endingTimeDetails.setText(eT);
        roomDetails.setText(meeting.getMeetingRoom());
        participantsDetails.setText(meeting.getMeetingEmails());
    }
}