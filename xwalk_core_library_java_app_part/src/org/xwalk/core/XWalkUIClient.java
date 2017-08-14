package org.xwalk.core;

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




/**
 * This class notifies the embedder UI events/callbacks.
 */
public class XWalkUIClient  {





    /**
     * The type of JavaScript modal dialog.
     * @since 1.0
     */
    public enum JavascriptMessageType {
        /** JavaScript alert dialog. */
        JAVASCRIPT_ALERT,
        /** JavaScript confirm dialog. */
        JAVASCRIPT_CONFIRM,
        /** JavaScript prompt dialog. */
        JAVASCRIPT_PROMPT,
        /** JavaScript dialog for a window-before-unload notification. */
        JAVASCRIPT_BEFOREUNLOAD
    }
    private Class<?> enumJavascriptMessageTypeClass;
    private Method enumJavascriptMessageTypeClassValueOfMethod;
    private Object ConvertJavascriptMessageType(JavascriptMessageType type) {
        return ReflectionHelper.invokeMethod(enumJavascriptMessageTypeClassValueOfMethod, null, type.toString());
    }


    /**
     * Initiator
     * @since 4.0
     */
    public enum InitiateBy {
        BY_USER_GESTURE,
        BY_JAVASCRIPT
    }
    private Class<?> enumInitiateByClass;
    private Method enumInitiateByClassValueOfMethod;
    private Object ConvertInitiateBy(InitiateBy type) {
        return ReflectionHelper.invokeMethod(enumInitiateByClassValueOfMethod, null, type.toString());
    }



    /**
     * The status when a page stopped loading
     * @since 2.1
     */
    public enum LoadStatus {
        /** Loading finished. */
        FINISHED,
        /** Loading failed. */
        FAILED,
        /** Loading cancelled by user. */
        CANCELLED
    }
    private Class<?> enumLoadStatusClass;
    private Method enumLoadStatusClassValueOfMethod;
    private Object ConvertLoadStatus(LoadStatus type) {
        return ReflectionHelper.invokeMethod(enumLoadStatusClassValueOfMethod, null, type.toString());
    }


    private final static String BRIDGE_CLASS = "org.xwalk.core.internal.XWalkUIClientBridge";
    private Object bridge;

    Object getBridge() {
        return bridge;
    }



    /**
     * Constructor.
     * @param view the owner XWalkView instance.
     * @since 1.0
     */
    public XWalkUIClient(XWalkView view) {

        bridge = ReflectionHelper.createInstance("XWalkUIClientInternalXWalkViewInternalConstructor", view.getBridge(), this);
        try {
            reflectionInit();
        } catch(Exception e) {
            ReflectionHelper.handleException(e);
        }

    }

    private Method onCreateWindowRequestedXWalkViewInternalInitiateByInternalValueCallbackMethod;


    /**
     * Request the host application to create a new window
     * @param view The XWalkView which initiate the request for a new window
     * @param initiator How the request was initiated
     * @param callback Callback when once a new XWalkView has been created
     * @return Return true if the host application will create a new window
     * @since 4.0
     */
    public boolean onCreateWindowRequested(XWalkView view, InitiateBy initiator, ValueCallback<XWalkView> callback) {
        return (Boolean)ReflectionHelper.invokeMethod(onCreateWindowRequestedXWalkViewInternalInitiateByInternalValueCallbackMethod, bridge, view.getBridge(), ConvertInitiateBy(initiator), callback);
    }

    private Method onIconAvailableXWalkViewInternalStringMessageMethod;


    /**
     * Notify the host application that an icon is available, send the message to start the downloading
     * @param view The XWalkView that icon belongs to
     * @param url The icon url
     * @param startDownload Message to initiate icon download
     * @since 4.0
     */
    public void onIconAvailable(XWalkView view, String url, Message startDownload) {
        ReflectionHelper.invokeMethod(onIconAvailableXWalkViewInternalStringMessageMethod, bridge, view.getBridge(), url, startDownload);
    }

    private Method onReceivedIconXWalkViewInternalStringBitmapMethod;


