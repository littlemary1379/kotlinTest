package com.mary.kotlintest.util

import android.util.Log
import com.mary.kotlintest.BuildConfig

object ILog {

    @JvmStatic
    fun iLogDebug(tag: String, content: Any) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, content.toString())
        }
    }

    fun iLogInfo(tag: String, content: Any) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, content.toString())
        }
    }

    fun iLogError(tag: String, content: Any) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, content.toString())
        }
    }

    fun iLogWarn(tag: String, content: Any) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, content.toString())
        }
    }
}