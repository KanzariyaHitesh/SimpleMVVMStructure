package com.mvvm.simple

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mvvm.simple.databinding.ActivityMainBinding
import com.mvvm.simple.model.CategoryListApiRes
import com.mvvm.simple.repository.RepoModel
import com.mvvm.simple.ui.viewmodel.ExploreFactory
import com.mvvm.simple.ui.viewmodel.MainActivityVM
import com.mvvm.simple.util.ProgressState
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val repo: RepoModel by inject()
    lateinit var binding: ActivityMainBinding
    lateinit var viewmodel: MainActivityVM
    lateinit var adapterCategoryList: ArrayAdapter<CategoryListApiRes.Data>
    var isInitForRestCategory = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        viewmodel =
            ViewModelProviders.of(this, ExploreFactory(repo)).get(MainActivityVM(repo)::class.java)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        binding.llSpCategory.setOnClickListener(this)
        binding.executePendingBindings()
        if (isInternetConnected())
            viewmodel.callCategoryListApi()
        observerLiveData()

    }

    private fun observerLiveData() {
        viewmodel.subCategoryListApiRes.observe(this, Observer {
            if(it.data.size>0){
                binding.rvCategoryList.visibility = View.VISIBLE
            }else{
                binding.tvNoData.visibility = View.VISIBLE
                binding.rvCategoryList.visibility = View.GONE
                binding.rvProductList.visibility = View.GONE
            }
        })

        viewmodel.selectedItemPosition.observe(this, Observer {
            for (i in viewmodel.subCategoryArrayList.indices) {
                if (i == it) {
                    viewmodel.subCategoryArrayList[it].isSelected = true
                } else {
                    viewmodel.subCategoryArrayList[i].isSelected = false
                }
            }
            viewmodel.subCategoryListAdapter.updateList(viewmodel.subCategoryArrayList)
            viewmodel.callProductListAPI(
                viewmodel.subCategoryArrayList[it].categoryId,
                viewmodel.subCategoryArrayList[it].subCategoryId
            )
        })

        viewmodel.categoryListApiRes.observe(this, Observer {
            setSpinnerAdapter()
        })

        viewmodel.progressState.observe(this, Observer {
            if (it.equals(ProgressState.SHOW)) {
                showProgressDialog()
            } else {
                dismissProgressDialog()
            }
        })

        viewmodel.productListApiRes.observe(this, Observer {
            if (it.data.size>0){
                binding.tvNoData.visibility = View.GONE
                binding.rvProductList.visibility = View.VISIBLE
            }else{
                binding.tvNoData.visibility = View.VISIBLE
                binding.rvProductList.visibility = View.GONE
            }

            rvProductList.adapter = viewmodel.productListAdapter
        })

        viewmodel.selectedProductItemPosition.observe(this, Observer {

        })
    }

    private fun setSpinnerAdapter() {
        adapterCategoryList = ArrayAdapter<CategoryListApiRes.Data>(
            this@MainActivity,
            R.layout.spinner_item_view,
            viewmodel.categoryArrayList!!
        )

        binding.spCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    binding.tvSpCategoryName.text =
                        "" + viewmodel.categoryArrayList!![position].categoryName
                    if (!isInitForRestCategory) {
                        viewmodel.callSubCategoryListApi(viewmodel.categoryArrayList!![position].categoryId)
                    } else {
                        binding.tvSpCategoryName.text = "Select Category"
                        isInitForRestCategory = false
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        adapterCategoryList.setDropDownViewResource(R.layout.spinner_dropdown_view)
        binding.spCategory.adapter = adapterCategoryList
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.llSpCategory -> {
                binding.spCategory.performClick()
            }
        }
    }

    private lateinit var mDialog: Dialog
    private fun showProgressDialog() {
        mDialog = Dialog(this, R.style.MaterialDialogSheet)
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDialog.setContentView(R.layout.progress_loader)
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.show()
    }

    fun dismissProgressDialog() {
        if (mDialog.isShowing) {
            mDialog.dismiss()
        }
    }

    fun isInternetConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected && networkInfo.isConnectedOrConnecting
    }

}