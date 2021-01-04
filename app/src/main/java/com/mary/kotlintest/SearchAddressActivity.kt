package com.mary.kotlintest

import android.graphics.Color
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.mary.kotlintest.ui.SimpleNavigationBarViewHolder
import com.mary.kotlintest.util.*
import com.mary.kotlintest.viewHolder.WebViewHolder
import org.json.JSONObject
import java.util.*

class SearchAddressActivity : AppCompatActivity() {

    private var simpleNavigationBarViewHolder: SimpleNavigationBarViewHolder? = null
    private var frameLayoutNavigationBar: FrameLayout? = null
    private var webViewHolder: WebViewHolder? = null
    private var frameLayoutWebView: FrameLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_address)
        ThemeUtil.setWindowStatusBarColor(this, Color.WHITE)
        ThemeUtil.setAndroidNativeLightStatusBar(this, true, false)
        findView()
        initNavigationBar()
        initWebView()
    }

    override fun finish() {
        super.finish()
        // when activity finish, current activity will slide_right_out(left in screen to right)
        // and old activity will slide_left_in(show in screen from left)
        //overridePendingTransition(R.anim.activity_slide_left_in, R.anim.activity_slide_right_out)
    }

    private fun findView() {
        frameLayoutNavigationBar = findViewById(R.id.frameLayoutNavigationBar)
        frameLayoutWebView = findViewById(R.id.frameLayoutWebView)
    }

    private fun initNavigationBar() {
        simpleNavigationBarViewHolder = SimpleNavigationBarViewHolder(this)
        simpleNavigationBarViewHolder!!.simpleNavigationBarViewHolderDelegate =
            object : SimpleNavigationBarViewHolder.SimpleNavigationBarViewHolderDelegate {
                override fun onLeftClick() {
                    onBackPressed()
                }

                override fun onHomeClick() {}
                override fun onRightClick() {}
            }
        //simpleNavigationBarViewHolder!!.setImageViewLeft(R.drawable.i_back)
        //simpleNavigationBarViewHolder!!.setTitle(getString(R.string.search_address_title))
        frameLayoutNavigationBar!!.addView(simpleNavigationBarViewHolder!!.view)
    }

    private fun initWebView() {
        webViewHolder = WebViewHolder(this, object : WebViewHolder.WebViewHolderDelegate {
            override fun loadUrlAndOverrideCondition(url: String?): Boolean {
                webViewHolder?.loadUrl(url)
                return true
            }

            override fun onPageFinished(url: String?) {}
        })
        webViewHolder!!.setupWebViewJavascript(JSInterface(object :
            JSInterface.JSInterfaceDelegate {
            override fun onAddressFinished(result: String) {
                try {
                    val addressAreas = result.split(",".toRegex()).toTypedArray()
                    for (i in addressAreas.indices) {
                    }
                    val jsonObject = JSONObject("{$result}")
                    ILog.iLogDebug(TAG, jsonObject.getString("zonecode"))
                    ILog.iLogDebug(TAG, jsonObject.getString("addr"))
                    ILog.iLogDebug(TAG, jsonObject.getString("extraAddr"))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                val hashMap = HashMap<String, Any>()
                hashMap["address"] = result
                EventCenter.instance.sendEvent(ESSArrows.SEARCH_ADDRESS_FINISHED, this, hashMap)
                finish()
            }
        }), "jsInterface")
        frameLayoutWebView!!.addView(webViewHolder!!.view)
        webViewHolder!!.loadUrl("http://13.124.112.44/v1/address")
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onDestroy() {
        if (webViewHolder != null) {
            webViewHolder!!.destroyWebView()
            webViewHolder = null
        }
        super.onDestroy()
    }

    companion object {
        private const val TAG = "SearchAddressActivity"
    }
}