    /**
     * Notify the host application of a new icon has been downloaded
     * @param view The XWalkView that icon belongs to
     * @param url The icon url
     * @param icon The icon image
     * @since 4.0
     */
    public void onReceivedIcon(XWalkView view, String url, Bitmap icon) {
        ReflectionHelper.invokeMethod(onReceivedIconXWalkViewInternalStringBitmapMethod, bridge, view.getBridge(), url, icon);
    }

    private Method onRequestFocusXWalkViewInternalMethod;


    /**
     * Request display and focus for this XWalkView.
     * @param view the owner XWalkView instance.
     * @since 1.0
     */
    public void onRequestFocus(XWalkView view) {
        ReflectionHelper.invokeMethod(onRequestFocusXWalkViewInternalMethod, bridge, view.getBridge());
    }

    private Method onJavascriptCloseWindowXWalkViewInternalMethod;


    /**
     * Notify the client to close the given XWalkView.
     * @param view the owner XWalkView instance.
     * @since 1.0
     */
    public void onJavascriptCloseWindow(XWalkView view) {
        ReflectionHelper.invokeMethod(onJavascriptCloseWindowXWalkViewInternalMethod, bridge, view.getBridge());
    }

    private Method onJavascriptModalDialogXWalkViewInternalJavascriptMessageTypeInternalStringStringStringXWalkJavascriptResultInternalMethod;


    /**
     * Tell the client to display a prompt dialog to the user.
     * @param view the owner XWalkView instance.
     * @param type the type of JavaScript modal dialog.
     * @param url the url of the web page which wants to show this dialog.
     * @param message the message to be shown.
     * @param defaultValue the default value string. Only valid for Prompt dialog.
     * @param result the callback to handle the result from caller.
     * @since 1.0
     */
    public boolean onJavascriptModalDialog(XWalkView view, JavascriptMessageType type, String url, String message, String defaultValue, XWalkJavascriptResult result) {
        return (Boolean)ReflectionHelper.invokeMethod(onJavascriptModalDialogXWalkViewInternalJavascriptMessageTypeInternalStringStringStringXWalkJavascriptResultInternalMethod, bridge, view.getBridge(), ConvertJavascriptMessageType(type), url, message, defaultValue, ((XWalkJavascriptResultHandler) result).getBridge());
    }

    private Method onFullscreenToggledXWalkViewInternalbooleanMethod;


    /**
     * Tell the client to toggle fullscreen mode.
     * @param view the owner XWalkView instance.
     * @param enterFullscreen true if it has entered fullscreen mode.
     * @since 1.0
     */
    public void onFullscreenToggled(XWalkView view, boolean enterFullscreen) {
        ReflectionHelper.invokeMethod(onFullscreenToggledXWalkViewInternalbooleanMethod, bridge, view.getBridge(), enterFullscreen);
    }

    private Method openFileChooserXWalkViewInternalValueCallbackStringStringMethod;


    /**
     * Tell the client to open a file chooser.
     * @param view the owner XWalkView instance.
     * @param uploadFile the callback class to handle the result from caller. It MUST
     *        be invoked in all cases. Leave it not invoked will block all following
     *        requests to open file chooser.
     * @param acceptType value of the 'accept' attribute of the input tag associated
     *        with this file picker.
     * @param capture value of the 'capture' attribute of the input tag associated
     *        with this file picker
     * @since 1.0
     */
    public void openFileChooser(XWalkView view, ValueCallback<Uri> uploadFile, String acceptType, String capture) {
        ReflectionHelper.invokeMethod(openFileChooserXWalkViewInternalValueCallbackStringStringMethod, bridge, view.getBridge(), uploadFile, acceptType, capture);
    }

    private Method onScaleChangedXWalkViewInternalfloatfloatMethod;


    /**
     * Notify the client that the scale applied to the XWalkView has changed.
     * @param view the owner XWalkView instance.
     * @param oldScale the old scale before scaling.
     * @param newScale the current scale factor after scaling.
     * @since 1.0
     */
    public void onScaleChanged(XWalkView view, float oldScale, float newScale) {
        ReflectionHelper.invokeMethod(onScaleChangedXWalkViewInternalfloatfloatMethod, bridge, view.getBridge(), oldScale, newScale);
    }

