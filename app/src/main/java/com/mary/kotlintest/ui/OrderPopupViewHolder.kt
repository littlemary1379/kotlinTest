package com.mary.kotlintest.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.mary.kotlintest.R
import com.mary.kotlintest.util.ESSArrows
import kotlinx.android.synthetic.main.view_holder_popup.view.*
import com.mary.kotlintest.util.EventCenter
import com.mary.kotlintest.util.ILog
import kotlinx.android.synthetic.main.view_holder_order_popup.view.*
import kotlinx.android.synthetic.main.view_holder_popup.view.textViewLeft
import kotlinx.android.synthetic.main.view_holder_popup.view.textViewRight

class OrderPopupViewHolder(context : Context)  {

    interface OrderPopupViewHolderDelegate {
        fun onLeftClick()
        fun onRightClick()
        fun onClose()
    }

    lateinit var orderPopupViewHolderDelegate: OrderPopupViewHolderDelegate

    var view: View = LayoutInflater.from(context).inflate(R.layout.view_holder_order_popup, null)

    init {
        Log.d(TAG, "init: 되니 ㅜㅜ?")
        setListener()
    }

    private fun setListener(){

        view.textViewLeft.setOnClickListener { orderPopupViewHolderDelegate.onLeftClick() }
        view.textViewRight.setOnClickListener { orderPopupViewHolderDelegate.onRightClick() }
        view.frameLayoutBackground.setOnClickListener { orderPopupViewHolderDelegate.onClose() }

    }

    companion object {
        private const val TAG = "PopupViewHolder"
    }


}





