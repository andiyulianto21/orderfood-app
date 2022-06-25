package com.example.foodorderonline.adapter;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderonline.R;
import com.example.foodorderonline.datamodel.ProductModel;
import com.google.android.material.card.MaterialCardView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecommendedRVAdapter extends RecyclerView.Adapter<RecommendedRVAdapter.ViewHolder> {

    List<ProductModel> productModelList = new ArrayList<>();

    public RecommendedRVAdapter(List<ProductModel> productModelList) {
        this.productModelList = productModelList;
    }

    public void setProductModelList(List<ProductModel> productModelList) {
        this.productModelList = productModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recommended, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text_judul.setText(productModelList.get(position).getNama_product());
        holder.text_deskripsi.setText(productModelList.get(position).getDeskripsi());

        String hargaNow = convertHarga(Long.valueOf(productModelList.get(position).getHarga()));
        holder.text_harga.setText(hargaNow);


        holder.card_recommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), productModelList.get(holder.getAdapterPosition()).getNama_product(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String convertHarga(Long harga) {
        Locale indo = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(indo);
        StringBuilder hargaRp = new StringBuilder(formatRupiah.format(harga));
        return String.valueOf(hargaRp.insert(2, " "));
    }

    @Override
    public int getItemCount() {
        if(productModelList.size() > 3){
            return 3;
        }
        return productModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView text_judul, text_deskripsi, text_harga;
        private MaterialCardView card_recommended;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_judul = itemView.findViewById(R.id.text_recommended_judul);
            text_deskripsi = itemView.findViewById(R.id.text_recommended_deskripsi);
            text_harga = itemView.findViewById(R.id.text_recommended_harga);
            card_recommended = itemView.findViewById(R.id.card_recommended);
        }
    }
}
