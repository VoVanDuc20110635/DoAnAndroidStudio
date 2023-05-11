package com.example.footapp.Helper;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.widget.Toast;

import com.example.footapp.Domain.FoodDomain;
import com.example.footapp.Interface.ChangeNumberItemListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.function.DoubleToLongFunction;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i =0; i< listFood.size(); i++){
            if (listFood.get(i).getId() == item.getId()){
                existAlready = true;
                n = i;
                break;
            }
        }
        if (existAlready){
            listFood.get(n).setNumberIncart(item.getNumberIncart());
        } else{
            listFood.add(item);
        }
        tinyDB.putListObject("CartList", listFood);
        Toast.makeText(context, "Add To Your Cart", Toast.LENGTH_SHORT).show();
    }
    public ArrayList<FoodDomain> getListCart(){
//        TinyDB tinyDB = new TinyDB(getContext());
//        ArrayList<FoodDomain> list = tinyDB.getListObject("CartList");
        return tinyDB.getListObject("CartList");
    }

    public void plusNumberFood (ArrayList<FoodDomain> listFood, int position, ChangeNumberItemListener changeNumberItemListener){
        listFood.get(position).setNumberIncart(listFood.get(position).getNumberIncart() + 1);
        tinyDB.putListObject("CartList", listFood);
        changeNumberItemListener.changed();
    }

    public void minusNumberFood(ArrayList<FoodDomain> listfood, int position, ChangeNumberItemListener changeNumberItemListener){
        if (listfood.get(position).getNumberIncart() == 1){
            listfood.remove(position);
        }else {
            listfood.get(position).setNumberIncart(listfood.get(position).getNumberIncart() - 1);
        }
        tinyDB.putListObject("CartList", listfood);
        changeNumberItemListener.changed();
    }

//    public Double getTotalFee(){
//        ArrayList<FoodDomain> listfood = getListCart();
//        double fee = 0;
//        for (int  i =0; i< listfood.size(); i++){
//            fee = fee + (listfood.get(i).getFee() * listfood.get(i).getNumberIncart());
//        }
//        return fee;
//    }
}
