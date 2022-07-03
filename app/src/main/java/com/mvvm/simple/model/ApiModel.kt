package com.mvvm.simple.model


import com.google.gson.annotations.SerializedName

data class ApiModel(
    @SerializedName("CompanyID")
    val companyID: Int, // 1
    @SerializedName("ImageSize")
    val imageSize: String, // small
    @SerializedName("MenuID")
    val menuID: Int, // 0
    @SerializedName("SearchMerchant")
    val searchMerchant: String,
    @SerializedName("SubCategoryID")
    val subCategoryID: Int // 0
)