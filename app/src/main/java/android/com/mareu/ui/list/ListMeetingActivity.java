package android.com.mareu.ui.list;

import android.com.mareu.R;
import android.com.mareu.di.Di;
import android.com.mareu.model.Meeting;
import android.com.mareu.service.MeetingApiService;
import android.com.mareu.service.MeetingRepository;
import android.com.mareu.ui.add.AddMeetingActivity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListMeetingActivity extends AppCompatActivity {
    private MeetingApiService repository = Di.getMeetingApiService();
    private MeetingAdapter meetingAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Meeting> l = repository.getMeetings();
        meetingAdapter.update(l);
    }

    void initView() {
        FloatingActionButton mAddMeetingBtn = findViewById(R.id.meeting_add_button);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.meeting_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        meetingAdapter = new MeetingAdapter(repository.getMeetings());
        recyclerView.setAdapter(meetingAdapter);
        mAddMeetingBtn.setOnClickListener(v -> {
            Intent addMeetingIntent = new Intent(ListMeetingActivity.this, AddMeetingActivity.class);
            startActivity(addMeetingIntent);
        });
   }
}