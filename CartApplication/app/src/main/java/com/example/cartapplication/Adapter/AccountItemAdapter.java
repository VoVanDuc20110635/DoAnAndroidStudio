package com.example.cartapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartapplication.Activity.admin.DetailAccountActivity;
import com.example.cartapplication.R;
import com.example.cartapplication.model.User;

import java.util.List;

public class AccountItemAdapter extends RecyclerView.Adapter<AccountItemAdapter.ViewHolder> {

    private List<User> mUsers;
    private Context context1;

    public AccountItemAdapter(List<User> users, Context context) {
        mUsers = users;
        context1=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_admin_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = mUsers.get(position);
        holder.bind(user);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context1, DetailAccountActivity.class);
                intent.putExtra("User", user);
                context1.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameTextView;
        private TextView mEmailTextView;
        private TextView mRoleTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mNameTextView = itemView.findViewById(R.id.name_user);
            mEmailTextView = itemView.findViewById(R.id.email_user);
            mRoleTextView = itemView.findViewById(R.id.role);
        }

        public void bind(User user) {
            mNameTextView.setText(user.getName());
            mEmailTextView.setText(user.getEmail());
            if (user.getAccount().getRoleNumber() == 1){
                mRoleTextView.setText("Admin");
            }
            else if(user.getAccount().getRoleNumber() == 2){
                mRoleTextView.setText("Shipper");
            }
            else{
                mRoleTextView.setText("Customer");
            }

        }
    }
}
