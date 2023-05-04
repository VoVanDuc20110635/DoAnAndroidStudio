package com.example.footapp.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.footapp.Domain.DetailFood;
import com.example.footapp.Domain.FoodDomain;
import com.example.footapp.R;
import com.example.footapp.activity.ShowDetailActivity;

import java.util.List;

public class PoplurarAdaptor extends RecyclerView.Adapter<PoplurarAdaptor.MyViewHolder>{

//    ArrayList<FoodDomain> popularFood;
//
//    public PoplurarAdaptor(ArrayList<FoodDomain> categoryFoods) {
//        this.popularFood = categoryFoods;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);
//        return new ViewHolder(inflate);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull PoplurarAdaptor.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.title.setText(popularFood.get(position).getTitle());
//        holder.fee.setText(String.valueOf(popularFood.get(position).getFee()));
//
//
//
//        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(popularFood.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
//
//        Glide.with(holder.itemView.getContext())
//                .load(drawableResourceId)
//                .into(holder.pic);
//
//        holder.addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
//                intent.putExtra("object", popularFood.get(position));
//                holder.itemView.getContext().startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return popularFood.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        TextView title, fee;
//        ImageView pic;
//        TextView addBtn;
//
//        public ViewHolder (@NonNull View itemView){
//            super(itemView);
//            title = itemView.findViewById(R.id.title);
//            fee = itemView.findViewById(R.id.fee);
//            pic = itemView.findViewById(R.id.pic);
//            addBtn = itemView.findViewById(R.id.addBtn);
//        }
//    }
    List<FoodDomain> array;
    Context context;

    DetailFood detailFood;

    public PoplurarAdaptor(List<FoodDomain> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView images;
        public TextView tenSp;
        public TextView addCartBtn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images=itemView.findViewById(R.id.image_product);
            tenSp=itemView.findViewById(R.id.tvNameProduct);
            addCartBtn = itemView.findViewById(R.id.addCartBtn);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Bạn đã chọn product"+tenSp.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position){

        FoodDomain product = array.get(position);
        holder.tenSp.setText(product.getStrMeal());
        Glide.with(context)
                .load(product.getStrMealThumb())
                .into(holder.images);


//        //tao 1 FoodDomain lay du lieu tu API
//        ApiService.apiService.getDetailMeal(array.get(position).getId()).enqueue(new Callback<DetailMealResponse>() {
//
//            @Override
//            public void onResponse(Call<DetailMealResponse> call, Response<DetailMealResponse> response) {
//                Toast.makeText(holder.itemView.getContext(), "Call User api success", Toast.LENGTH_SHORT).show();
//                DetailMealResponse stateMessage = response.body();
//
//                if(stateMessage != null){
//                    Log.e("id",Integer.toString(stateMessage.getDetailFood().getId()));
//                    Log.e("meal", stateMessage.getDetailFood().getMeal());
//                    Log.e("area", stateMessage.getDetailFood().getArea());
//                    Log.e("category", stateMessage.getDetailFood().getCategory());
//                    Log.e("instructions", stateMessage.getDetailFood().getInstructions());
//                    Log.e("strmealthumb", stateMessage.getDetailFood().getStrmealthumb());
//                    Log.e("price", Float.toString(stateMessage.getDetailFood().getPrice()));
//
//                    detailFood = new DetailFood(stateMessage.getDetailFood().getId(),
//                            stateMessage.getDetailFood().getMeal(),
//                            stateMessage.getDetailFood().getArea(),
//                            stateMessage.getDetailFood().getCategory(),
//                            stateMessage.getDetailFood().getInstructions(),
//                            stateMessage.getDetailFood().getStrmealthumb(),
//                            stateMessage.getDetailFood().getPrice());
//                }
//                else{
//                    Toast.makeText(holder.itemView.getContext(), "Wrong Account", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DetailMealResponse> call, Throwable t) {
//                Toast.makeText(holder.itemView.getContext(), "Call api error", Toast.LENGTH_SHORT).show();
//            }
//        });
        holder.addCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", array.get(position));
//                intent.putExtra("object", detailFood);
                //array.get(position) la FoodDomain
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount(){return array==null?0:array.size();}
}
