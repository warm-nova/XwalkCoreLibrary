package org.xwalk.core;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.http.SslError;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebResourceResponse;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;




/**
 * This class notifies the embedder resource events/callbacks.
 */
public class XWalkResourceClient  {


    /**
     * Success
     * @since 1.0
     */
    public final static int ERROR_OK = 0;

    /**
     * Generic error
     * @since 1.0
     */
    public final static int ERROR_UNKNOWN = -1;

    /**
     * Server or proxy hostname lookup failed
     * @since 1.0
     */
    public final static int ERROR_HOST_LOOKUP = -2;

    /**
     * Unsupported authentication scheme (not basic or digest)
     * @since 1.0
     */
    public final static int ERROR_UNSUPPORTED_AUTH_SCHEME = -3;

    /**
     * User authentication failed on server
     * @since 1.0
     */
    public final static int ERROR_AUTHENTICATION = -4;

    /**
     * User authentication failed on proxy
     * @since 1.0
     */
    public final static int ERROR_PROXY_AUTHENTICATION = -5;

    /**
     * Failed to connect to the server
     * @since 1.0
     */
    public final static int ERROR_CONNECT = -6;

    /**
     * Failed to read or write to the server
     * @since 1.0
     */
    public final static int ERROR_IO = -7;

    /**
     * Connection timed out
     * @since 1.0
     */
    public final static int ERROR_TIMEOUT = -8;

    /**
     * Too many redirects
     * @since 1.0
     */
    public final static int ERROR_REDIRECT_LOOP = -9;

    /**
     * Unsupported URI scheme
     * @since 1.0
     */
    public final static int ERROR_UNSUPPORTED_SCHEME = -10;

    /**
     * Failed to perform SSL handshake
     * @since 1.0
     */
    public final static int ERROR_FAILED_SSL_HANDSHAKE = -11;

    /**
     * Malformed URL
     * @since 1.0
     */
    public final static int ERROR_BAD_URL = -12;

    /**
     * Generic file error
     * @since 1.0
     */
    public final static int ERROR_FILE = -13;

    /**
     * File not found
     * @since 1.0
     */
    public final static int ERROR_FILE_NOT_FOUND = -14;

    /**
     * Too many requests during this load
     * @since 1.0
     */
    public final static int ERROR_TOO_MANY_REQUESTS = -15;




    private final static String BRIDGE_CLASS = "org.xwalk.core.internal.XWalkResourceClientBridge";
    private Object bridge;

    Object getBridge() {
        return bridge;
    }



    /**
     * Constructor.
     * @param view the owner XWalkView instance.
     * @since 1.0
     */
    public XWalkResourceClient(XWalkView view) {

        bridge = ReflectionHelper.createInstance("XWalkResourceClientInternalXWalkViewInternalConstructor", view.getBridge(), this);
        try {
            reflectionInit();
        } catch(Exception e) {
            ReflectionHelper.handleException(e);
        }

    }

    private Method onLoadStartedXWalkViewInternalStringMethod;


    /**
     * Notify the client that the XWalkView will load the resource specified
     * by the given url.
     * @param view the owner XWalkView instance.
     * @param url the url for the resource to be loaded.
     * @since 1.0
     */
    public void onLoadStarted(XWalkView view, String url) {
        ReflectionHelper.invokeMethod(onLoadStartedXWalkViewInternalStringMethod, bridge, view.getBridge(), url);
    }

    private Method onLoadFinishedXWalkViewInternalStringMethod;


    /**
     * Notify the client that the XWalkView completes to load the resource
     * specified by the given url.
     * @param view the owner XWalkView instance.
     * @param url the url for the resource done for loading.
     * @since 1.0
     */
    public void onLoadFinished(XWalkView view, String url) {
        ReflectionHelper.invokeMethod(onLoadFinishedXWalkViewInternalStringMethod, bridge, view.getBridge(), url);
    }

    private Method onProgressChangedXWalkViewInternalintMethod;


    /**
     * Notify the client the progress info of loading a specific url.
     * @param view the owner XWalkView instance.
     * @param progressInPercent the loading process in percent.
     * @since 1.0
     */
    public void onProgressChanged(XWalkView view, int progressInPercent) {
        ReflectionHelper.invokeMethod(onProgressChangedXWalkViewInternalintMethod, bridge, view.getBridge(), progressInPercent);
    }

    private Method shouldInterceptLoadRequestXWalkViewInternalStringMethod;


