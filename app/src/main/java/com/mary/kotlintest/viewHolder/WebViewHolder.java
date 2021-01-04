package com.mary.kotlintest.viewHolder;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mary.kotlintest.R;
import com.mary.kotlintest.util.ILog;

import kr.gounwoori.ushome.framework.util.view.ViewUtil;

public class WebViewHolder {

    public interface WebViewHolderDelegate {
        boolean loadUrlAndOverrideCondition(String url);
        void onPageFinished(String url);
    }

    private final static String TAG = "WebViewHolder";

    private View view;

    private WebView webView;

    private WebChromeClient webChromeClient = new WebChromeClient() {

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {

            ILog.iLogDebug(TAG, "onCreateWindow");

            view.setWebChromeClient(this);

            WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
            transport.setWebView(view);
            resultMsg.sendToTarget();

            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);

        }

        @SuppressLint("LongLogTag")
        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            ILog.iLogDebug(TAG, "onConsoleMessage " + consoleMessage.message() + " " + consoleMessage.lineNumber() + " " + consoleMessage.sourceId() + " " + consoleMessage.toString());
            return super.onConsoleMessage(consoleMessage);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
            new AlertDialog.Builder(view.getContext()).setTitle("").setMessage(message).setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    result.confirm();
                }
            }).setCancelable(false).create().show();
            return true;
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
            new AlertDialog.Builder(view.getContext()).setTitle("").setMessage(message).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    result.confirm();
                }
            }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    result.cancel();
                }
            }).create().show();
            return true;
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            super.onGeolocationPermissionsShowPrompt(origin, callback);
            callback.invoke(origin, true, false);
        }


    };

    private WebViewClient webViewClient = new WebViewClient() {

        @SuppressLint("LongLogTag")
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //ILog.iLogDebug(TAG, "onReceivedError " + request.getUrl() + " " + error.getErrorCode() + " " + error.getDescription().toString());
            }
        }

        @SuppressLint("LongLogTag")
        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);
            //ILog.iLogDebug(TAG, "onReceivedHttpError " + request.getUrl() + " " + errorResponse.getReasonPhrase() + " " + errorResponse.getStatusCode());
        }

        @SuppressLint("LongLogTag")
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            //ILog.iLogDebug(TAG, "onReceivedSslError " + handler.toString() + " " + error.getUrl() + " " + error.toString());
        }

        /**
         *
         * @param view
         * @param request
         * @return True if the host application wants to leave the current WebView and handle the url itself, otherwise return false.
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            //ILog.iLogDebug(TAG, "shouldOverrideUrlLoading ??? " + request.getUrl().toString());
            return loadUrlAndOverride(request.getUrl().toString());

        }

        /**
         *
         * @param view
         * @param url
         * @return True if the host application wants to leave the current WebView and handle the url itself, otherwise return false.
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            ILog.iLogDebug(TAG, "shouldOverrideUrlLoading " + url);
            return loadUrlAndOverride(url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            ILog.iLogDebug(TAG, "onPageStarted " + url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            webViewHolderDelegate.onPageFinished(url);
            ILog.iLogDebug(TAG, "onPageFinished " + url);
        }

    };

    private WebViewHolderDelegate webViewHolderDelegate;

    public WebViewHolder(Context context, WebViewHolderDelegate webViewHolderDelegate) {
        this.webViewHolderDelegate = webViewHolderDelegate;
        view = ViewUtil.inflateView(context, R.layout.view_holder_webview, null);
        findView();

    }

    private void findView() {
        webView = view.findViewById(R.id.webView);

        initWebView();
    }

    public void setTransparent() {
        webView.setBackgroundColor(0);
        webView.getBackground().setAlpha(0);
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    private void initWebView() {

        setupWebViewSetting();
        setupWebChromeClient();
        setupWebViewClient();

    }

    private boolean loadUrlAndOverride(String url) {

        ILog.iLogDebug(TAG, url);

        if(webViewHolderDelegate.loadUrlAndOverrideCondition(url)) {
            return true;
        }

        webView.loadUrl(url);
        return true;
    }

    @SuppressLint("JavascriptInterface")
    public void setupWebViewJavascript(Object object, String name) {
        webView.addJavascriptInterface(object, name);
    }

    private void setupWebViewSetting() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setSupportZoom(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webSettings.setMediaPlaybackRequiresUserGesture(false);
        }

        /* before KITKAT not support web debug */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setWebContentsDebuggingEnabled(true);
        }
    }

    private void setupWebChromeClient() {

        webView.setWebChromeClient(webChromeClient);
    }

    private void setupWebViewClient() {

        webView.setWebViewClient(webViewClient);
    }

    public void loadUrl(String url) {

        if(url.endsWith(".pdf")) {
            ILog.iLogDebug(TAG, "pdf!!!!");
            String data = "<iframe src='http://docs.google.com/gview?embedded=true&url=" + url + "'"+" width='100%' height='100%' style='border: none;'></iframe>";
            webView.loadData(data, "text/html", "UTF-8");
        }
        else {
            webView.loadUrl(url);
        }
    }

    public void postUrl(String url, byte[] postData) {
        webView.postUrl(url, postData);
    }

    public void evaluateJavascript(String url) {
        webView.evaluateJavascript(url, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                ILog.iLogDebug(TAG, value);
            }
        });
    }

    public void reload() {
        webView.reload();
    }

    public void stopLoading() {
        webView.stopLoading();
    }

    public boolean canGoBack() {
        return webView.canGoBack();
    }

    public void goBack() {
        webView.goBack();
    }

    public View getView() {
        return view;
    }

    public void destroyWebView() {
        ILog.iLogDebug(TAG, "destroyWebView");
        if(webView != null) {
            webView.setWebChromeClient(null);
            webChromeClient = null;
            webView.setWebViewClient(null);
            webViewClient = null;
            webView.stopLoading();
            webView = null;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        ILog.iLogDebug(TAG, "finalize");
        super.finalize();
    }
}
