package com.example.cartapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cartapplication.R;
import com.example.cartapplication.model.Flavor;

import java.util.List;

public class FlavorSpinnerAdapter extends ArrayAdapter<Flavor> {
    private Context context;
    private List<Flavor> flavors;

    public FlavorSpinnerAdapter(Context context, List<Flavor> flavors) {
        super(context, R.layout.spinner_item, flavors);
        this.context = context;
        this.flavors = flavors;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_item, parent, false);
        TextView flavorName = (TextView) row.findViewById(R.id.spinner_item_text);
        flavorName.setText("Vị: " + flavors.get(position).getFlavorName());
        return row;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_item, parent, false);
        TextView flavorName = (TextView) row.findViewById(R.id.spinner_item_text);
        flavorName.setText("Flavor: " + flavors.get(position).getFlavorName());
        return row;
    }
}