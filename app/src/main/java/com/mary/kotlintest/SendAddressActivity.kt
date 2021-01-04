package com.mary.kotlintest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mary.kotlintest.ui.OrderPopupViewHolder
import com.mary.kotlintest.ui.SimpleNavigationBarViewHolder
import com.mary.kotlintest.util.ActivityUtil
import com.mary.kotlintest.util.ILog
import kotlinx.android.synthetic.main.activity_main.frameLayoutNavigation
import kotlinx.android.synthetic.main.activity_send_address.*

class SendAddressActivity : AppCompatActivity() {

    lateinit var simpleNavigationBarViewHolder : SimpleNavigationBarViewHolder
    lateinit var orderPopupViewHolder : OrderPopupViewHolder

    companion object {
        private const val TAG = "SendAddressActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_address)
        initNavigation()
        setListener()

    }

    private fun initNavigation(){
        simpleNavigationBarViewHolder = SimpleNavigationBarViewHolder(this)
        simpleNavigationBarViewHolder.simpleNavigationBarViewHolderDelegate = object :
            SimpleNavigationBarViewHolder.SimpleNavigationBarViewHolderDelegate {
            override fun onLeftClick() {
                onBackPressed()
            }

            override fun onHomeClick() {
            }

            override fun onRightClick() {

            }

        }

        simpleNavigationBarViewHolder.setTitle("주소지 입력")
        simpleNavigationBarViewHolder.setImageViewLeft(R.drawable.i_back)
        frameLayoutNavigation.addView(simpleNavigationBarViewHolder.view)
    }

    private fun setListener() {
        textViewFindAddress.setOnClickListener {
            ILog.iLogDebug(TAG, "위치 클릭")
            ActivityUtil.startNewActivityWithoutFinish(this@SendAddressActivity, SearchAddressActivity::class.java)
        }

        textViewNext.setOnClickListener {
            ILog.iLogDebug(TAG, "다음 클릭")
            showPopup()
        }
    }

    private fun showPopup(){
        orderPopupViewHolder = OrderPopupViewHolder(this)
        orderPopupViewHolder.orderPopupViewHolderDelegate = object : OrderPopupViewHolder.OrderPopupViewHolderDelegate {
            override fun onLeftClick() {
                ActivityUtil.startNewActivityWithFinish(this@SendAddressActivity, MallListDetailActivity::class.java)
            }

            override fun onRightClick() {
                closePopup()
            }

            override fun onClose() {
                closePopup()
            }

        }

        frameLayoutPopup.addView(orderPopupViewHolder.view)
        frameLayoutPopup.visibility = View.VISIBLE

    }

    private fun closePopup() {
        frameLayoutPopup.removeAllViews()
        frameLayoutPopup.visibility = View.GONE
    }


}