package kr.gounwoori.ushome.framework.util.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.mary.kotlintest.R


object ViewUtil {

    @JvmStatic
    fun inflateView(context: Context?, resource: Int, viewGroup: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(resource, viewGroup)
    }


//    fun viewFromBottom(context: Context?, view: View) {
//        val animation = AnimationUtils.loadAnimation(context, R.anim.view_from_bottom)
//        view.startAnimation(animation)
//    }
//
//    fun viewOutBottom(context: Context?, view: View) {
//        val animation = AnimationUtils.loadAnimation(context, R.anim.view_out_bottom)
//        view.startAnimation(animation)
//    }
//
//    fun viewFromBottomQuickly(context: Context?, view: View) {
//        val animation = AnimationUtils.loadAnimation(context, R.anim.view_from_bottom_quickly)
//        view.startAnimation(animation)
//    }
//
//    fun viewOutBottomQuickly(context: Context?, view: View) {
//        val animation = AnimationUtils.loadAnimation(context, R.anim.view_out_bottom_quickly)
//        view.startAnimation(animation)
//    }
//
//    fun viewFromTop(context: Context?, view: View) {
//        val animation = AnimationUtils.loadAnimation(context, R.anim.view_from_top)
//        view.startAnimation(animation)
//    }
//
//    fun viewOutTop(context: Context?, view: View) {
//        val animation = AnimationUtils.loadAnimation(context, R.anim.view_out_top)
//        view.startAnimation(animation)
//    }
}

