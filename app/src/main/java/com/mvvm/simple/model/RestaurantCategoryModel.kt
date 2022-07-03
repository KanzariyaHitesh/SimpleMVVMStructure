package com.mvvm.simple.model

data class RestaurantCategoryModel(
    var categoryName: String = "",
    var categoryId: String = ""
) {
    override fun toString(): String {
        return categoryName
    }
}