package com.example.foodorderonline.api;

import com.example.foodorderonline.datamodel.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndpointApi {

    @GET("show_product.php")
    Call<List<ProductModel>> getAllProduct();

}
