package com.example.footapp.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.footapp.Domain.CategoryDomain;
import com.example.footapp.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.MyViewHolder>{

//    ArrayList<CategoryDomain> categoryDomains;
//
//    public CategoryAdaptor(ArrayList<CategoryDomain> categoryDomains) {
//        this.categoryDomains = categoryDomains;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
//        return new ViewHolder(inflate);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CategoryAdaptor.ViewHolder holder, int position) {
//        holder.categoryName.setText(categoryDomains.get(position).getTitle());
//        String picUrl = "";
//        switch (position){
//            case 0:{
//                picUrl="cat_1";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
//                break;
//            }
//            case 1:{
//                picUrl="cat_2";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background2));
//                break;
//            }
//            case 2:{
//                picUrl="cat_3";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background3));
//                break;
//            }
//            case 3:{
//                picUrl="cat_4";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background4));
//                break;
//            }
//            case 4:{
//                picUrl="cat_5";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background5));
//                break;
//            }
//        }
//        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
//
//        Glide.with(holder.itemView.getContext())
//                .load(drawableResourceId)
//                .into(holder.categoryPic);
//    }
//
//    @Override
//    public int getItemCount() {
//        return categoryDomains.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        TextView categoryName;
//        ImageView categoryPic;
//        ConstraintLayout mainLayout;
//
//        public ViewHolder (@NonNull View itemView){
//            super(itemView);
//            categoryName=itemView.findViewById(R.id.categoryName);
//            categoryPic=itemView.findViewById(R.id.categoryPic);
//            mainLayout=itemView.findViewById(R.id.mainLayout);
//        }
//    }
    List<CategoryDomain> array;
    Context context;

    public CategoryAdaptor(List<CategoryDomain> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView images;
        public TextView tenSp;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images=itemView.findViewById(R.id.image_product);
            tenSp=itemView.findViewById(R.id.tvNameProduct);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Bạn đã chọn category"+tenSp.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position){
        CategoryDomain category =array.get(position);
        holder.tenSp.setText(category.getName());
        Glide.with(context)
                .load(category.getImages())
                .into(holder.images);
    }
    @Override
    public int getItemCount(){return array==null?0:array.size();}
}
