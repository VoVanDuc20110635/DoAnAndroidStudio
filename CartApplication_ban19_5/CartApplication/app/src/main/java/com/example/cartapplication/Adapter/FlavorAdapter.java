package com.example.cartapplication.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartapplication.Activity.ProductbyCateorFlavorActivity;
import com.example.cartapplication.R;
import com.example.cartapplication.model.Flavor;

import java.util.List;

public class FlavorAdapter extends RecyclerView.Adapter<FlavorAdapter.FlavorViewHolder> {

    private List<Flavor> flavorList;

    public FlavorAdapter(List<Flavor> flavorList) {
        this.flavorList = flavorList;
    }

    @NonNull
    @Override
    public FlavorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.taglayout, parent, false);
        return new FlavorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlavorViewHolder holder, int position) {
        Flavor flavor = flavorList.get(position);
        holder.flavorName.setText(flavor.getFlavorName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductbyCateorFlavorActivity.class);
                intent.putExtra("FlavorId", flavor.getId());
                intent.putExtra("CorF","F");

                intent.putExtra("name","Vị "+flavor.getFlavorName());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return flavorList.size();
    }

    public static class FlavorViewHolder extends RecyclerView.ViewHolder {

        TextView flavorName;

        public FlavorViewHolder(@NonNull View itemView) {
            super(itemView);
            flavorName = itemView.findViewById(R.id.nametag);
        }
    }
}
