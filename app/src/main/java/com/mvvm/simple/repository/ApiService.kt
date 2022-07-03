package com.mvvm.simple.repository

import com.google.gson.GsonBuilder
import com.mvvm.simple.model.CategoryListApiRes
import com.mvvm.simple.model.ProductListApiRes
import com.mvvm.simple.model.SubCategoryListApiRes
import com.mvvm.simple.util.Constants
import com.mvvm.simple.util.SharedPreference
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

public interface ApiService {

    @GET("Category/list")
    fun callCategoryListAPI(
        @Query("companyId") companyId: Int
    ): Observable<CategoryListApiRes>

    @GET("SubCategory/list")
    fun callSubCategoryListAPI(
        @Query("companyId") companyId: Int,
        @Query("categoryId") categoryId: Int
    ): Observable<SubCategoryListApiRes>

    @GET("Product/list")
    fun callProductListAPI(
        @Query("companyId") companyId: Int,
        @Query("categoryId") categoryId: Int,
        @Query("subCategoryId") subCategoryId: Int
    ): Observable<ProductListApiRes>

    companion object {
        lateinit var retrofit: Retrofit
        private val timeout = 30

        fun createRetrofit(appPreference: SharedPreference): Retrofit {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(createOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit
        }

        private fun createOkHttpClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
            builder.readTimeout(timeout.toLong(), TimeUnit.SECONDS)
            builder.writeTimeout(timeout.toLong(), TimeUnit.SECONDS)
            builder.connectTimeout(timeout.toLong(), TimeUnit.SECONDS)

            builder.addInterceptor { chain ->
                val originalRequest = chain.request()
                val originalUrl = originalRequest.url
                val url = originalUrl.newBuilder()
                    .build()
                val requestBuilder = originalRequest.newBuilder()
                    .url(url)
                chain.proceed(requestBuilder.build())
            }
            return builder.build()
        }
    }

}

