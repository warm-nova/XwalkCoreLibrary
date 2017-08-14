package org.xwalk.core.internal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ApplicationErrorReport;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.widget.FrameLayout;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.app.Activity;
import android.os.Bundle;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class XWalkViewBridge extends XWalkViewInternal {
    private final static String WRAPPER_CLASS = "org.xwalk.core.Object";
    private Object wrapper;

    public Object getWrapper() {
        return wrapper;
    }


    public XWalkViewBridge(Context context, AttributeSet attrs, Object wrapper) {
        super(context, attrs);
        this.wrapper = wrapper;
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    public XWalkViewBridge(Context context, Activity activity, Object wrapper) {
        super(context, activity);
        this.wrapper = wrapper;
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    private Method loadStringStringMethod;
    @Override
    public void load(String url, String content) {
        ReflectionHelper.invokeMethod(
            loadStringStringMethod, wrapper, url, content);
    }

    public void loadSuper(String url, String content) {
        super.load(url, content);
    }

    private Method loadAppFromManifestStringStringMethod;
    @Override
    public void loadAppFromManifest(String url, String content) {
        ReflectionHelper.invokeMethod(
            loadAppFromManifestStringStringMethod, wrapper, url, content);
    }

    public void loadAppFromManifestSuper(String url, String content) {
        super.loadAppFromManifest(url, content);
    }

    private Method reloadintMethod;
    @Override
    public void reload(int mode) {
        ReflectionHelper.invokeMethod(
            reloadintMethod, wrapper, mode);
    }

    public void reloadSuper(int mode) {
        super.reload(mode);
    }

    private Method stopLoadingMethod;
    @Override
    public void stopLoading() {
        ReflectionHelper.invokeMethod(
            stopLoadingMethod, wrapper);
    }

    public void stopLoadingSuper() {
        super.stopLoading();
    }

    private Method getUrlMethod;
    @Override
    public String getUrl() {
        return (String)ReflectionHelper.invokeMethod(
            getUrlMethod, wrapper);
    }

    public String getUrlSuper() {
        String ret;
        ret = super.getUrl();
        if (ret == null) return null;
        return ret;
    }

    private Method getTitleMethod;
    @Override
    public String getTitle() {
        return (String)ReflectionHelper.invokeMethod(
            getTitleMethod, wrapper);
    }

    public String getTitleSuper() {
        String ret;
        ret = super.getTitle();
        if (ret == null) return null;
        return ret;
    }

    private Method getOriginalUrlMethod;
    @Override
    public String getOriginalUrl() {
        return (String)ReflectionHelper.invokeMethod(
            getOriginalUrlMethod, wrapper);
    }

    public String getOriginalUrlSuper() {
        String ret;
        ret = super.getOriginalUrl();
        if (ret == null) return null;
        return ret;
    }

    private Method getNavigationHistoryMethod;
    @Override
    public XWalkNavigationHistoryInternal getNavigationHistory() {
        return (XWalkNavigationHistoryBridge)ReflectionHelper.getBridgeOrWrapper(ReflectionHelper.invokeMethod(
            getNavigationHistoryMethod, wrapper));
    }

    public XWalkNavigationHistoryBridge getNavigationHistorySuper() {
        XWalkNavigationHistoryInternal ret;
        ret = super.getNavigationHistory();
        if (ret == null) return null;
        return (ret instanceof XWalkNavigationHistoryBridge ? ((XWalkNavigationHistoryBridge) ret ) : new XWalkNavigationHistoryBridge(ret));
    }

    private Method addJavascriptInterfaceObjectStringMethod;
    @Override
    public void addJavascriptInterface(Object object, String name) {
        ReflectionHelper.invokeMethod(
            addJavascriptInterfaceObjectStringMethod, wrapper, object, name);
    }

    public void addJavascriptInterfaceSuper(Object object, String name) {
        super.addJavascriptInterface(object, name);
    }

    private Method evaluateJavascriptStringValueCallbackMethod;
    @Override
    public void evaluateJavascript(String script, ValueCallback<String> callback) {
        ReflectionHelper.invokeMethod(
            evaluateJavascriptStringValueCallbackMethod, wrapper, script, callback);
    }

    public void evaluateJavascriptSuper(String script, ValueCallback<String> callback) {
        super.evaluateJavascript(script, callback);
    }

    private Method clearCachebooleanMethod;
    @Override
    public void clearCache(boolean includeDiskFiles) {
        ReflectionHelper.invokeMethod(
            clearCachebooleanMethod, wrapper, includeDiskFiles);
    }

    public void clearCacheSuper(boolean includeDiskFiles) {
        super.clearCache(includeDiskFiles);
    }

    private Method hasEnteredFullscreenMethod;
    @Override
    public boolean hasEnteredFullscreen() {
        return (Boolean)ReflectionHelper.invokeMethod(
            hasEnteredFullscreenMethod, wrapper);
    }

    public boolean hasEnteredFullscreenSuper() {
        boolean ret;
        ret = super.hasEnteredFullscreen();
        
        return ret;
    }

    private Method leaveFullscreenMethod;
    @Override
    public void leaveFullscreen() {
        ReflectionHelper.invokeMethod(
            leaveFullscreenMethod, wrapper);
    }

    public void leaveFullscreenSuper() {
        super.leaveFullscreen();
    }

    private Method pauseTimersMethod;
    @Override
    public void pauseTimers() {
        ReflectionHelper.invokeMethod(
            pauseTimersMethod, wrapper);
    }

    public void pauseTimersSuper() {
        super.pauseTimers();
    }

    private Method resumeTimersMethod;
    @Override
    public void resumeTimers() {
        ReflectionHelper.invokeMethod(
            resumeTimersMethod, wrapper);
    }

    public void resumeTimersSuper() {
        super.resumeTimers();
    }

    private Method onHideMethod;
    @Override
    public void onHide() {
        ReflectionHelper.invokeMethod(
            onHideMethod, wrapper);
    }

    public void onHideSuper() {
        super.onHide();
    }

    private Method onShowMethod;
    @Override
    public void onShow() {
        ReflectionHelper.invokeMethod(
            onShowMethod, wrapper);
    }

    public void onShowSuper() {
        super.onShow();
    }

    private Method onDestroyMethod;
    @Override
    public void onDestroy() {
        ReflectionHelper.invokeMethod(
            onDestroyMethod, wrapper);
    }

    public void onDestroySuper() {
        super.onDestroy();
    }

    private Method onActivityResultintintIntentMethod;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ReflectionHelper.invokeMethod(
            onActivityResultintintIntentMethod, wrapper, requestCode, resultCode, data);
    }

    public void onActivityResultSuper(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private Method onNewIntentIntentMethod;
    @Override
    public boolean onNewIntent(Intent intent) {
        return (Boolean)ReflectionHelper.invokeMethod(
            onNewIntentIntentMethod, wrapper, intent);
    }

    public boolean onNewIntentSuper(Intent intent) {
        boolean ret;
        ret = super.onNewIntent(intent);
        
        return ret;
    }

    private Method saveStateBundleMethod;
    @Override
    public boolean saveState(Bundle outState) {
        return (Boolean)ReflectionHelper.invokeMethod(
            saveStateBundleMethod, wrapper, outState);
    }

    public boolean saveStateSuper(Bundle outState) {
        boolean ret;
        ret = super.saveState(outState);
        
        return ret;
    }

    private Method restoreStateBundleMethod;
    @Override
    public boolean restoreState(Bundle inState) {
        return (Boolean)ReflectionHelper.invokeMethod(
            restoreStateBundleMethod, wrapper, inState);
    }

    public boolean restoreStateSuper(Bundle inState) {
        boolean ret;
        ret = super.restoreState(inState);
        
        return ret;
    }

    private Method getAPIVersionMethod;
    @Override
    public String getAPIVersion() {
        return (String)ReflectionHelper.invokeMethod(
            getAPIVersionMethod, wrapper);
    }

    public String getAPIVersionSuper() {
        String ret;
        ret = super.getAPIVersion();
        if (ret == null) return null;
        return ret;
    }

    private Method getXWalkVersionMethod;
    @Override
    public String getXWalkVersion() {
        return (String)ReflectionHelper.invokeMethod(
            getXWalkVersionMethod, wrapper);
    }

    public String getXWalkVersionSuper() {
        String ret;
        ret = super.getXWalkVersion();
        if (ret == null) return null;
        return ret;
    }

    private Method setUIClientXWalkUIClientInternalMethod;
    @Override
    public void setUIClient(XWalkUIClientInternal client) {
        if ((client instanceof XWalkUIClientBridge)) {
            setUIClient((XWalkUIClientBridge) client);
        } else {
            super.setUIClient(client);
        }
    }

    public void setUIClient(XWalkUIClientBridge client) {
        ReflectionHelper.invokeMethod(
            setUIClientXWalkUIClientInternalMethod, wrapper, client.getWrapper());
    }

    public void setUIClientSuper(XWalkUIClientBridge client) {
        super.setUIClient(client);
    }

    private Method setResourceClientXWalkResourceClientInternalMethod;
    @Override
    public void setResourceClient(XWalkResourceClientInternal client) {
        if ((client instanceof XWalkResourceClientBridge)) {
            setResourceClient((XWalkResourceClientBridge) client);
        } else {
            super.setResourceClient(client);
        }
    }

    public void setResourceClient(XWalkResourceClientBridge client) {
        ReflectionHelper.invokeMethod(
            setResourceClientXWalkResourceClientInternalMethod, wrapper, client.getWrapper());
    }

    public void setResourceClientSuper(XWalkResourceClientBridge client) {
        super.setResourceClient(client);
    }

    private Method setBackgroundColorintMethod;
    @Override
    public void setBackgroundColor(int color) {
        ReflectionHelper.invokeMethod(
            setBackgroundColorintMethod, wrapper, color);
    }

    public void setBackgroundColorSuper(int color) {
        super.setBackgroundColor(color);
    }

    private Method setLayerTypeintPaintMethod;
    @Override
    public void setLayerType(int layerType, Paint paint) {
        ReflectionHelper.invokeMethod(
            setLayerTypeintPaintMethod, wrapper, layerType, paint);
    }

    public void setLayerTypeSuper(int layerType, Paint paint) {
        super.setLayerType(layerType, paint);
    }

    private Method setNetworkAvailablebooleanMethod;
    @Override
    public void setNetworkAvailable(boolean networkUp) {
        ReflectionHelper.invokeMethod(
            setNetworkAvailablebooleanMethod, wrapper, networkUp);
    }

    public void setNetworkAvailableSuper(boolean networkUp) {
        super.setNetworkAvailable(networkUp);
    }

    private Method getRemoteDebuggingUrlMethod;
    @Override
    public Uri getRemoteDebuggingUrl() {
        return (Uri)ReflectionHelper.invokeMethod(
            getRemoteDebuggingUrlMethod, wrapper);
    }

    public Uri getRemoteDebuggingUrlSuper() {
        Uri ret;
        ret = super.getRemoteDebuggingUrl();
        if (ret == null) return null;
        return ret;
    }



    private void reflectionInit() throws NoSuchMethodException,
            ClassNotFoundException {
        Class<?> clazz = wrapper.getClass();
        loadStringStringMethod = ReflectionHelper.loadMethod(clazz, "load", String.class, String.class);
        loadAppFromManifestStringStringMethod = ReflectionHelper.loadMethod(clazz, "loadAppFromManifest", String.class, String.class);
        reloadintMethod = ReflectionHelper.loadMethod(clazz, "reload", int.class);
        stopLoadingMethod = ReflectionHelper.loadMethod(clazz, "stopLoading");
        getUrlMethod = ReflectionHelper.loadMethod(clazz, "getUrl");
        getTitleMethod = ReflectionHelper.loadMethod(clazz, "getTitle");
        getOriginalUrlMethod = ReflectionHelper.loadMethod(clazz, "getOriginalUrl");
        getNavigationHistoryMethod = ReflectionHelper.loadMethod(clazz, "getNavigationHistory");
        addJavascriptInterfaceObjectStringMethod = ReflectionHelper.loadMethod(clazz, "addJavascriptInterface", Object.class, String.class);
        evaluateJavascriptStringValueCallbackMethod = ReflectionHelper.loadMethod(clazz, "evaluateJavascript", String.class, ValueCallback.class);
        clearCachebooleanMethod = ReflectionHelper.loadMethod(clazz, "clearCache", boolean.class);
        hasEnteredFullscreenMethod = ReflectionHelper.loadMethod(clazz, "hasEnteredFullscreen");
        leaveFullscreenMethod = ReflectionHelper.loadMethod(clazz, "leaveFullscreen");
        pauseTimersMethod = ReflectionHelper.loadMethod(clazz, "pauseTimers");
        resumeTimersMethod = ReflectionHelper.loadMethod(clazz, "resumeTimers");
        onHideMethod = ReflectionHelper.loadMethod(clazz, "onHide");
        onShowMethod = ReflectionHelper.loadMethod(clazz, "onShow");
        onDestroyMethod = ReflectionHelper.loadMethod(clazz, "onDestroy");
        onActivityResultintintIntentMethod = ReflectionHelper.loadMethod(clazz, "onActivityResult", int.class, int.class, Intent.class);
        onNewIntentIntentMethod = ReflectionHelper.loadMethod(clazz, "onNewIntent", Intent.class);
        saveStateBundleMethod = ReflectionHelper.loadMethod(clazz, "saveState", Bundle.class);
        restoreStateBundleMethod = ReflectionHelper.loadMethod(clazz, "restoreState", Bundle.class);
        getAPIVersionMethod = ReflectionHelper.loadMethod(clazz, "getAPIVersion");
        getXWalkVersionMethod = ReflectionHelper.loadMethod(clazz, "getXWalkVersion");
        setUIClientXWalkUIClientInternalMethod = ReflectionHelper.loadMethod(clazz, "setUIClient", "org.xwalk.core.XWalkUIClient");
        setResourceClientXWalkResourceClientInternalMethod = ReflectionHelper.loadMethod(clazz, "setResourceClient", "org.xwalk.core.XWalkResourceClient");
        setBackgroundColorintMethod = ReflectionHelper.loadMethod(clazz, "setBackgroundColor", int.class);
        setLayerTypeintPaintMethod = ReflectionHelper.loadMethod(clazz, "setLayerType", int.class, Paint.class);
        setNetworkAvailablebooleanMethod = ReflectionHelper.loadMethod(clazz, "setNetworkAvailable", boolean.class);
        getRemoteDebuggingUrlMethod = ReflectionHelper.loadMethod(clazz, "getRemoteDebuggingUrl");

    }


}
