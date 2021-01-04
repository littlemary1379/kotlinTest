package com.mary.kotlintest.viewHolder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mary.kotlintest.util.ESSArrows
import com.mary.kotlintest.util.EventCenter
import kotlinx.android.synthetic.main.view_holder_point_item.view.*

class TestViewHolder(v : View) : RecyclerView.ViewHolder(v) {

    var view : View = v

    init {
        setListener()

    }

    companion object {
        private const val TAG = "TestViewHolder"
    }

    fun updateView(item : String) {
        view.textViewCount.text=item
    }

    private fun setListener() {
        view.textViewDetail.setOnClickListener {
            Log.d(TAG, "setListener: 클릭되니??")
            EventCenter.instance.sendEvent(ESSArrows.SHOW_DETAIL, this, null)
        }
    }


}