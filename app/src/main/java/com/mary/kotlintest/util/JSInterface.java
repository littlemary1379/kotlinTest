package com.mary.kotlintest.util;

import android.webkit.JavascriptInterface;

public class JSInterface {

    private final static String TAG = "JSInterface";

    public interface JSInterfaceDelegate {
        void onAddressFinished(String result);
    }

    private JSInterfaceDelegate jsInterfaceDelegate;

    public JSInterface(JSInterfaceDelegate jsInterfaceDelegate) {
        this.jsInterfaceDelegate = jsInterfaceDelegate;
    }

    @JavascriptInterface
    public void onAddressFinished(String result) {
        ILog.iLogDebug(TAG, "onAddressFinished " + result);
        jsInterfaceDelegate.onAddressFinished(result);
    }

}
