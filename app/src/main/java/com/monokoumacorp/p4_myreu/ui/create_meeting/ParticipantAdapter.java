package com.monokoumacorp.p4_myreu.ui.create_meeting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.monokoumacorp.p4_myreu.R;
import com.monokoumacorp.p4_myreu.ui.list.MeetingAdapter;
import com.monokoumacorp.p4_myreu.ui.list.MeetingViewStateItem;
import com.monokoumacorp.p4_myreu.ui.list.OnMeetingClickedListenner;


public class ParticipantAdapter extends ListAdapter<CreateMeetingViewStateItem, ParticipantAdapter.ViewHolder> {

    public ParticipantAdapter() {
        super(new ParticipantItemCallback());


    }

    private static class ParticipantItemCallback extends DiffUtil.ItemCallback<CreateMeetingViewStateItem> {
        @Override
        public boolean areItemsTheSame(@NonNull CreateMeetingViewStateItem oldItem, @NonNull CreateMeetingViewStateItem newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull CreateMeetingViewStateItem oldItem, @NonNull CreateMeetingViewStateItem newItem) {
            return oldItem.equals(newItem);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.participants_itemsview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        private final TextView participantEmail;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            participantEmail = itemView.findViewById(R.id.participant_itemView_email);

        }

        public void bind(CreateMeetingViewStateItem item) {
            participantEmail.setText(item.getParticipantEmail() + "@lamzone.fr");
        }
    }

}
