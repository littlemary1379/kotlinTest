package com.mary.kotlintest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.mary.kotlintest.adapter.TestAdapter
import com.mary.kotlintest.ui.PopupViewHolder
import com.mary.kotlintest.ui.SimpleNavigationBarViewHolder
import com.mary.kotlintest.ui.SimpleNavigationBarViewHolder.SimpleNavigationBarViewHolderDelegate
import com.mary.kotlintest.util.ActivityUtil
import com.mary.kotlintest.util.ESSArrows
import kotlinx.android.synthetic.main.activity_main.*
import com.mary.kotlintest.util.EventCenter
import com.mary.kotlintest.util.ILog
import java.util.HashMap

class MainActivity : AppCompatActivity() {

    private val list : List<String> = listOf(
            "남은 수량 : 1개", "남은 수량 : 2개", "남은 수량 : 3개", "남은 수량 : 4개"
    )

    lateinit var popupViewHolder : PopupViewHolder
    lateinit var simpleNavigationBarViewHolder : SimpleNavigationBarViewHolder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        initESS()
        initNavigation()
        setListener()
    }

    private fun initESS(){
        EventCenter.instance.addEventObserver(ESSArrows.CANCEL_POPUP,this, object: EventCenter.EventRunnable{
            override fun run(arrow: String?, poster: Any?, data: HashMap<String, Any>?) {
                closePopup()
            }
        })

        EventCenter.instance.addEventObserver(ESSArrows.SHOW_DETAIL, this, object :EventCenter.EventRunnable {
            override fun run(arrow: String?, poster: Any?, data: HashMap<String, Any>?) {
                showPopup()
            }

        })
    }

    private fun initNavigation(){
        simpleNavigationBarViewHolder = SimpleNavigationBarViewHolder(this)
        simpleNavigationBarViewHolder.simpleNavigationBarViewHolderDelegate = object : SimpleNavigationBarViewHolderDelegate {
            override fun onLeftClick() {
                TODO("Not yet implemented")
            }

            override fun onHomeClick() {
                TODO("Not yet implemented")
            }

            override fun onRightClick() {
                TODO("Not yet implemented")
            }

        }

        simpleNavigationBarViewHolder.setTitle("포인트몰")
        frameLayoutNavigation.addView(simpleNavigationBarViewHolder.view)
    }

    private fun setListener(){
        textViewMallList.setOnClickListener {
            ILog.iLogDebug(TAG, "몰 리스트 클릭")
            ActivityUtil.startNewActivityWithoutFinish(this@MainActivity, PointMallListActivity::class.java)
        }
    }

    private fun showPopup(){
        popupViewHolder.popupDelegate = object : PopupViewHolder.PopupViewHolderDelegate {
            override fun onLeftClick() {
                ILog.iLogDebug(TAG, "우왕 됏나?")
                ActivityUtil.startNewActivityWithoutFinish(this@MainActivity, SendAddressActivity::class.java)
            }

            override fun onRightClick() {
                closePopup()
            }

        }

        frameLayoutPopup.addView(popupViewHolder.view)
        frameLayoutPopup.visibility = View.VISIBLE

    }

    private fun closePopup() {
        frameLayoutPopup.removeAllViews()
        frameLayoutPopup.visibility = View.GONE
    }


    private fun init() {
        popupViewHolder = PopupViewHolder(this)
        val adapter = TestAdapter(list)
        val layoutManager = GridLayoutManager(this,2)
        //val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter


    }

    companion object {
        private const val TAG = "MainActivity"
    }
}



