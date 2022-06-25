package com.example.foodorderonline.datamodel;

import com.google.gson.annotations.SerializedName;

public class ProductModel {
    @SerializedName("id")
    private String id_product;

    @SerializedName("name")
    private String nama_product;

    @SerializedName("description")
    private String deskripsi;

    @SerializedName("price")
    private String harga;

    @SerializedName("id_category")
    private String id_category;

    public String getId_product() {
        return id_product;
    }

    public String getNama_product() {
        return nama_product;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getHarga() {
        return harga;
    }

    public String getId_category() {
        return id_category;
    }
}
