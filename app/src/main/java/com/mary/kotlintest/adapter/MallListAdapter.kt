package com.mary.kotlintest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mary.kotlintest.R
import com.mary.kotlintest.viewHolder.MallListViewHolder

class MallListAdapter(private val itemList : List<String>) : RecyclerView.Adapter<MallListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MallListViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_point_mall_list_item, parent, false)
        return MallListViewHolder(inflateView)
    }

    override fun onBindViewHolder(holder: MallListViewHolder, position: Int) {
        val item = itemList[position]
        holder.apply {

        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}