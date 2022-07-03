package com.mvvm.simple.model


import com.google.gson.annotations.SerializedName

data class SubCategoryListApiRes(
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
        val categoryId: Int, // 20120
        @SerializedName("categoryName")
        val categoryName: String, // Auto sync test category
        @SerializedName("subCategoryId")
        val subCategoryId: Int, // 10033
        @SerializedName("subCategoryName")
        val subCategoryName: String, // Auto sync test sub category adf
        var isSelected:Boolean=false
    )
}