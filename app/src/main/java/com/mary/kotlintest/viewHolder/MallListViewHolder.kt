package com.mary.kotlintest.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mary.kotlintest.MallListDetailActivity
import com.mary.kotlintest.util.ActivityUtil
import com.mary.kotlintest.util.ILog
import kotlinx.android.synthetic.main.view_holder_point_mall_list_item.view.*

class MallListViewHolder(v: View) : RecyclerView.ViewHolder(v)  {

    var view : View = v

    init {
        setListener()
    }

    private fun setListener() {
        view.textViewItemDetail.setOnClickListener {
            ILog.iLogDebug(TAG, "상세정보 클릭")
            ActivityUtil.startNewActivityWithoutFinish(view.context, MallListDetailActivity::class.java)
        }
    }

    companion object {
        private const val TAG = "MailListViewHolder"
    }
}