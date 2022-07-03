package com.mvvm.simple.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson


class SharedPreference(mContext: Context) {

    public val sharedPreferences: SharedPreferences = mContext.getSharedPreferences("practicaldemo", Context.MODE_PRIVATE)

    private val editor = sharedPreferences.edit()

    fun <T> setList(key: String?, list: List<T>?) {
        val gson = Gson()
        val json: String = gson.toJson(list)
        set(key, json)
    }

    operator fun set(key: String?, value: String?) {
        editor.putString(key, value)
        editor.commit()
    }
}