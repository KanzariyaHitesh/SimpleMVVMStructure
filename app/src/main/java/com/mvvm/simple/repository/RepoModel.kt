package com.mvvm.simple.repository

import android.content.Context
import com.mvvm.simple.repository.ApiService.Companion.createRetrofit
import com.mvvm.simple.util.SharedPreference
import org.koin.core.KoinComponent

class RepoModel(context: Context) : KoinComponent {

    val appPreference = SharedPreference(context)
    var api = createRetrofit(appPreference).create(ApiService::class.java)
}