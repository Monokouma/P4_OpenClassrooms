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


public class ParticipantAdapter extends ListAdapter<CreateMeetingParticipantViewStateItem, ParticipantAdapter.ViewHolder> {

    public ParticipantAdapter() {
        super(new ParticipantItemCallback());
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

        public void bind(CreateMeetingParticipantViewStateItem item) {
            participantEmail.setText(item.getParticipantEmail() + "@lamzone.fr");
        }
    }

    private static class ParticipantItemCallback extends DiffUtil.ItemCallback<CreateMeetingParticipantViewStateItem> {
        @Override
        public boolean areItemsTheSame(@NonNull CreateMeetingParticipantViewStateItem oldItem, @NonNull CreateMeetingParticipantViewStateItem newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull CreateMeetingParticipantViewStateItem oldItem, @NonNull CreateMeetingParticipantViewStateItem newItem) {
            return oldItem.equals(newItem);
        }
    }
}
