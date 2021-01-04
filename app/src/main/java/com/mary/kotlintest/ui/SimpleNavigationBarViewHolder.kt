package com.mary.kotlintest.ui

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.mary.kotlintest.R
import kr.gounwoori.ushome.framework.util.view.ViewUtil


class SimpleNavigationBarViewHolder(context: Context?) {
    interface SimpleNavigationBarViewHolderDelegate {
        fun onLeftClick()
        fun onHomeClick()
        fun onRightClick()
    }


    val view: View = ViewUtil.inflateView(context, R.layout.view_holder_simple_navigation_bar, null)

    init {
        findView()
        setListener()
    }
    private var imageViewLeft: ImageView? = null
    private var imageViewRight: ImageView? = null
    private var textViewTitle: TextView? = null
    private var imageViewHome: ImageView? = null
    private var viewBottomLine: View? = null
    var simpleNavigationBarViewHolderDelegate: SimpleNavigationBarViewHolderDelegate? = null

    private fun findView() {

        imageViewLeft = view.findViewById(R.id.imageViewLeft)
        imageViewHome = view.findViewById(R.id.imageViewHome)
        imageViewRight = view.findViewById(R.id.imageViewRight)
        textViewTitle = view.findViewById(R.id.textViewTitle)
        viewBottomLine = view.findViewById(R.id.viewBottomLine)
    }

    private fun setListener() {
        imageViewLeft!!.setOnClickListener { simpleNavigationBarViewHolderDelegate!!.onLeftClick() }
        imageViewHome!!.setOnClickListener { simpleNavigationBarViewHolderDelegate!!.onHomeClick() }
        imageViewRight!!.setOnClickListener { simpleNavigationBarViewHolderDelegate!!.onRightClick() }
    }

    fun setImageViewLeft(resourceId: Int) {
        imageViewLeft!!.setImageResource(resourceId)
    }

    fun setImageViewHome(resourceId: Int) {
        imageViewHome!!.setImageResource(resourceId)
    }

    fun setImageViewRight(resourceId: Int) {
        imageViewRight!!.setImageResource(resourceId)
    }

    fun setTitle(title: String?) {
        textViewTitle!!.text = title
    }

    fun setTitle(title: Int) {
        textViewTitle!!.setText(title)
    }

    fun hideBottomLine() {
        viewBottomLine!!.visibility = View.GONE
    }

    val rightImageView: View?
        get() = imageViewRight


}