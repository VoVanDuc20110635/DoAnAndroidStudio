package com.example.cartapplication.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartapplication.R;
import com.example.cartapplication.model.Feedback;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder> {

    private List<Feedback> feedbackList;

    public FeedbackAdapter(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feedback, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Feedback feedback = feedbackList.get(position);
        if (feedback.getUser() != null)
            holder.feedbackUserName.setText(feedback.getUser().getName());
        holder.feedbackContent.setText(feedback.getContent());
        //Log.e("Feedback",feedback.getContent());
        holder.feedbackDate.setText(String.valueOf(feedback.getDate()));
    }

    @Override
    public int getItemCount() {
        if (feedbackList == null)
            return 0;
        return feedbackList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView feedbackUserName;
        TextView feedbackContent;
        TextView feedbackDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            feedbackUserName = itemView.findViewById(R.id.feedback_user_name);
            feedbackContent = itemView.findViewById(R.id.feedback_content);
            feedbackDate = itemView.findViewById(R.id.feedback_date);
        }
    }
}
