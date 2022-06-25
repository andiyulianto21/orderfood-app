package com.example.foodorderonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorderonline.databinding.ActivityProductItemBinding;

public class ProductItemActivity extends AppCompatActivity {

    ActivityProductItemBinding binding;
    private String nama_produk, harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductItemBinding.inflate(LayoutInflater.from(this));
        getSupportActionBar().setTitle("Detail Produk");
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        nama_produk = intent.getStringExtra("nama_produk");
        harga = intent.getStringExtra("harga");

        Toast.makeText(this, nama_produk + ": " + harga , Toast.LENGTH_SHORT).show();

        binding.textNamaProdukTerpilih.setText(nama_produk);
        binding.textHargaTerpilih.setText(harga);
    }
}