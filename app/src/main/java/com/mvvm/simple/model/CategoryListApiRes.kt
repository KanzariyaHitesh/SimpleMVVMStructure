package com.mvvm.simple.model


import com.google.gson.annotations.SerializedName

data class CategoryListApiRes(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("errorMessage")
    val errorMessage: Any, // null
    @SerializedName("isErrorFree")
    val isErrorFree: Boolean, // true
    @SerializedName("message")
    val message: Any, // null
    @SerializedName("statusCode")
    val statusCode: Int // 200
) {
    data class Data(
        @SerializedName("categoryId")
        val categoryId: Int, // 20107
        @SerializedName("categoryName")
        val categoryName: String, // Alcohol
        @SerializedName("companyId")
        val companyId: Int, // 1
    ){
        override fun toString(): String {
            return categoryName
        }
    }
}