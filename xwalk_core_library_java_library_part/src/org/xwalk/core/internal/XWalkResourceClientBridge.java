package org.xwalk.core.internal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.http.SslError;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebResourceResponse;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class XWalkResourceClientBridge extends XWalkResourceClientInternal {
    private final static String WRAPPER_CLASS = "org.xwalk.core.Object";
    private Object wrapper;

    public Object getWrapper() {
        return wrapper;
    }


    public XWalkResourceClientBridge(XWalkViewBridge view, Object wrapper) {
        super(view);
        this.wrapper = wrapper;
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    private Method onLoadStartedXWalkViewInternalStringMethod;
    @Override
    public void onLoadStarted(XWalkViewInternal view, String url) {
        if ((view instanceof XWalkViewBridge)) {
            onLoadStarted((XWalkViewBridge) view, url);
        } else {
            super.onLoadStarted(view, url);
        }
    }

    public void onLoadStarted(XWalkViewBridge view, String url) {
        ReflectionHelper.invokeMethod(
            onLoadStartedXWalkViewInternalStringMethod, wrapper, view.getWrapper(), url);
    }

    public void onLoadStartedSuper(XWalkViewBridge view, String url) {
        super.onLoadStarted(view, url);
    }

    private Method onLoadFinishedXWalkViewInternalStringMethod;
    @Override
    public void onLoadFinished(XWalkViewInternal view, String url) {
        if ((view instanceof XWalkViewBridge)) {
            onLoadFinished((XWalkViewBridge) view, url);
        } else {
            super.onLoadFinished(view, url);
        }
    }

    public void onLoadFinished(XWalkViewBridge view, String url) {
        ReflectionHelper.invokeMethod(
            onLoadFinishedXWalkViewInternalStringMethod, wrapper, view.getWrapper(), url);
    }

    public void onLoadFinishedSuper(XWalkViewBridge view, String url) {
        super.onLoadFinished(view, url);
    }

    private Method onProgressChangedXWalkViewInternalintMethod;
    @Override
    public void onProgressChanged(XWalkViewInternal view, int progressInPercent) {
        if ((view instanceof XWalkViewBridge)) {
            onProgressChanged((XWalkViewBridge) view, progressInPercent);
        } else {
            super.onProgressChanged(view, progressInPercent);
        }
    }

    public void onProgressChanged(XWalkViewBridge view, int progressInPercent) {
        ReflectionHelper.invokeMethod(
            onProgressChangedXWalkViewInternalintMethod, wrapper, view.getWrapper(), progressInPercent);
    }

    public void onProgressChangedSuper(XWalkViewBridge view, int progressInPercent) {
        super.onProgressChanged(view, progressInPercent);
    }

    private Method shouldInterceptLoadRequestXWalkViewInternalStringMethod;
    @Override
    public WebResourceResponse shouldInterceptLoadRequest(XWalkViewInternal view, String url) {
        if ((view instanceof XWalkViewBridge)) {
            return shouldInterceptLoadRequest((XWalkViewBridge) view, url);
        } else {
            return super.shouldInterceptLoadRequest(view, url);
        }
    }

    public WebResourceResponse shouldInterceptLoadRequest(XWalkViewBridge view, String url) {
        return (WebResourceResponse)ReflectionHelper.invokeMethod(
            shouldInterceptLoadRequestXWalkViewInternalStringMethod, wrapper, view.getWrapper(), url);
    }

    public WebResourceResponse shouldInterceptLoadRequestSuper(XWalkViewBridge view, String url) {
        WebResourceResponse ret;
        ret = super.shouldInterceptLoadRequest(view, url);
        if (ret == null) return null;
        return ret;
    }

    private Method onReceivedLoadErrorXWalkViewInternalintStringStringMethod;
    @Override
    public void onReceivedLoadError(XWalkViewInternal view, int errorCode, String description, String failingUrl) {
        if ((view instanceof XWalkViewBridge)) {
            onReceivedLoadError((XWalkViewBridge) view, errorCode, description, failingUrl);
        } else {
            super.onReceivedLoadError(view, errorCode, description, failingUrl);
        }
    }

    public void onReceivedLoadError(XWalkViewBridge view, int errorCode, String description, String failingUrl) {
        ReflectionHelper.invokeMethod(
            onReceivedLoadErrorXWalkViewInternalintStringStringMethod, wrapper, view.getWrapper(), errorCode, description, failingUrl);
    }

    public void onReceivedLoadErrorSuper(XWalkViewBridge view, int errorCode, String description, String failingUrl) {
        super.onReceivedLoadError(view, errorCode, description, failingUrl);
    }

    private Method shouldOverrideUrlLoadingXWalkViewInternalStringMethod;
    @Override
    public boolean shouldOverrideUrlLoading(XWalkViewInternal view, String url) {
        if ((view instanceof XWalkViewBridge)) {
            return shouldOverrideUrlLoading((XWalkViewBridge) view, url);
        } else {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    public boolean shouldOverrideUrlLoading(XWalkViewBridge view, String url) {
        return (Boolean)ReflectionHelper.invokeMethod(
            shouldOverrideUrlLoadingXWalkViewInternalStringMethod, wrapper, view.getWrapper(), url);
    }

    public boolean shouldOverrideUrlLoadingSuper(XWalkViewBridge view, String url) {
        boolean ret;
        ret = super.shouldOverrideUrlLoading(view, url);
        
        return ret;
    }

    private Method onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod;
    @Override
    public void onReceivedSslError(XWalkViewInternal view, ValueCallback<Boolean> callback, SslError error) {
        if ((view instanceof XWalkViewBridge)) {
            onReceivedSslError((XWalkViewBridge) view, callback, error);
        } else {
            super.onReceivedSslError(view, callback, error);
        }
    }

    public void onReceivedSslError(XWalkViewBridge view, ValueCallback<Boolean> callback, SslError error) {
        ReflectionHelper.invokeMethod(
            onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod, wrapper, view.getWrapper(), callback, error);
    }

    public void onReceivedSslErrorSuper(XWalkViewBridge view, ValueCallback<Boolean> callback, SslError error) {
        super.onReceivedSslError(view, callback, error);
    }



    private void reflectionInit() throws NoSuchMethodException,
            ClassNotFoundException {
        Class<?> clazz = wrapper.getClass();
        onLoadStartedXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "onLoadStarted", "org.xwalk.core.XWalkView", String.class);
        onLoadFinishedXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "onLoadFinished", "org.xwalk.core.XWalkView", String.class);
        onProgressChangedXWalkViewInternalintMethod = ReflectionHelper.loadMethod(clazz, "onProgressChanged", "org.xwalk.core.XWalkView", int.class);
        shouldInterceptLoadRequestXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "shouldInterceptLoadRequest", "org.xwalk.core.XWalkView", String.class);
        onReceivedLoadErrorXWalkViewInternalintStringStringMethod = ReflectionHelper.loadMethod(clazz, "onReceivedLoadError", "org.xwalk.core.XWalkView", int.class, String.class, String.class);
        shouldOverrideUrlLoadingXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "shouldOverrideUrlLoading", "org.xwalk.core.XWalkView", String.class);
        onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod = ReflectionHelper.loadMethod(clazz, "onReceivedSslError", "org.xwalk.core.XWalkView", ValueCallback.class, SslError.class);

    }


}
