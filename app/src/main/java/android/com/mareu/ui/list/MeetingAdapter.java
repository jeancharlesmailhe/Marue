package android.com.mareu.ui.list;

import android.com.mareu.R;
import android.com.mareu.di.Di;
import android.com.mareu.model.Meeting;
import android.com.mareu.service.MeetingApiService;
import android.content.Context;
import android.graphics.Color;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {

    private List<Meeting> mMeetings = null;
    private MeetingApiService mApiService;

    public MeetingAdapter(List<Meeting> meetings) {
        mApiService = Di.getMeetingApiService();
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
        holder.titleTV.setText(meeting.getMeetingRoom() + " - " + meeting.getMeetingTime() + " - " + meeting.getMeetingName());
        holder.emailTV.setText(meeting.getMeetingEmails());

        switch (meeting.getMeetingRoom()) {
            case "Room A":
                holder.roomIMG.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.red));
                break;
            case "Room B":
                holder.roomIMG.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.brown_de_larbre));
                break;
            case "Room C":
                holder.roomIMG.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.green_de_lherbe));
                break;
            case "Room D":
                holder.roomIMG.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.yellow_des_pokemons));
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

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView roomIMG;
        ImageView deleteIMG;
        TextView titleTV;
        TextView emailTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleTV);
            titleTV.setSelected(true);
            titleTV.setHorizontallyScrolling(true);
            emailTV = itemView.findViewById(R.id.emailsTV);
            roomIMG = itemView.findViewById(R.id.imageView);
            deleteIMG = itemView.findViewById(R.id.deleteBT);

        }
    }

 }