    /**
     * Notify the client of a resource request and allow the client to return
     * the data.  If the return value is null, the XWalkView
     * will continue to load the resource as usual.  Otherwise, the return
     * response and data will be used.  NOTE: This method is called by the
     * network thread so clients should exercise caution when accessing private
     * data.
     * @param view The owner XWalkView instance that is requesting the
     *             resource.
     * @param url The raw url of the resource.
     * @return A {@link android.webkit.WebResourceResponse} containing the
     *         response information or null if the XWalkView should load the
     *         resource itself.
     * @since 1.0
     */
    public WebResourceResponse shouldInterceptLoadRequest(XWalkView view, String url) {
        return (WebResourceResponse)ReflectionHelper.invokeMethod(shouldInterceptLoadRequestXWalkViewInternalStringMethod, bridge, view.getBridge(), url);
    }

    private Method onReceivedLoadErrorXWalkViewInternalintStringStringMethod;


    /**
     * Report an error to the client.
     * @param view the owner XWalkView instance.
     * @param errorCode the error id.
     * @param description A String describing the error.
     * @param failingUrl The url that failed to load.
     * @since 1.0
     */
    public void onReceivedLoadError(XWalkView view, int errorCode, String description, String failingUrl) {
        ReflectionHelper.invokeMethod(onReceivedLoadErrorXWalkViewInternalintStringStringMethod, bridge, view.getBridge(), errorCode, description, failingUrl);
    }

    private Method shouldOverrideUrlLoadingXWalkViewInternalStringMethod;


    /**
     * Give the host application a chance to take over the control when a new
     * url is about to be loaded in the current XWalkView. If XWalkClient is not
     * provided, by default XWalkView will ask Activity Manager to choose the
     * proper handler for the url. If XWalkClient is provided, return true
     * means the host application handles the url, while return false means the
     * current XWalkView handles the url.
     *
     * @param view The XWalkView that is initiating the callback.
     * @param url The url to be loaded.
     * @return True if the host application wants to leave the current XWalkView
     *         and handle the url itself, otherwise return false.
     *
     * @since 2.1
     */
    public boolean shouldOverrideUrlLoading(XWalkView view, String url) {
        return (Boolean)ReflectionHelper.invokeMethod(shouldOverrideUrlLoadingXWalkViewInternalStringMethod, bridge, view.getBridge(), url);
    }

    private Method onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod;


    /**
      * Notify the host application that an SSL error occurred while loading a
      * resource. The host application must call either callback.onReceiveValue(true)
      * or callback.onReceiveValue(false) . Note that the decision may be retained for
      * use in response to future SSL errors. The default behavior is to pop up a dialog
      * @param view the xwalkview that is initiating the callback
      * @param callback passing 'true' means accepting the ssl error and continue to load.
      *                 passing 'false' means forbidding to load the web page.
      * @param error the SSL error object
      * @since 4.0
      */
    public void onReceivedSslError(XWalkView view, ValueCallback<Boolean> callback, SslError error) {
        ReflectionHelper.invokeMethod(onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod, bridge, view.getBridge(), callback, error);
    }



    private void reflectionInit() throws NoSuchMethodException,
            ClassNotFoundException {
        Class<?> clazz = bridge.getClass();
        onLoadStartedXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "onLoadStartedSuper", "org.xwalk.core.internal.XWalkViewBridge", String.class);
        onLoadFinishedXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "onLoadFinishedSuper", "org.xwalk.core.internal.XWalkViewBridge", String.class);
        onProgressChangedXWalkViewInternalintMethod = ReflectionHelper.loadMethod(clazz, "onProgressChangedSuper", "org.xwalk.core.internal.XWalkViewBridge", int.class);
        shouldInterceptLoadRequestXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "shouldInterceptLoadRequestSuper", "org.xwalk.core.internal.XWalkViewBridge", String.class);
        onReceivedLoadErrorXWalkViewInternalintStringStringMethod = ReflectionHelper.loadMethod(clazz, "onReceivedLoadErrorSuper", "org.xwalk.core.internal.XWalkViewBridge", int.class, String.class, String.class);
        shouldOverrideUrlLoadingXWalkViewInternalStringMethod = ReflectionHelper.loadMethod(clazz, "shouldOverrideUrlLoadingSuper", "org.xwalk.core.internal.XWalkViewBridge", String.class);
        onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod = ReflectionHelper.loadMethod(clazz, "onReceivedSslErrorSuper", "org.xwalk.core.internal.XWalkViewBridge", ValueCallback.class, SslError.class);

    }


    static {
        ReflectionHelper.registerConstructor("XWalkResourceClientInternalXWalkViewInternalConstructor", "org.xwalk.core.internal.XWalkResourceClientBridge", "org.xwalk.core.internal.XWalkViewBridge", Object.class);

    }

}
