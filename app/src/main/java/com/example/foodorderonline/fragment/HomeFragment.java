package com.example.foodorderonline.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodorderonline.AllItemActivity;
import com.example.foodorderonline.R;
import com.example.foodorderonline.adapter.RecommendedRVAdapter;
import com.example.foodorderonline.api.ApiClient;
import com.example.foodorderonline.api.EndpointApi;
import com.example.foodorderonline.datamodel.ProductModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private RecyclerView rv_recommended;
    private Button btn_all_item;
    RecommendedRVAdapter rvAdapter;
    List<ProductModel> productList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume() {
        super.onResume();
        getAllProduct();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rv_recommended = view.findViewById(R.id.rv_recommended);
        btn_all_item = view.findViewById(R.id.btn_all_item);


        rvAdapter = new RecommendedRVAdapter(productList = new ArrayList<>());

        rv_recommended.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_recommended.setAdapter(rvAdapter);

        getAllProduct();

        btn_all_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AllItemActivity.class));
            }
        });

        return view;
    }

    private void getAllProduct() {

        EndpointApi api = ApiClient.getRetrofit().create(EndpointApi.class);
        Call<List<ProductModel>> call= api.getAllProduct();
        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                rvAdapter.setProductModelList(response.body());
                rvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}