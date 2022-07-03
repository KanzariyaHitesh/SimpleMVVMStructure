package com.mvvm.simple.model


import com.google.gson.annotations.SerializedName

data class ProductListApiRes(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("errorMessage")
    val errorMessage: Any, // null
    @SerializedName("isErrorFree")
    val isErrorFree: Boolean, // true
    @SerializedName("message")
    val message: Any, // null
    @SerializedName("statusCode")
    val statusCode: Int // 0
) {
    data class Data(
        @SerializedName("categoryId")
        val categoryId: Int, // 20105
        @SerializedName("categoryName")
        val categoryName: String, // Cafe
        @SerializedName("companyId")
        val companyId: Int, // 1
        @SerializedName("description")
        val description: String, // with VAT 20
        @SerializedName("price")
        val price: String, // 2
        @SerializedName("productId")
        val productId: Int, // 20222
         @SerializedName("productImage")
        val productImage: String, // 20222
        @SerializedName("productName")
        val productName: String // Flat White Caffee
    )
}