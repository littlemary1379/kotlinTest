package com.mary.kotlintest

import android.content.ClipData
import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mary.kotlintest.ui.SimpleNavigationBarViewHolder
import com.mary.kotlintest.util.ILog
import com.mary.kotlintest.util.ToastUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.frameLayoutNavigation
import kotlinx.android.synthetic.main.activity_mall_list_detail.*

class MallListDetailActivity : AppCompatActivity() {

    lateinit var simpleNavigationViewHolder: SimpleNavigationBarViewHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mall_list_detail)

        initNavigation()
        setListener()
    }

    private fun initNavigation() {
        simpleNavigationViewHolder = SimpleNavigationBarViewHolder(this)
        simpleNavigationViewHolder.simpleNavigationBarViewHolderDelegate =
            object : SimpleNavigationBarViewHolder.SimpleNavigationBarViewHolderDelegate {
                override fun onLeftClick() {
                    onBackPressed()
                }

                override fun onHomeClick() {

                }

                override fun onRightClick() {

                }

            }

        simpleNavigationViewHolder.setTitle("주문 상세보기")
        simpleNavigationViewHolder.setImageViewLeft(R.drawable.i_back)
        frameLayoutNavigation.addView(simpleNavigationViewHolder.view)
    }

    private fun setListener() {
        textViewCopy.setOnClickListener {
            ILog.iLogDebug(TAG, "송장번호 복사 클릭")

            var clipboardManager : ClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            var clip = ClipData.newPlainText("송장번호" , "98877637670")
            clipboardManager.setPrimaryClip(clip)
            ToastUtil.showCustomLongToastNormal(this@MallListDetailActivity, "송장번호가 복사되었습니다.")

        }
    }

    companion object {
        private const val TAG = "MallListDetailActivity"
    }
}