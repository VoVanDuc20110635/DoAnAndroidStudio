package com.example.cartapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cartapplication.R;
import com.example.cartapplication.model.Category;

import java.util.List;

public class CategoryspinnerAdapter extends ArrayAdapter<Category> {
    private Context context;
    private List<Category> categories;

    public CategoryspinnerAdapter(Context context, List<Category> categories) {
        super(context, R.layout.spinner_item, categories);
        this.context = context;
        this.categories = categories;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_item, parent, false);
        TextView categoryName = (TextView) row.findViewById(R.id.spinner_item_text);
        categoryName.setText(categories.get(position).getCategoryName());
        return row;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_item, parent, false);
        TextView categoryName = (TextView) row.findViewById(R.id.spinner_item_text);
        categoryName.setText(categories.get(position).getCategoryName());
        return row;
    }
}