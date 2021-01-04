package com.mary.kotlintest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mary.kotlintest.adapter.MallListAdapter
import com.mary.kotlintest.ui.SimpleNavigationBarViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.frameLayoutNavigation
import kotlinx.android.synthetic.main.activity_main.recyclerView
import kotlinx.android.synthetic.main.activity_point_mall_list.*

class PointMallListActivity : AppCompatActivity() {

    lateinit var simpleNavigationViewHolder: SimpleNavigationBarViewHolder


    private val list: List<String> = listOf(
        "휴대용 손난로", "휴대용 손난로2"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_point_mall_list)

        initNavigation()
        init()
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

        simpleNavigationViewHolder.setTitle("포인트몰 주문조회")
        simpleNavigationViewHolder.setImageViewLeft(R.drawable.i_back)
        frameLayoutNavigation.addView(simpleNavigationViewHolder.view)
    }

    private fun init(){
        val adapter = MallListAdapter(list)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}