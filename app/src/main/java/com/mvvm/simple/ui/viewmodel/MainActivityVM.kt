package com.mvvm.simple.ui.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.mvvm.simple.model.*
import com.mvvm.simple.repository.RepoModel
import com.mvvm.simple.ui.adapter.ProductListAdapter
import com.mvvm.simple.ui.adapter.SubCategoryListAdapter
import com.mvvm.simple.util.ProgressState
import com.mvvm.simple.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.HttpException

class MainActivityVM(private var repo: RepoModel) : ViewModel() {

    private val TAG = "MainActivityVM"

    private var _CategoryListApiRes: MutableLiveData<CategoryListApiRes> = MutableLiveData()
    var categoryListApiRes: LiveData<CategoryListApiRes> = _CategoryListApiRes

    private var _SubCategoryListApiRes: MutableLiveData<SubCategoryListApiRes> = MutableLiveData()
    var subCategoryListApiRes: LiveData<SubCategoryListApiRes> = _SubCategoryListApiRes

    private var _selectedItemPosition: SingleLiveEvent<Int> = SingleLiveEvent()
    var selectedItemPosition: LiveData<Int> = _selectedItemPosition

    private var _ProductListApiRes: MutableLiveData<ProductListApiRes> = MutableLiveData()
    var productListApiRes: LiveData<ProductListApiRes> = _ProductListApiRes

    private var _selectedProductItemPosition: SingleLiveEvent<Int> = SingleLiveEvent()
    var selectedProductItemPosition: LiveData<Int> = _selectedProductItemPosition
    private var _progressState: SingleLiveEvent<ProgressState> = SingleLiveEvent()
    var progressState: LiveData<ProgressState> = _progressState

    private var _errorMsg: SingleLiveEvent<String> = SingleLiveEvent()
    var errorMsg: LiveData<String> = _errorMsg

    var productListAdapter = ProductListAdapter(mutableListOf())
    var productList = ArrayList<ProductListApiRes.Data>()

    var categoryArrayList = ArrayList<CategoryListApiRes.Data>()
    var subCategoryListAdapter = SubCategoryListAdapter(mutableListOf())
    var subCategoryArrayList = ArrayList<SubCategoryListApiRes.Data>()

    var dateSet = HashSet<RestaurantCategoryModel>()
    var subCategoryList: ArrayList<RestaurantCategoryModel>? = null

    init {
        setSubCategoryList()
        setRestaurantList()
    }

    private fun setSubCategoryList() {
        subCategoryListAdapter.select = object : SubCategoryListAdapter.OnSelectSubCategoryItem {
            override fun onSelectSubCategoryItem(
                position: Int
            ) {
                _selectedItemPosition.value = position
            }
        }
    }

    private fun setRestaurantList() {
        productListAdapter.select = object : ProductListAdapter.OnSelectProductItem {
            override fun onSelectProductItem(position: Int) {
                _selectedProductItemPosition.value = position
            }
        }
    }

    @SuppressLint("CheckResult")
    fun callCategoryListApi() {
        _progressState.value = ProgressState.SHOW
        repo.api.callCategoryListAPI(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    categoryArrayList.clear()
                    categoryArrayList.addAll(it.data)
                    _progressState.value = ProgressState.HIDE
                    _CategoryListApiRes.value = it
                    Log.e("d", "category==> " + it.data)
                },
                onComplete = {
                    _progressState.value = ProgressState.HIDE
                },
                onError = {
                    _progressState.value = ProgressState.HIDE
                    if (it is HttpException) {
                        val errorJsonString = it.response()!!.errorBody()?.string()
                        val testModel = Gson().fromJson(errorJsonString, ErrorResponse::class.java)
                        _errorMsg.value = testModel.error.message
                        println(it.message)
                    } else {
                        Log.d(TAG, it.message!!)
                    }
                }
            )
    }

    @SuppressLint("CheckResult")
    fun callSubCategoryListApi(categoryId: Int) {
        _progressState.value = ProgressState.SHOW
        repo.api.callSubCategoryListAPI(1, categoryId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    subCategoryArrayList.clear()
                    subCategoryArrayList.addAll(it.data)
                    subCategoryListAdapter.updateList(subCategoryArrayList)
                    _progressState.value = ProgressState.HIDE
                    _SubCategoryListApiRes.value = it
                    Log.e("d", "sub category==> " + it.data)
                },
                onComplete = {
                    _progressState.value = ProgressState.HIDE
                },
                onError = {
                    _progressState.value = ProgressState.HIDE
                    if (it is HttpException) {
                        val errorJsonString = it.response()!!.errorBody()?.string()
                        val testModel = Gson().fromJson(errorJsonString, ErrorResponse::class.java)
                        _errorMsg.value = testModel.error.message
                        println(it.message)
                    } else {
                        Log.d(TAG, it.message!!)
                    }
                }
            )
    }


    @SuppressLint("CheckResult")
    fun callProductListAPI(categoryId: Int, subCategoryID: Int) {
        _progressState.value = ProgressState.SHOW
        repo.api.callProductListAPI(1, categoryId, subCategoryID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    productList.clear()
                    productList.addAll(it.data)
                    productListAdapter.updateList(productList)
                    _progressState.value = ProgressState.HIDE
                    _ProductListApiRes.value = it
                },
                onComplete = {
                    _progressState.value = ProgressState.HIDE
                },
                onError = {
                    _progressState.value = ProgressState.HIDE
                    if (it is HttpException) {
                        val errorJsonString = it.response()!!.errorBody()?.string()
                        val testModel = Gson().fromJson(errorJsonString, ErrorResponse::class.java)
                        _errorMsg.value = testModel.error.message
                        println(it.message)
                    } else {
                        Log.d(TAG, it.message!!)
                    }
                }
            )
    }

}

class ExploreFactory(private var repo: RepoModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainActivityVM(repo) as T
}