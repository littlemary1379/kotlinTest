package com.mary.kotlintest.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.mary.kotlintest.R
import com.mary.kotlintest.util.ESSArrows
import kotlinx.android.synthetic.main.view_holder_popup.view.*
import com.mary.kotlintest.util.EventCenter

class PopupViewHolder(context : Context)  {

    interface PopupViewHolderDelegate {
        fun onLeftClick()
        fun onRightClick()
    }

    lateinit var popupDelegate: PopupViewHolderDelegate

    var view: View = LayoutInflater.from(context).inflate(R.layout.view_holder_popup, null)

    init {
        Log.d(TAG, "init: 되니 ㅜㅜ?")
        setListener()
    }

    private fun setListener(){

        view.imageViewCancel.setOnClickListener {
            Log.d(TAG, "setListener: 되니 ㅜㅜㅠ?")
            EventCenter.instance.sendEvent(ESSArrows.CANCEL_POPUP, this, null)
        }

        view.textViewLeft.setOnClickListener { popupDelegate.onLeftClick() }
        view.textViewRight.setOnClickListener { popupDelegate.onRightClick() }

    }

    companion object {
        private const val TAG = "PopupViewHolder"
    }


}





