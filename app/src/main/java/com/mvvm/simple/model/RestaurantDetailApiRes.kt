package com.mvvm.simple.model


import com.google.gson.annotations.SerializedName

data class RestaurantDetailApiRes(
    @SerializedName("banner_enable")
    val bannerEnable: Int, // 1
    @SerializedName("bannerImages")
    val bannerImages: List<BannerImage>,
    @SerializedName("CartCount")
    val cartCount: Int, // 0
    @SerializedName("ClosedAccount")
    val closedAccount: Int, // 0
    @SerializedName("loginauth")
    val loginauth: Int, // 0
    @SerializedName("Message")
    val message: String, // Success
    @SerializedName("PremiumMerchant")
    val premiumMerchant: List<PremiumMerchant>,
    @SerializedName("RecordCount")
    val recordCount: String, // 16
    @SerializedName("Result")
    val result: ArrayList<Result>,
    @SerializedName("Status")
    val status: Int, // 1
    @SerializedName("SubCategoryList")
    val subCategoryList: List<Any>
) {
    data class BannerImage(
        @SerializedName("detail_url")
        val detailUrl: String, // https://suntecproject-stg.s3.amazonaws.com/BI/banners/1002-359-salon260x260.PNG
        @SerializedName("image_height")
        val imageHeight: String, // 150
        @SerializedName("image_width")
        val imageWidth: String, // 148
        @SerializedName("imageurl")
        val imageurl: String, // https://suntecproject-stg.s3.amazonaws.com/BI/banners/1002-359-salon260x260.PNG
        @SerializedName("sub_title")
        val subTitle: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: String // 1
    )

    data class PremiumMerchant(
        @SerializedName("AboutStore")
        val aboutStore: String, // Established in 2007, Akemi Uchi is a one-stop home concept store that creates ultimate modern look for your home by introducing fashionable presence into interior space. They are inspired for ultimate comfort, great service and only for the best ideas.Enjoy 10% off regular-priced items at Akemi Uchi as a Suntec Rewards member! Check out Members Only for more details.
        @SerializedName("DetailImage")
        val detailImage: String, // https://suntecproject-stg.s3.amazonaws.com/BI/merchant/detail/small/7070-akemi_uchi_.jpg
        @SerializedName("Email")
        val email: String, // trycalender@gmail.com
        @SerializedName("IsSponsored")
        val isSponsored: String, // 1
        @SerializedName("Lat")
        val lat: String,
        @SerializedName("Lng")
        val lng: String,
        @SerializedName("MerchantCode")
        val merchantCode: String, // 20150617473C
        @SerializedName("MerchantID")
        val merchantID: String, // 3
        @SerializedName("MerchantLogo")
        val merchantLogo: String, // https://suntecproject-stg.s3.amazonaws.com/BI/merchant/thumb/390-akemi.jpg
        @SerializedName("MerchantOpeningHours")
        val merchantOpeningHours: String, // 01:45 PM
        @SerializedName("MerchantTypeID")
        val merchantTypeID: String, // 1
        @SerializedName("ParticipationLevel")
        val participationLevel: String, // 2
        @SerializedName("PhoneNo")
        val phoneNo: String, // 6238 8462
        @SerializedName("PromotionalImage")
        val promotionalImage: String, // https://suntecproject-stg.s3.amazonaws.com/BI/merchant/sponsore/small/346-akemiuchi2.jpg
        @SerializedName("RefundDays")
        val refundDays: String,
        @SerializedName("RefundPolicy")
        val refundPolicy: String,
        @SerializedName("StoreName")
        val storeName: String, // Akemi Uchi
        @SerializedName("SubCategoryID")
        val subCategoryID: String, // 15
        @SerializedName("SubCategoryName")
        val subCategoryName: String, // Home & Furnishing
        @SerializedName("TowerNumber")
        val towerNumber: String, // North Wing
        @SerializedName("UnitNumber")
        val unitNumber: String, // #02-439/440
        @SerializedName("Website")
        val website: String,
        @SerializedName("Zone")
        val zone: String // 5
    )

    data class Result(
        @SerializedName("AboutStore")
        val aboutStore: String,
        @SerializedName("DetailImage")
        val detailImage: String, // https://suntecproject-stg.s3.amazonaws.com/BI/merchant/detail/small/7152-ToTT_e-Voucher.jpg
        @SerializedName("Email")
        val email: String, // tanjiaxuan+1011@apmasia.com.sg
        @SerializedName("IsSponsored")
        val isSponsored: String, // 0
        @SerializedName("Lat")
        val lat: String,
        @SerializedName("Lng")
        val lng: String,
        @SerializedName("MerchantCode")
        val merchantCode: String, // F7DBEDC7
        @SerializedName("MerchantID")
        val merchantID: String, // 664
        @SerializedName("MerchantLogo")
        val merchantLogo: String,
        @SerializedName("MerchantOpeningHours")
        val merchantOpeningHours: String,
        @SerializedName("MerchantTypeID")
        val merchantTypeID: String, // 2
        @SerializedName("ParticipationLevel")
        val participationLevel: String, // 2
        @SerializedName("PhoneNo")
        val phoneNo: String,
        @SerializedName("PromotionalImage")
        val promotionalImage: String, // https://suntecproject-stg.s3.amazonaws.com/BI/merchant/sponsore/small/9745-ToTT_e-Voucher.jpg
        @SerializedName("RefundDays")
        val refundDays: String,
        @SerializedName("RefundPolicy")
        val refundPolicy: String,
        @SerializedName("StoreName")
        val storeName: String, // 108 Matcha Saro
        @SerializedName("SubCategoryID")
        val subCategoryID: String, // 8
        @SerializedName("SubCategoryName")
        val subCategoryName: String, // Fast Food & Takeaway 
        @SerializedName("TowerNumber")
        val towerNumber: String, // West Wing
        @SerializedName("UnitNumber")
        val unitNumber: String, // #B1-K5
        @SerializedName("Website")
        val website: String,
        @SerializedName("Zone")
        val zone: String // 8
    )
}