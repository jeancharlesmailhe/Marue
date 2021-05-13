package android.com.mareu.ui.list;

import android.com.mareu.R;
import android.com.mareu.di.Di;
import android.com.mareu.model.Meeting;
import android.com.mareu.service.MeetingApiService;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {

    private final MeetingApiService mApiService;
    private List<Meeting> mMeetings;
    private OnMeetingClickListener onMeetingClickListener;

    public MeetingAdapter(List<Meeting> meetings, OnMeetingClickListener listener) {
        mApiService = Di.getMeetingApiService();
        mMeetings = meetings;
        this.onMeetingClickListener = listener;
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
        String timeOfMeeting = DateFormat.format("HH:mm", meeting.getMeetingBeginning()).toString();
        holder.titleTV.setText(meeting.getMeetingName() + " - " + timeOfMeeting + " - " + meeting.getMeetingRoom());
        holder.emailTV.setText(meeting.getMeetingEmails());
        String dateOfMeeting = DateFormat.format("dd/MM", meeting.getMeetingDate()).toString();
        holder.dateTV.setText(dateOfMeeting);

        switch (meeting.getMeetingRoom()) {
            case "Room A":
                holder.roomIMG.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.red));
                break;
            case "Room B":
                holder.roomIMG.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.brown_fade));
                break;
            case "Room C":
                holder.roomIMG.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.green_fade));
                break;
            case "Room D":
                holder.roomIMG.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.blue_submarine));
                break;
            case "Room E":
                holder.roomIMG.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
                break;
            case "Room F":
                holder.roomIMG.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.canard));
                break;
            case "Room G":
                holder.roomIMG.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.green_bright));
                break;
            case "Room H":
                holder.roomIMG.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.orange));
                break;
            case "Room I":
                holder.roomIMG.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.deep_blue));
                break;
            case "Room J":
                holder.roomIMG.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.brown_deep));
                break;
        }

        holder.deleteIMG.setOnClickListener(v -> {
            mApiService.deleteMeeting(meeting);
            update(mApiService.getMeetings());
        });
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

    public interface OnMeetingClickListener {
        void onMeetingClicked(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView roomIMG;
        ImageView deleteIMG;
        TextView titleTV;
        TextView emailTV;
        TextView dateTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTV = itemView.findViewById(R.id.dateTV);
            titleTV = itemView.findViewById(R.id.titleTV);
            titleTV.setSelected(true);
            titleTV.setHorizontallyScrolling(true);
            emailTV = itemView.findViewById(R.id.emailsTV);
            roomIMG = itemView.findViewById(R.id.imageView);
            deleteIMG = itemView.findViewById(R.id.deleteBT);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onMeetingClickListener.onMeetingClicked(getAdapterPosition());
        }
    }
}

