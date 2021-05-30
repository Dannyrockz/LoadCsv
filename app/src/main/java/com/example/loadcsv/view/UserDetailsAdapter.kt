package com.example.loadcsv.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.loadcsv.R
import com.example.loadcsv.databinding.ItemDataBinding
import com.example.loadcsv.model.UserDetails

class UserDetailsAdapter(val dataList: ArrayList<UserDetails>) :
    RecyclerView.Adapter<UserDetailsAdapter.UserDetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemDataBinding>(inflater, R.layout.item_data, parent, false)
        return UserDetailsViewHolder(view)
    }

    fun updateUserList(newDataList: List<UserDetails>) {
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: UserDetailsViewHolder, position: Int) {
        holder.view.user = dataList[position]
    }

    class UserDetailsViewHolder(var view: ItemDataBinding) : RecyclerView.ViewHolder(view.root)

}

