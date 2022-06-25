package com.example.foodorderonline.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderonline.AllItemActivity;
import com.example.foodorderonline.ProductItemActivity;
import com.example.foodorderonline.R;
import com.example.foodorderonline.datamodel.ProductModel;
import com.google.android.material.card.MaterialCardView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AllItemRVAdapter extends RecyclerView.Adapter<AllItemRVAdapter.ViewHolder> {

    Activity activity;
    List<ProductModel> productModelList = new ArrayList<>();

    public AllItemRVAdapter(List<ProductModel> productModelList, Activity activity) {
        this.productModelList = productModelList;
        this.activity = activity;
    }

    public void setProductModelList(List<ProductModel> productModelList) {
        this.productModelList = productModelList;
    }

    @NonNull
    @Override
    public AllItemRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_all_item, parent, false);
        return new AllItemRVAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllItemRVAdapter.ViewHolder holder, int position) {
        holder.text_judul.setText(productModelList.get(position).getNama_product());
        holder.text_deskripsi.setText(productModelList.get(position).getDeskripsi());

        String hargaNow = convertHarga(Long.valueOf(productModelList.get(position).getHarga()));
        holder.text_harga.setText(hargaNow);

    }

    private String convertHarga(Long harga) {
        Locale indo = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(indo);
        StringBuilder hargaRp = new StringBuilder(formatRupiah.format(harga));
        return String.valueOf(hargaRp.insert(2, " "));
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView text_judul, text_deskripsi, text_harga;
        private MaterialCardView card_all_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_judul = itemView.findViewById(R.id.text_allitem_judul);
            text_deskripsi = itemView.findViewById(R.id.text_allitem_deskripsi);
            text_harga = itemView.findViewById(R.id.text_allitem_harga);
            card_all_item = itemView.findViewById(R.id.card_all_item);

            card_all_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String harga = productModelList.get(getAdapterPosition()).getHarga();
                    String nama_produk = productModelList.get(getAdapterPosition()).getNama_product();

                    Intent intent = new Intent(view.getContext(), ProductItemActivity.class);
                    intent.putExtra("harga" , harga);
                    intent.putExtra("nama_produk", nama_produk);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

}
