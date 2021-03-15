package android.com.mareu.ui.list;

import android.com.mareu.R;
import android.com.mareu.model.Meeting;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {

    private List<Meeting> mMeetings = null;

    public MeetingAdapter(List<Meeting> meetings) {
        mMeetings = meetings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.meeting_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meeting meeting = this.mMeetings.get(position);
        holder.bind(meeting);
    }

    @Override
    public int getItemCount() {
        if (this.mMeetings == null) {
            return 0;
        }
        return this.mMeetings.size();
    }

    void update(List<Meeting> meetings) {
        this.mMeetings = meetings;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView roomIMG;
        ImageView deleteIMG;
        TextView titleTV;
        TextView emailTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleTV);
            emailTV = itemView.findViewById(R.id.emailsTV);
            roomIMG = itemView.findViewById(R.id.imageView);
            deleteIMG = itemView.findViewById(R.id.deleteBT);
        }

        void bind(Meeting meeting) {
            titleTV.setText(meeting.getMeetingTime()+meeting.getMeetingName()+meeting.getMeetingDate());
            titleTV.setText(meeting.getMeetingTime()+meeting.getMeetingName()+meeting.getMeetingDate());
            emailTV.setText((CharSequence) meeting.getEmails());


        }
    }

}
