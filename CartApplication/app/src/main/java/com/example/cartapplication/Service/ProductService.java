package com.example.cartapplication.Service;

import com.example.cartapplication.model.Product;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductService {
    @GET("api/product/getProductList")
    Call<List<Product>> getProductList();
    @GET("api/product/find/{id}")
    Call<Product>getProductbyid(@Path("id") int id);

    @GET("api/product/getbycate/{id}")
    Call<List<Product>> getProductCategory(@Path("id") int id);
    @GET("api/product/getbyflavor/{id}")
    Call<List<Product>> getProductFlavor(@Path("id") int id);
    @POST("api/product/addnewproduct")
    Call<ResponseBody> addProduct (@Query("nameproduct") String nameproduct,
                                   @Query("soluong") int soluong,
                                   @Query("price") int price,
                                   @Query("cateId") int cateId,
                                   @Query("flavorId") int flavorId,
                                   @Query("des") String des,
                                   @Query("image") String image);

}