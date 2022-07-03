package com.mvvm.simple.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.simple.R
import com.mvvm.simple.databinding.ListrowCategoryItemBinding
import com.mvvm.simple.model.SubCategoryListApiRes
import kotlinx.android.synthetic.main.listrow_category_item.view.*

class SubCategoryListAdapter(private var arrayList: MutableList<SubCategoryListApiRes.Data>) :
    RecyclerView.Adapter<SubCategoryListAdapter.EventDataViewHolder>() {

    lateinit var context: Context
    lateinit var select: OnSelectSubCategoryItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventDataViewHolder {
        var inflator = LayoutInflater.from(parent.context)
        context = parent.context
        var binding = DataBindingUtil.inflate<ListrowCategoryItemBinding>(
            inflator,
            R.layout.listrow_category_item,
            parent,
            false
        )
        binding.onSelectSubCategoryItem = select
        return EventDataViewHolder(binding)
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: EventDataViewHolder, position: Int) {
        holder.onBindView(arrayList[position])
        if (arrayList[position].isSelected!!){
            holder.itemView.cvMainView.setCardBackgroundColor(context.resources.getColor(R.color.black))
            holder.itemView.tvCategoryName.setTextColor(context.resources.getColor(R.color.colorWhite))
        }else{
            holder.itemView.cvMainView.setCardBackgroundColor(context.resources.getColor(R.color.colorWhite))
            holder.itemView.tvCategoryName.setTextColor(context.resources.getColor(R.color.black))
        }
    }

    fun updateList(subCategoryList: ArrayList<SubCategoryListApiRes.Data>) {
        arrayList.clear()
        arrayList.addAll(subCategoryList)
        notifyDataSetChanged()
    }

    class EventDataViewHolder(private var binding: ListrowCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBindView(subCategoryListApiRes: SubCategoryListApiRes.Data) {
            binding.subCategoryListApiRes = subCategoryListApiRes
            binding.position = adapterPosition
        }
    }

    interface OnSelectSubCategoryItem {
        fun onSelectSubCategoryItem( position: Int)
    }

}