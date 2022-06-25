package com.example.foodorderonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.foodorderonline.adapter.AllItemRVAdapter;
import com.example.foodorderonline.api.ApiClient;
import com.example.foodorderonline.api.EndpointApi;
import com.example.foodorderonline.datamodel.ProductModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllItemActivity extends AppCompatActivity {

    private RecyclerView rv_all_item;
    private AllItemRVAdapter rvAdapter;
    private List<ProductModel> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Daftar Semua Produk");
        setContentView(R.layout.activity_all_item);

        rv_all_item = findViewById(R.id.rv_all_item);
        rvAdapter = new AllItemRVAdapter(productList = new ArrayList<>(), AllItemActivity.this);
        rv_all_item.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_all_item.setAdapter(rvAdapter);

        getAllItem();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllItem();
    }

    private void getAllItem() {

        EndpointApi api = ApiClient.getRetrofit().create(EndpointApi.class);
        Call<List<ProductModel>> call = api.getAllProduct();

        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                rvAdapter.setProductModelList(response.body());
                rv_all_item.setAdapter(rvAdapter);
                rvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                Toast.makeText(AllItemActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}