    private Method shouldOverrideKeyEventXWalkViewInternalKeyEventMethod;


    /**
     * Give the host application a chance to handle the key event synchronously.
     * e.g. menu shortcut key events need to be filtered this way. If return
     * true, XWalkView will not handle the key event. If return false, XWalkView
     * will always handle the key event, so none of the super in the view chain
     * will see the key event. The default behavior returns false.
     *
     * @param view The XWalkView that is initiating the callback.
     * @param event The key event.
     * @return True if the host application wants to handle the key event
     *         itself, otherwise return false
     *
     * @since 2.1
     */
    public boolean shouldOverrideKeyEvent(XWalkView view, KeyEvent event) {
        return (Boolean)ReflectionHelper.invokeMethod(shouldOverrideKeyEventXWalkViewInternalKeyEventMethod, bridge, view.getBridge(), event);
    }

    private Method onUnhandledKeyEventXWalkViewInternalKeyEventMethod;


    /**
     * Notify the host application that a key was not handled by the XWalkView.
     * Except system keys, XWalkView always consumes the keys in the normal flow
     * or if shouldOverrideKeyEvent returns true. This is called asynchronously
     * from where the key is dispatched. It gives the host application a chance
     * to handle the unhandled key events.
     *
     * @param view The XWalkView that is initiating the callback.
     * @param event The key event.
     *
     * @since 2.1
     */
    public void onUnhandledKeyEvent(XWalkView view, KeyEvent event) {
        ReflectionHelper.invokeMethod(onUnhandledKeyEventXWalkViewInternalKeyEventMethod, bridge, view.getBridge(), event);
    }

    private Method onReceivedTitleXWalkViewInternalStringMethod;


    /**
     * Notify the host application of a change in the document title.
     * @param view The XWalkView that initiated the callback.
     * @param title A String containing the new title of the document.
     * @since 2.1
     */
    public void onReceivedTitle(XWalkView view, String title) {
        ReflectionHelper.invokeMethod(onReceivedTitleXWalkViewInternalStringMethod, bridge, view.getBridge(), title);
    }

    private Method onPageLoadStartedXWalkViewInternalStringMethod;


    /**
     * Notify the host application that a page has started loading. This method
     * is called once for each main frame load so a page with iframes or
     * framesets will call onPageLoadStarted one time for the main frame. This also
     * means that onPageLoadStarted will not be called when the contents of an
     * embedded frame changes, i.e. clicking a link whose target is an iframe.
     *
     * @param view The XWalkView that is initiating the callback.
     * @param url The url to be loaded.
     *
     * @since 2.1
     */
    public void onPageLoadStarted(XWalkView view, String url) {
        ReflectionHelper.invokeMethod(onPageLoadStartedXWalkViewInternalStringMethod, bridge, view.getBridge(), url);
    }

    private Method onPageLoadStoppedXWalkViewInternalStringLoadStatusInternalMethod;


    /**
     * Notify the host application that a page has stopped loading. This method
     * is called only for main frame. When onPageLoadStopped() is called, the
     * rendering picture may not be updated yet.
     *
     * @param view The XWalkView that is initiating the callback.
     * @param url The url of the page.
     * @param status The status when the page stopped loading.
     *
     * @since 2.1
     */
    public void onPageLoadStopped(XWalkView view, String url, LoadStatus status) {
        ReflectionHelper.invokeMethod(onPageLoadStoppedXWalkViewInternalStringLoadStatusInternalMethod, bridge, view.getBridge(), url, ConvertLoadStatus(status));
    }



