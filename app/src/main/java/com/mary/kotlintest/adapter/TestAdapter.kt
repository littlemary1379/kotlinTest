package com.mary.kotlintest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mary.kotlintest.R
import com.mary.kotlintest.viewHolder.TestViewHolder

class TestAdapter(private val itemList : List<String>) : RecyclerView.Adapter<TestViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_point_item, parent, false)
        return TestViewHolder(inflateView)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val item = itemList[position]
        holder.apply {
            updateView(item)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}