package com.mvvm.simple.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.simple.R
import com.mvvm.simple.databinding.ListrowFoodItemDetailBinding
import com.mvvm.simple.model.ProductListApiRes

class ProductListAdapter(private var arrayList: MutableList<ProductListApiRes.Data>) :
    RecyclerView.Adapter<ProductListAdapter.EventDataViewHolder>() {

    lateinit var context: Context
    lateinit var select: OnSelectProductItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventDataViewHolder {
        var inflator = LayoutInflater.from(parent.context)
        context = parent.context
        var binding = DataBindingUtil.inflate<ListrowFoodItemDetailBinding>(
            inflator,
            R.layout.listrow_food_item_detail,
            parent,
            false
        )
        binding.onSelectProductItem = select
        return EventDataViewHolder(binding)
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: EventDataViewHolder, position: Int) {
        holder.onBindView(arrayList[position])
    }

    fun updateList(productList: ArrayList<ProductListApiRes.Data>) {
        arrayList.clear()
        arrayList.addAll(productList)
        notifyDataSetChanged()
    }

    class EventDataViewHolder(private var binding: ListrowFoodItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBindView(productListApiRes: ProductListApiRes.Data) {
            binding.productListApiRes = productListApiRes
            binding.position = adapterPosition
        }
    }

    interface OnSelectProductItem {
        fun onSelectProductItem(position: Int)
    }

}