    private void reflectionInit() throws NoSuchMethodException,
            ClassNotFoundException {
        Class<?> clazz = bridge.getClass();
        enumJavascriptMessageTypeClass = clazz.getClassLoader().loadClass("org.xwalk.core.internal.XWalkUIClientInternal$JavascriptMessageTypeInternal");
        enumJavascriptMessageTypeClassValueOfMethod = enumJavascriptMessageTypeClass.getMethod("valueOf", String.class);
        enumInitiateByClass = clazz.getClassLoader().loadClass("org.xwalk.core.internal.XWalkUIClientInternal$InitiateByInternal");
        enumInitiateByClassValueOfMethod = enumInitiateByClass.getMethod("valueOf", String.class);
        enumLoadStatusClass = clazz.getClassLoader().loadClass("org.xwalk.core.internal.XWalkUIClientInternal$LoadStatusInternal");
        enumLoadStatusClassValueOfMethod = enumLoadStatusClass.getMethod("valueOf", String.class);
        onCreateWindowRequestedXWalkViewInternalInitiateByInternalValueCallbackMethod = ReflectionHelper.loadMethod(clazz, "onCreateWindowRequestedSuper", "org.xwalk.core.internal.XWalkViewBridge", enumInitiateByClass, ValueCallback.class);
        onIconAvailableXWalkViewInternalStringMessageMethod = ReflectionHelper.loadMethod(clazz, "onIconAvailableSuper", "org.xwalk.core.internal.XWalkViewBridge", String.class, Message.class);
        onReceivedIconXWalkViewInternalStringBitmapMethod = ReflectionHelper.loadMethod(clazz, "onReceivedIconSuper", "org.xwalk.core.internal.XWalkViewBridge", String.class, Bitmap.class);
        onRequestFocusXWalkViewInternalMethod = ReflectionHelper.loadMethod(clazz, "onRequestFocusSuper", "org.xwalk.core.internal.XWalkViewBridge");
        onJavascriptCloseWindowXWalkViewInternalMethod = ReflectionHelper.loadMethod(clazz, "onJavascriptCloseWindowSuper", "org.xwalk.core.internal.XWalkViewBridge");
        onJavascriptModalDialogXWalkViewInternalJavascriptMessageTypeInternalStringStringStringXWalkJavascriptResultInternalMethod = ReflectionHelper.loadMethod(clazz, "onJavascriptModalDialogSuper", "org.xwalk.core.internal.XWalkViewBridge", enumJavascriptMessageTypeClass, String.class, String.class, String.class, "org.xwalk.core.internal.XWalkJavascriptResultHandlerBridge");
        onFullscreenToggledXWalkViewInternalbooleanMethod = ReflectionHelper.loadMethod(clazz, "onFullscreenToggledSuper", "org.xwalk.core.internal.XWalkViewBridge", boolean.class);
        openFileChooserXWalkViewInternalValueCallbackStringStringMethod = ReflectionHelper.loadMethod(clazz, "openFileChooserSuper", "org.xwalk.core.internal.XWalkViewBridge", ValueCallback.class, String.class, String.class);
        onScaleChangedXWalkViewInternalfloatfloatMethod = ReflectionHelper.loadMethod(clazz, "onScaleChangedSuper", "org.xwalk.core.internal.XWalkViewBridge", float.class, float.class);
        shouldOverrideKeyEventXWalkViewInternalKeyEventMethod = ReflectionHelper.loadMethod(clazz, "shouldOverrideKeyEventSuper", "org.xwalk.core.internal.XWalkViewBridge", KeyEvent.class);
        onUnhandledKeyEventXWalkViewInternalKeyEventMethod = ReflectionHelper.loadMethod(clazz, "onUnhandledKeyEventSuper", "org.xwalk.core.internal.XWalkViewBridge", KeyEvent.class);
        onReceivedTitleXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "onReceivedTitleSuper", "org.xwalk.core.internal.XWalkViewBridge", String.class);
        onPageLoadStartedXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "onPageLoadStartedSuper", "org.xwalk.core.internal.XWalkViewBridge", String.class);
        onPageLoadStoppedXWalkViewInternalStringLoadStatusInternalMethod = ReflectionHelper.loadMethod(clazz, "onPageLoadStoppedSuper", "org.xwalk.core.internal.XWalkViewBridge", String.class, enumLoadStatusClass);

    }


    static {
        ReflectionHelper.registerConstructor("XWalkUIClientInternalXWalkViewInternalConstructor", "org.xwalk.core.internal.XWalkUIClientBridge", "org.xwalk.core.internal.XWalkViewBridge", Object.class);

    }

}
