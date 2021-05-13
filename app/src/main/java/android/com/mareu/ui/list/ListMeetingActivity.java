package android.com.mareu.ui.list;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.com.mareu.R;
import android.com.mareu.di.Di;
import android.com.mareu.model.Meeting;
import android.com.mareu.service.MeetingApiService;
import android.com.mareu.ui.add.AddMeetingActivity;
import android.com.mareu.ui.details.DetailMeetingActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListMeetingActivity extends AppCompatActivity implements MeetingAdapter.OnMeetingClickListener {
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

    @Override
    protected void onStart() {
        super.onStart();
        List<Meeting> l = repository.getMeetings();
        meetingAdapter.update(l);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.filter_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter_by_room:
                filterByRoom();
                break;
            case R.id.action_filter_by_date:
                filterByDate();
                break;
            case R.id.action_filter_reset:
                reset();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void filterByDate() {

        DatePickerDialog datePickerDialog;
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        datePickerDialog = new DatePickerDialog(ListMeetingActivity.this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    Date filteredDate = new Date(year, monthOfYear, dayOfMonth);
                    List<Meeting> filterResult = repository.filterByDate(filteredDate);
                    meetingAdapter.update(filterResult);
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void filterByRoom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(R.string.rooms)
                .setItems(R.array.meeting_room_list, (dialog, which) -> {
                    String[] rooms = getResources().getStringArray(R.array.meeting_room_list);
                    List<Meeting> filterResult = repository.filterByRoom(rooms[which]);
                    meetingAdapter.update(filterResult);
                });
        builder.create().show();
    }

    private void reset() {
        meetingAdapter.update(repository.getMeetings());
    }

    void initView() {
        FloatingActionButton mAddMeetingBtn = findViewById(R.id.meeting_add_button);
        RecyclerView recyclerView = findViewById(R.id.meeting_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        meetingAdapter = new MeetingAdapter(repository.getMeetings(), this);
        recyclerView.setAdapter(meetingAdapter);
        mAddMeetingBtn.setOnClickListener(v -> {
            Intent addMeetingIntent = new Intent(ListMeetingActivity.this, AddMeetingActivity.class);
            startActivity(addMeetingIntent);
        });
    }

    @Override
    public void onMeetingClicked(int position) {
        Meeting meeting = repository.getMeetings().get(position);
        Intent i = new Intent(this, DetailMeetingActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("MY_DATA_MEETING", meeting);
        i.putExtras(bundle);
        startActivity(i);
    }
}