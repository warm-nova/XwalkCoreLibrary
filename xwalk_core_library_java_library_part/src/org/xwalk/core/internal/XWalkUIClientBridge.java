package org.xwalk.core.internal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.widget.EditText;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class XWalkUIClientBridge extends XWalkUIClientInternal {
    private final static String WRAPPER_CLASS = "org.xwalk.core.Object";
    private Object wrapper;

    public Object getWrapper() {
        return wrapper;
    }

    private Class<?> enumJavascriptMessageTypeClass;
    private Method enumJavascriptMessageTypeClassValueOfMethod;
    private Object ConvertJavascriptMessageTypeInternal(JavascriptMessageTypeInternal type) {
        return ReflectionHelper.invokeMethod(enumJavascriptMessageTypeClassValueOfMethod, null, type.toString());
    }
    private Class<?> enumInitiateByClass;
    private Method enumInitiateByClassValueOfMethod;
    private Object ConvertInitiateByInternal(InitiateByInternal type) {
        return ReflectionHelper.invokeMethod(enumInitiateByClassValueOfMethod, null, type.toString());
    }
    private Class<?> enumLoadStatusClass;
    private Method enumLoadStatusClassValueOfMethod;
    private Object ConvertLoadStatusInternal(LoadStatusInternal type) {
        return ReflectionHelper.invokeMethod(enumLoadStatusClassValueOfMethod, null, type.toString());
    }

    public XWalkUIClientBridge(XWalkViewBridge view, Object wrapper) {
        super(view);
        this.wrapper = wrapper;
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    private Method onCreateWindowRequestedXWalkViewInternalInitiateByInternalValueCallbackMethod;
    @Override
    public boolean onCreateWindowRequested(XWalkViewInternal view, InitiateByInternal initiator, ValueCallback<XWalkViewInternal> callback) {
        if ((view instanceof XWalkViewBridge)) {
            return onCreateWindowRequested((XWalkViewBridge) view, initiator, callback);
        } else {
            return super.onCreateWindowRequested(view, initiator, callback);
        }
    }

    public boolean onCreateWindowRequested(XWalkViewBridge view, InitiateByInternal initiator, ValueCallback<XWalkViewInternal> callback) {
        final ValueCallback<XWalkViewInternal> callbackFinal = callback;
        return (Boolean)ReflectionHelper.invokeMethod(
            onCreateWindowRequestedXWalkViewInternalInitiateByInternalValueCallbackMethod, wrapper, view.getWrapper(), ConvertInitiateByInternal(initiator), new ValueCallback<Object>() {
                @Override
                public void onReceiveValue(Object value) {
                    callbackFinal.onReceiveValue((XWalkViewBridge) ReflectionHelper.getBridgeOrWrapper(value));
                }
            });
    }

    public boolean onCreateWindowRequestedSuper(XWalkViewBridge view, InitiateByInternal initiator, ValueCallback<XWalkViewInternal> callback) {
        boolean ret;
        ret = super.onCreateWindowRequested(view, initiator, callback);
        
        return ret;
    }

    private Method onIconAvailableXWalkViewInternalStringMessageMethod;
    @Override
    public void onIconAvailable(XWalkViewInternal view, String url, Message startDownload) {
        if ((view instanceof XWalkViewBridge)) {
            onIconAvailable((XWalkViewBridge) view, url, startDownload);
        } else {
            super.onIconAvailable(view, url, startDownload);
        }
    }

    public void onIconAvailable(XWalkViewBridge view, String url, Message startDownload) {
        ReflectionHelper.invokeMethod(
            onIconAvailableXWalkViewInternalStringMessageMethod, wrapper, view.getWrapper(), url, startDownload);
    }

    public void onIconAvailableSuper(XWalkViewBridge view, String url, Message startDownload) {
        super.onIconAvailable(view, url, startDownload);
    }

    private Method onReceivedIconXWalkViewInternalStringBitmapMethod;
    @Override
    public void onReceivedIcon(XWalkViewInternal view, String url, Bitmap icon) {
        if ((view instanceof XWalkViewBridge)) {
            onReceivedIcon((XWalkViewBridge) view, url, icon);
        } else {
            super.onReceivedIcon(view, url, icon);
        }
    }

    public void onReceivedIcon(XWalkViewBridge view, String url, Bitmap icon) {
        ReflectionHelper.invokeMethod(
            onReceivedIconXWalkViewInternalStringBitmapMethod, wrapper, view.getWrapper(), url, icon);
    }

    public void onReceivedIconSuper(XWalkViewBridge view, String url, Bitmap icon) {
        super.onReceivedIcon(view, url, icon);
    }

    private Method onRequestFocusXWalkViewInternalMethod;
    @Override
    public void onRequestFocus(XWalkViewInternal view) {
        if ((view instanceof XWalkViewBridge)) {
            onRequestFocus((XWalkViewBridge) view);
        } else {
            super.onRequestFocus(view);
        }
    }

    public void onRequestFocus(XWalkViewBridge view) {
        ReflectionHelper.invokeMethod(
            onRequestFocusXWalkViewInternalMethod, wrapper, view.getWrapper());
    }

    public void onRequestFocusSuper(XWalkViewBridge view) {
        super.onRequestFocus(view);
    }

    private Method onJavascriptCloseWindowXWalkViewInternalMethod;
    @Override
    public void onJavascriptCloseWindow(XWalkViewInternal view) {
        if ((view instanceof XWalkViewBridge)) {
            onJavascriptCloseWindow((XWalkViewBridge) view);
        } else {
            super.onJavascriptCloseWindow(view);
        }
    }

    public void onJavascriptCloseWindow(XWalkViewBridge view) {
        ReflectionHelper.invokeMethod(
            onJavascriptCloseWindowXWalkViewInternalMethod, wrapper, view.getWrapper());
    }

    public void onJavascriptCloseWindowSuper(XWalkViewBridge view) {
        super.onJavascriptCloseWindow(view);
    }

    private Method onJavascriptModalDialogXWalkViewInternalJavascriptMessageTypeInternalStringStringStringXWalkJavascriptResultInternalMethod;
    @Override
    public boolean onJavascriptModalDialog(XWalkViewInternal view, JavascriptMessageTypeInternal type, String url, String message, String defaultValue, XWalkJavascriptResultInternal result) {
        if ((view instanceof XWalkViewBridge)) {
            return onJavascriptModalDialog((XWalkViewBridge) view, type, url, message, defaultValue, (result instanceof XWalkJavascriptResultHandlerBridge ? ((XWalkJavascriptResultHandlerBridge) result ) : new XWalkJavascriptResultHandlerBridge((XWalkJavascriptResultHandlerInternal) result)));
        } else {
            return super.onJavascriptModalDialog(view, type, url, message, defaultValue, result);
        }
    }

    public boolean onJavascriptModalDialog(XWalkViewBridge view, JavascriptMessageTypeInternal type, String url, String message, String defaultValue, XWalkJavascriptResultHandlerBridge result) {
        return (Boolean)ReflectionHelper.invokeMethod(
            onJavascriptModalDialogXWalkViewInternalJavascriptMessageTypeInternalStringStringStringXWalkJavascriptResultInternalMethod, wrapper, view.getWrapper(), ConvertJavascriptMessageTypeInternal(type), url, message, defaultValue, (result instanceof XWalkJavascriptResultHandlerBridge ? ((XWalkJavascriptResultHandlerBridge) result ) : new XWalkJavascriptResultHandlerBridge((XWalkJavascriptResultHandlerInternal) result)).getWrapper());
    }

    public boolean onJavascriptModalDialogSuper(XWalkViewBridge view, JavascriptMessageTypeInternal type, String url, String message, String defaultValue, XWalkJavascriptResultHandlerBridge result) {
        boolean ret;
        ret = super.onJavascriptModalDialog(view, type, url, message, defaultValue, result);
        
        return ret;
    }

    private Method onFullscreenToggledXWalkViewInternalbooleanMethod;
    @Override
    public void onFullscreenToggled(XWalkViewInternal view, boolean enterFullscreen) {
        if ((view instanceof XWalkViewBridge)) {
            onFullscreenToggled((XWalkViewBridge) view, enterFullscreen);
        } else {
            super.onFullscreenToggled(view, enterFullscreen);
        }
    }

    public void onFullscreenToggled(XWalkViewBridge view, boolean enterFullscreen) {
        ReflectionHelper.invokeMethod(
            onFullscreenToggledXWalkViewInternalbooleanMethod, wrapper, view.getWrapper(), enterFullscreen);
    }

    public void onFullscreenToggledSuper(XWalkViewBridge view, boolean enterFullscreen) {
        super.onFullscreenToggled(view, enterFullscreen);
    }

    private Method openFileChooserXWalkViewInternalValueCallbackStringStringMethod;
    @Override
    public void openFileChooser(XWalkViewInternal view, ValueCallback<Uri> uploadFile, String acceptType, String capture) {
        if ((view instanceof XWalkViewBridge)) {
            openFileChooser((XWalkViewBridge) view, uploadFile, acceptType, capture);
        } else {
            super.openFileChooser(view, uploadFile, acceptType, capture);
        }
    }

    public void openFileChooser(XWalkViewBridge view, ValueCallback<Uri> uploadFile, String acceptType, String capture) {
        ReflectionHelper.invokeMethod(
            openFileChooserXWalkViewInternalValueCallbackStringStringMethod, wrapper, view.getWrapper(), uploadFile, acceptType, capture);
    }

    public void openFileChooserSuper(XWalkViewBridge view, ValueCallback<Uri> uploadFile, String acceptType, String capture) {
        super.openFileChooser(view, uploadFile, acceptType, capture);
    }

    private Method onScaleChangedXWalkViewInternalfloatfloatMethod;
    @Override
    public void onScaleChanged(XWalkViewInternal view, float oldScale, float newScale) {
        if ((view instanceof XWalkViewBridge)) {
            onScaleChanged((XWalkViewBridge) view, oldScale, newScale);
        } else {
            super.onScaleChanged(view, oldScale, newScale);
        }
    }

    public void onScaleChanged(XWalkViewBridge view, float oldScale, float newScale) {
        ReflectionHelper.invokeMethod(
            onScaleChangedXWalkViewInternalfloatfloatMethod, wrapper, view.getWrapper(), oldScale, newScale);
    }

    public void onScaleChangedSuper(XWalkViewBridge view, float oldScale, float newScale) {
        super.onScaleChanged(view, oldScale, newScale);
    }

    private Method shouldOverrideKeyEventXWalkViewInternalKeyEventMethod;
    @Override
    public boolean shouldOverrideKeyEvent(XWalkViewInternal view, KeyEvent event) {
        if ((view instanceof XWalkViewBridge)) {
            return shouldOverrideKeyEvent((XWalkViewBridge) view, event);
        } else {
            return super.shouldOverrideKeyEvent(view, event);
        }
    }

    public boolean shouldOverrideKeyEvent(XWalkViewBridge view, KeyEvent event) {
        return (Boolean)ReflectionHelper.invokeMethod(
            shouldOverrideKeyEventXWalkViewInternalKeyEventMethod, wrapper, view.getWrapper(), event);
    }

    public boolean shouldOverrideKeyEventSuper(XWalkViewBridge view, KeyEvent event) {
        boolean ret;
        ret = super.shouldOverrideKeyEvent(view, event);
        
        return ret;
    }

    private Method onUnhandledKeyEventXWalkViewInternalKeyEventMethod;
    @Override
    public void onUnhandledKeyEvent(XWalkViewInternal view, KeyEvent event) {
        if ((view instanceof XWalkViewBridge)) {
            onUnhandledKeyEvent((XWalkViewBridge) view, event);
        } else {
            super.onUnhandledKeyEvent(view, event);
        }
    }

    public void onUnhandledKeyEvent(XWalkViewBridge view, KeyEvent event) {
        ReflectionHelper.invokeMethod(
            onUnhandledKeyEventXWalkViewInternalKeyEventMethod, wrapper, view.getWrapper(), event);
    }

    public void onUnhandledKeyEventSuper(XWalkViewBridge view, KeyEvent event) {
        super.onUnhandledKeyEvent(view, event);
    }

    private Method onReceivedTitleXWalkViewInternalStringMethod;
    @Override
    public void onReceivedTitle(XWalkViewInternal view, String title) {
        if ((view instanceof XWalkViewBridge)) {
            onReceivedTitle((XWalkViewBridge) view, title);
        } else {
            super.onReceivedTitle(view, title);
        }
    }

    public void onReceivedTitle(XWalkViewBridge view, String title) {
        ReflectionHelper.invokeMethod(
            onReceivedTitleXWalkViewInternalStringMethod, wrapper, view.getWrapper(), title);
    }

    public void onReceivedTitleSuper(XWalkViewBridge view, String title) {
        super.onReceivedTitle(view, title);
    }

    private Method onPageLoadStartedXWalkViewInternalStringMethod;
    @Override
    public void onPageLoadStarted(XWalkViewInternal view, String url) {
        if ((view instanceof XWalkViewBridge)) {
            onPageLoadStarted((XWalkViewBridge) view, url);
        } else {
            super.onPageLoadStarted(view, url);
        }
    }

    public void onPageLoadStarted(XWalkViewBridge view, String url) {
        ReflectionHelper.invokeMethod(
            onPageLoadStartedXWalkViewInternalStringMethod, wrapper, view.getWrapper(), url);
    }

    public void onPageLoadStartedSuper(XWalkViewBridge view, String url) {
        super.onPageLoadStarted(view, url);
    }

    private Method onPageLoadStoppedXWalkViewInternalStringLoadStatusInternalMethod;
    @Override
    public void onPageLoadStopped(XWalkViewInternal view, String url, LoadStatusInternal status) {
        if ((view instanceof XWalkViewBridge)) {
            onPageLoadStopped((XWalkViewBridge) view, url, status);
        } else {
            super.onPageLoadStopped(view, url, status);
        }
    }

    public void onPageLoadStopped(XWalkViewBridge view, String url, LoadStatusInternal status) {
        ReflectionHelper.invokeMethod(
            onPageLoadStoppedXWalkViewInternalStringLoadStatusInternalMethod, wrapper, view.getWrapper(), url, ConvertLoadStatusInternal(status));
    }

    public void onPageLoadStoppedSuper(XWalkViewBridge view, String url, LoadStatusInternal status) {
        super.onPageLoadStopped(view, url, status);
    }



    private void reflectionInit() throws NoSuchMethodException,
            ClassNotFoundException {
        Class<?> clazz = wrapper.getClass();
        enumJavascriptMessageTypeClass = clazz.getClassLoader().loadClass("org.xwalk.core.XWalkUIClient$JavascriptMessageType");
        enumJavascriptMessageTypeClassValueOfMethod = enumJavascriptMessageTypeClass.getMethod("valueOf", String.class);
        enumInitiateByClass = clazz.getClassLoader().loadClass("org.xwalk.core.XWalkUIClient$InitiateBy");
        enumInitiateByClassValueOfMethod = enumInitiateByClass.getMethod("valueOf", String.class);
        enumLoadStatusClass = clazz.getClassLoader().loadClass("org.xwalk.core.XWalkUIClient$LoadStatus");
        enumLoadStatusClassValueOfMethod = enumLoadStatusClass.getMethod("valueOf", String.class);
        onCreateWindowRequestedXWalkViewInternalInitiateByInternalValueCallbackMethod = ReflectionHelper.loadMethod(clazz, "onCreateWindowRequested", "org.xwalk.core.XWalkView", enumInitiateByClass, ValueCallback.class);
        onIconAvailableXWalkViewInternalStringMessageMethod = ReflectionHelper.loadMethod(clazz, "onIconAvailable", "org.xwalk.core.XWalkView", String.class, Message.class);
        onReceivedIconXWalkViewInternalStringBitmapMethod = ReflectionHelper.loadMethod(clazz, "onReceivedIcon", "org.xwalk.core.XWalkView", String.class, Bitmap.class);
        onRequestFocusXWalkViewInternalMethod = ReflectionHelper.loadMethod(clazz, "onRequestFocus", "org.xwalk.core.XWalkView");
        onJavascriptCloseWindowXWalkViewInternalMethod = ReflectionHelper.loadMethod(clazz, "onJavascriptCloseWindow", "org.xwalk.core.XWalkView");
        onJavascriptModalDialogXWalkViewInternalJavascriptMessageTypeInternalStringStringStringXWalkJavascriptResultInternalMethod = ReflectionHelper.loadMethod(clazz, "onJavascriptModalDialog", "org.xwalk.core.XWalkView", enumJavascriptMessageTypeClass, String.class, String.class, String.class, "org.xwalk.core.XWalkJavascriptResult");
        onFullscreenToggledXWalkViewInternalbooleanMethod = ReflectionHelper.loadMethod(clazz, "onFullscreenToggled", "org.xwalk.core.XWalkView", boolean.class);
        openFileChooserXWalkViewInternalValueCallbackStringStringMethod = ReflectionHelper.loadMethod(clazz, "openFileChooser", "org.xwalk.core.XWalkView", ValueCallback.class, String.class, String.class);
        onScaleChangedXWalkViewInternalfloatfloatMethod = ReflectionHelper.loadMethod(clazz, "onScaleChanged", "org.xwalk.core.XWalkView", float.class, float.class);
        shouldOverrideKeyEventXWalkViewInternalKeyEventMethod = ReflectionHelper.loadMethod(clazz, "shouldOverrideKeyEvent", "org.xwalk.core.XWalkView", KeyEvent.class);
        onUnhandledKeyEventXWalkViewInternalKeyEventMethod = ReflectionHelper.loadMethod(clazz, "onUnhandledKeyEvent", "org.xwalk.core.XWalkView", KeyEvent.class);
        onReceivedTitleXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "onReceivedTitle", "org.xwalk.core.XWalkView", String.class);
        onPageLoadStartedXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "onPageLoadStarted", "org.xwalk.core.XWalkView", String.class);
        onPageLoadStoppedXWalkViewInternalStringLoadStatusInternalMethod = ReflectionHelper.loadMethod(clazz, "onPageLoadStopped", "org.xwalk.core.XWalkView", String.class, enumLoadStatusClass);

    }


}
