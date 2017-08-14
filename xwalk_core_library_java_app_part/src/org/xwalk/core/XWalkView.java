package org.xwalk.core;

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




/**
 * <p>XWalkView represents an Android view for web apps/pages. Thus most of attributes
 * for Android view are valid for this class. Since it internally uses
 * <a href="http://developer.android.com/reference/android/view/SurfaceView.html">
 * android.view.SurfaceView</a> for rendering web pages by default, it can't be resized,
 * rotated, transformed and animated due to the limitations of SurfaceView.
 * Alternatively, if the preference key {@link XWalkPreferences#ANIMATABLE_XWALK_VIEW}
 * is set to True, XWalkView can be transformed and animated because
 * <a href="http://developer.android.com/reference/android/view/TextureView.html">
 * TextureView</a> is intentionally used to render web pages for animation support.
 * Besides, XWalkView won't be rendered if it's invisible.</p>
 *
 * <p>XWalkView needs hardware acceleration to render web pages. As a result, the
 * AndroidManifest.xml of the caller's app must be appended with the attribute
 * "android:hardwareAccelerated" and its value must be set as "true".</p>
 * <pre>
 * &lt;application android:name="android.app.Application" android:label="XWalkUsers"
 *     android:hardwareAccelerated="true"&gt;
 * </pre>
 *
 * <p>Crosswalk provides 2 major callback classes, namely {@link XWalkResourceClient} and
 * {@link XWalkUIClient} for listening to the events related to resource loading and UI.
 * By default, Crosswalk has a default implementation. Callers can override them if needed.</p>
 *
 * <p>Unlike other Android views, this class has to listen to system events like intents and activity result.
 * The web engine inside this view need to get and handle them.
 * With contianer activity's lifecycle change, XWalkView will pause all timers and other
 * components like videos when activity paused, resume back them when activity resumed.
 * When activity is about to destroy, XWalkView will destroy itself as well.
 * Embedders can also call onHide() and pauseTimers() to explicitly pause XWalkView.
 * Similarily with onShow(), resumeTimers() and onDestroy().
 *
 * For example:</p>
 *
 * <pre>
 *   import android.app.Activity;
 *   import android.os.Bundle;
 *
 *   import org.xwalk.core.internal.XWalkResourceClient;
 *   import org.xwalk.core.internal.XWalkUIClient;
 *   import org.xwalk.core.internal.XWalkView;
 *
 *   public class MyActivity extends Activity {
 *       XWalkView mXwalkView;
 *
 *       class MyResourceClient extends XWalkResourceClient {
 *           MyResourceClient(XWalkView view) {
 *               super(view);
 *           }
 *
 *           &#64;Override
 *           WebResourceResponse shouldInterceptLoadRequest(XWalkView view, String url) {
 *               // Handle it here.
 *               ...
 *           }
 *       }
 *
 *       class MyUIClient extends XWalkUIClient {
 *           MyUIClient(XWalkView view) {
 *               super(view);
 *           }
 *
 *           &#64;Override
 *           void onFullscreenToggled(XWalkView view, String url) {
 *               // Handle it here.
 *               ...
 *           }
 *       }
 *
 *       &#64;Override
 *       protected void onCreate(Bundle savedInstanceState) {
 *           mXwalkView = new XWalkView(this, null);
 *           setContentView(mXwalkView);
 *           mXwalkView.setResourceClient(new MyResourceClient(mXwalkView));
 *           mXwalkView.setUIClient(new MyUIClient(mXwalkView));
 *           mXwalkView.load("http://www.crosswalk-project.org", null);
 *       }
 *
 *       &#64;Override
 *       protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 *           if (mXwalkView != null) {
 *               mXwalkView.onActivityResult(requestCode, resultCode, data);
 *           }
 *       }
 *
 *       &#64;Override
 *       protected void onNewIntent(Intent intent) {
 *           if (mXwalkView != null) {
 *               mXwalkView.onNewIntent(intent);
 *           }
 *       }
 *   }
 * </pre>
 */
public class XWalkView  extends FrameLayout  {



    /**
     * Normal reload mode as default.
     * @since 1.0
     */
    public final static int RELOAD_NORMAL = 0;

    /**
     * Reload mode with bypassing the cache.
     * @since 1.0
     */
    public final static int RELOAD_IGNORE_CACHE = 1;




    private final static String BRIDGE_CLASS = "org.xwalk.core.internal.XWalkViewBridge";
    private Object bridge;

    Object getBridge() {
        return bridge;
    }



    /**
     * Constructor for inflating via XML.
     * @param context  a Context object used to access application assets.
     * @param attrs    an AttributeSet passed to our parent.
     * @since 1.0
     */
    public XWalkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bridge = ReflectionHelper.createInstance("XWalkViewInternalContextAttributeSetConstructor", context, attrs, this);
        try {
            reflectionInit();
        } catch(Exception e) {
            ReflectionHelper.handleException(e);
        }
        if (bridge == null) return;
        addView((FrameLayout)bridge, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
    }



    /**
     * Constructor for Crosswalk runtime. In shared mode, context isi
     * different from activity. In embedded mode, they're same.
     * @param context  a Context object used to access application assets
     * @param activity the activity for this XWalkView.
     * @since 1.0
     */
    public XWalkView(Context context, Activity activity) {
        super(context, null);
        bridge = ReflectionHelper.createInstance("XWalkViewInternalContextActivityConstructor", context, activity, this);
        try {
            reflectionInit();
        } catch(Exception e) {
            ReflectionHelper.handleException(e);
        }
        if (bridge == null) return;
        addView((FrameLayout)bridge, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
    }

    private Method loadStringStringMethod;


    /**
     * Load a web page/app from a given base URL or a content.
     * If url is null or empty and content is null or empty, then this function
     * will do nothing.
     * If content is not null, load the web page/app from the content.
     * If content is not null and the url is not set, return "about:blank" ifi
     * calling {@link XWalkView#getUrl()}.
     * If content is null, try to load the content from the url.
     *
     * It supports URL schemes like 'http:', 'https:' and 'file:'.
     * It can also load files from Android assets, e.g. 'file:///android_asset/'.
     * @param url the url for web page/app.
     * @param content the content for the web page/app. Could be empty.
     * @since 1.0
     */
    public void load(String url, String content) {
        ReflectionHelper.invokeMethod(loadStringStringMethod, bridge, url, content);
    }

    private Method loadAppFromManifestStringStringMethod;


    /**
     * Load a web app from a given manifest.json file. If content is not null,
     * load the manifest.json from the content. If content is null, try to load
     * the manifest.json from the url. Note that url should not be null if the
     * launched path defined in manifest.json is relative.
     *
     * It supports URL schemes like 'http:', 'https:' and 'file:'.
     * It can also load files from Android assets, e.g. 'file:///android_asset/'.
     * @param url the url for manifest.json.
     * @param content the content for manifest.json.
     * @since 1.0
     */
    public void loadAppFromManifest(String url, String content) {
        ReflectionHelper.invokeMethod(loadAppFromManifestStringStringMethod, bridge, url, content);
    }

    private Method reloadintMethod;


    /**
     * Reload a web app with a given mode.
     * @param mode the reload mode.
     * @since 1.0
     */
    public void reload(int mode) {
        ReflectionHelper.invokeMethod(reloadintMethod, bridge, mode);
    }

    private Method stopLoadingMethod;


    /**
     * Stop current loading progress.
     * @since 1.0
     */
    public void stopLoading() {
        ReflectionHelper.invokeMethod(stopLoadingMethod, bridge);
    }

    private Method getUrlMethod;


    /**
     * Get the url of current web page/app. This may be different from what's passed
     * by caller.
     * @return the url for current web page/app.
     * @since 1.0
     */
    public String getUrl() {
        return (String)ReflectionHelper.invokeMethod(getUrlMethod, bridge);
    }

    private Method getTitleMethod;


    /**
     * Get the title of current web page/app. This may be different from what's passed
     * by caller.
     * @return the title for current web page/app.
     * @since 1.0
     */
    public String getTitle() {
        return (String)ReflectionHelper.invokeMethod(getTitleMethod, bridge);
    }

    private Method getOriginalUrlMethod;


    /**
     * Get the original url specified by caller.
     * @return the original url.
     * @since 1.0
     */
    public String getOriginalUrl() {
        return (String)ReflectionHelper.invokeMethod(getOriginalUrlMethod, bridge);
    }

    private Method getNavigationHistoryMethod;


    /**
     * Get the navigation history for current XWalkView. It's synchronized with
     * this XWalkView if any backward/forward and navigation operations.
     * @return the navigation history.
     * @since 1.0
     */
    public XWalkNavigationHistory getNavigationHistory() {
        return (XWalkNavigationHistory)ReflectionHelper.getBridgeOrWrapper(
            ReflectionHelper.invokeMethod(getNavigationHistoryMethod, bridge));
    }

    private Method addJavascriptInterfaceObjectStringMethod;


    /**
     * Injects the supplied Java object into this XWalkView.
     * Each method defined in the class of the object should be
     * marked with {@link JavascriptInterface} if it's called by JavaScript.
     * @param object the supplied Java object, called by JavaScript.
     * @param name the name injected in JavaScript.
     * @since 1.0
     */
    public void addJavascriptInterface(Object object, String name) {
        ReflectionHelper.invokeMethod(addJavascriptInterfaceObjectStringMethod, bridge, object, name);
    }

    private Method evaluateJavascriptStringValueCallbackMethod;


    /**
     * Evaluate a fragment of JavaScript code and get the result via callback.
     * @param script the JavaScript string.
     * @param callback the callback to handle the evaluated result.
     * @since 1.0
     */
    public void evaluateJavascript(String script, ValueCallback<String> callback) {
        ReflectionHelper.invokeMethod(evaluateJavascriptStringValueCallbackMethod, bridge, script, callback);
    }

    private Method clearCachebooleanMethod;


    /**
     * Clear the resource cache. Note that the cache is per-application, so this
     * will clear the cache for all XWalkViews used.
     * @param includeDiskFiles indicate whether to clear disk files for cache.
     * @since 1.0
     */
    public void clearCache(boolean includeDiskFiles) {
        ReflectionHelper.invokeMethod(clearCachebooleanMethod, bridge, includeDiskFiles);
    }

    private Method hasEnteredFullscreenMethod;


    /**
     * Indicate that a HTML element is occupying the whole screen.
     * @return true if any HTML element is occupying the whole screen.
     * @since 1.0
     */
    public boolean hasEnteredFullscreen() {
        return (Boolean)ReflectionHelper.invokeMethod(hasEnteredFullscreenMethod, bridge);
    }

    private Method leaveFullscreenMethod;


    /**
     * Leave fullscreen mode if it's. Do nothing if it's not
     * in fullscreen.
     * @since 1.0
     */
    public void leaveFullscreen() {
        ReflectionHelper.invokeMethod(leaveFullscreenMethod, bridge);
    }

    private Method pauseTimersMethod;


    /**
     * Pause all layout, parsing and JavaScript timers for all XWalkView instances.
     * It will be called when the container Activity get paused. It can also be explicitly
     * called to pause timers.
     *
     * Note that it will globally impact all XWalkView instances, not limited to
     * just this XWalkView.
     *
     * @since 1.0
     */
    public void pauseTimers() {
        ReflectionHelper.invokeMethod(pauseTimersMethod, bridge);
    }

    private Method resumeTimersMethod;


    /**
     * Resume all layout, parsing and JavaScript timers for all XWalkView instances.
     * It will be called when the container Activity get resumed. It can also be explicitly
     * called to resume timers.
     *
     * Note that it will globally impact all XWalkView instances, not limited to
     * just this XWalkView.
     *
     * @since 1.0
     */
    public void resumeTimers() {
        ReflectionHelper.invokeMethod(resumeTimersMethod, bridge);
    }

    private Method onHideMethod;


    /**
     * Pause many other things except JavaScript timers inside rendering engine,
     * like video player, modal dialogs, etc. See {@link #pauseTimers} about pausing
     * JavaScript timers.
     * It will be called when the container Activity get paused. It can also be explicitly
     * called to pause above things.
     * @since 1.0
     */
    public void onHide() {
        ReflectionHelper.invokeMethod(onHideMethod, bridge);
    }

    private Method onShowMethod;


    /**
     * Resume video player, modal dialogs. Embedders are in charge of calling
     * this during resuming this activity if they call onHide.
     * Typically it should be called when the activity for this view is resumed.
     * It will be called when the container Activity get resumed. It can also be explicitly
     * called to resume above things.
     * @since 1.0
     */
    public void onShow() {
        ReflectionHelper.invokeMethod(onShowMethod, bridge);
    }

    private Method onDestroyMethod;


    /**
     * Release internal resources occupied by this XWalkView.
     * It will be called when the container Activity get destroyed. It can also be explicitly
     * called to release resources.
     * @since 1.0
     */
    public void onDestroy() {
        ReflectionHelper.invokeMethod(onDestroyMethod, bridge);
    }

    private Method onActivityResultintintIntentMethod;


    /**
     * Pass through activity result to XWalkView. Many internal facilities need this
     * to handle activity result like JavaScript dialog, Crosswalk extensions, etc.
     * See <a href="http://developer.android.com/reference/android/app/Activity.html">
     * android.app.Activity.onActivityResult()</a>.
     * @param requestCode passed from android.app.Activity.onActivityResult().
     * @param resultCode passed from android.app.Activity.onActivityResult().
     * @param data passed from android.app.Activity.onActivityResult().
     * @since 1.0
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ReflectionHelper.invokeMethod(onActivityResultintintIntentMethod, bridge, requestCode, resultCode, data);
    }

    private Method onNewIntentIntentMethod;


    /**
     * Pass through intents to XWalkView. Many internal facilities need this
     * to receive the intents like web notification. See
     * <a href="http://developer.android.com/reference/android/app/Activity.html">
     * android.app.Activity.onNewIntent()</a>.
     * @param intent passed from android.app.Activity.onNewIntent().
     * @since 1.0
     */
    public boolean onNewIntent(Intent intent) {
        return (Boolean)ReflectionHelper.invokeMethod(onNewIntentIntentMethod, bridge, intent);
    }

    private Method saveStateBundleMethod;


    /**
     * Save current internal state of this XWalkView. This can help restore this state
     * afterwards restoring.
     * @param outState the saved state for restoring.
     * @since 1.0
     */
    public boolean saveState(Bundle outState) {
        return (Boolean)ReflectionHelper.invokeMethod(saveStateBundleMethod, bridge, outState);
    }

    private Method restoreStateBundleMethod;


    /**
     * Restore the state from the saved bundle data.
     * @param inState the state saved from saveState().
     * @return true if it can restore the state.
     * @since 1.0
     */
    public boolean restoreState(Bundle inState) {
        return (Boolean)ReflectionHelper.invokeMethod(restoreStateBundleMethod, bridge, inState);
    }

    private Method getAPIVersionMethod;

    public String getAPIVersion() {
        return (String)ReflectionHelper.invokeMethod(getAPIVersionMethod, bridge);
    }

    private Method getXWalkVersionMethod;

    public String getXWalkVersion() {
        return (String)ReflectionHelper.invokeMethod(getXWalkVersionMethod, bridge);
    }

    private Method setUIClientXWalkUIClientInternalMethod;


    /**
     * Embedders use this to customize their handlers to events/callbacks related
     * to UI.
     * @param client the XWalkUIClient defined by callers.
     * @since 1.0
     */
    public void setUIClient(XWalkUIClient client) {
        ReflectionHelper.invokeMethod(setUIClientXWalkUIClientInternalMethod, bridge, client.getBridge());
    }

    private Method setResourceClientXWalkResourceClientInternalMethod;


    /**
     * Embedders use this to customize their handlers to events/callbacks related
     * to resource loading.
     * @param client the XWalkResourceClient defined by callers.
     * @since 1.0
     */
    public void setResourceClient(XWalkResourceClient client) {
        ReflectionHelper.invokeMethod(setResourceClientXWalkResourceClientInternalMethod, bridge, client.getBridge());
    }

    private Method setBackgroundColorintMethod;

    public void setBackgroundColor(int color) {
        ReflectionHelper.invokeMethod(setBackgroundColorintMethod, bridge, color);
    }

    private Method setLayerTypeintPaintMethod;

    public void setLayerType(int layerType, Paint paint) {
        ReflectionHelper.invokeMethod(setLayerTypeintPaintMethod, bridge, layerType, paint);
    }

    private Method setNetworkAvailablebooleanMethod;


    /**
     * This method is used by Cordova for hacking.
     * TODO(yongsheng): remove this and related test cases?
     *
     * @hide
     */
    public void setNetworkAvailable(boolean networkUp) {
        ReflectionHelper.invokeMethod(setNetworkAvailablebooleanMethod, bridge, networkUp);
    }

    private Method getRemoteDebuggingUrlMethod;


    /**
     * Get the websocket url for remote debugging.
     * @return the web socket url to remote debug this xwalk view.
     * null will be returned if remote debugging is not enabled.
     * @since 4.1
     */
    public Uri getRemoteDebuggingUrl() {
        return (Uri)ReflectionHelper.invokeMethod(getRemoteDebuggingUrlMethod, bridge);
    }



    private void reflectionInit() throws NoSuchMethodException,
            ClassNotFoundException {
        Class<?> clazz = bridge.getClass();
        loadStringStringMethod = ReflectionHelper.loadMethod(clazz, "loadSuper", String.class, String.class);
        loadAppFromManifestStringStringMethod = ReflectionHelper.loadMethod(clazz, "loadAppFromManifestSuper", String.class, String.class);
        reloadintMethod = ReflectionHelper.loadMethod(clazz, "reloadSuper", int.class);
        stopLoadingMethod = ReflectionHelper.loadMethod(clazz, "stopLoadingSuper");
        getUrlMethod = ReflectionHelper.loadMethod(clazz, "getUrlSuper");
        getTitleMethod = ReflectionHelper.loadMethod(clazz, "getTitleSuper");
        getOriginalUrlMethod = ReflectionHelper.loadMethod(clazz, "getOriginalUrlSuper");
        getNavigationHistoryMethod = ReflectionHelper.loadMethod(clazz, "getNavigationHistorySuper");
        addJavascriptInterfaceObjectStringMethod = ReflectionHelper.loadMethod(clazz, "addJavascriptInterfaceSuper", Object.class, String.class);
        evaluateJavascriptStringValueCallbackMethod = ReflectionHelper.loadMethod(clazz, "evaluateJavascriptSuper", String.class, ValueCallback.class);
        clearCachebooleanMethod = ReflectionHelper.loadMethod(clazz, "clearCacheSuper", boolean.class);
        hasEnteredFullscreenMethod = ReflectionHelper.loadMethod(clazz, "hasEnteredFullscreenSuper");
        leaveFullscreenMethod = ReflectionHelper.loadMethod(clazz, "leaveFullscreenSuper");
        pauseTimersMethod = ReflectionHelper.loadMethod(clazz, "pauseTimersSuper");
        resumeTimersMethod = ReflectionHelper.loadMethod(clazz, "resumeTimersSuper");
        onHideMethod = ReflectionHelper.loadMethod(clazz, "onHideSuper");
        onShowMethod = ReflectionHelper.loadMethod(clazz, "onShowSuper");
        onDestroyMethod = ReflectionHelper.loadMethod(clazz, "onDestroySuper");
        onActivityResultintintIntentMethod = ReflectionHelper.loadMethod(clazz, "onActivityResultSuper", int.class, int.class, Intent.class);
        onNewIntentIntentMethod = ReflectionHelper.loadMethod(clazz, "onNewIntentSuper", Intent.class);
        saveStateBundleMethod = ReflectionHelper.loadMethod(clazz, "saveStateSuper", Bundle.class);
        restoreStateBundleMethod = ReflectionHelper.loadMethod(clazz, "restoreStateSuper", Bundle.class);
        getAPIVersionMethod = ReflectionHelper.loadMethod(clazz, "getAPIVersionSuper");
        getXWalkVersionMethod = ReflectionHelper.loadMethod(clazz, "getXWalkVersionSuper");
        setUIClientXWalkUIClientInternalMethod = ReflectionHelper.loadMethod(clazz, "setUIClientSuper", "org.xwalk.core.internal.XWalkUIClientBridge");
        setResourceClientXWalkResourceClientInternalMethod = ReflectionHelper.loadMethod(clazz, "setResourceClientSuper", "org.xwalk.core.internal.XWalkResourceClientBridge");
        setBackgroundColorintMethod = ReflectionHelper.loadMethod(clazz, "setBackgroundColorSuper", int.class);
        setLayerTypeintPaintMethod = ReflectionHelper.loadMethod(clazz, "setLayerTypeSuper", int.class, Paint.class);
        setNetworkAvailablebooleanMethod = ReflectionHelper.loadMethod(clazz, "setNetworkAvailableSuper", boolean.class);
        getRemoteDebuggingUrlMethod = ReflectionHelper.loadMethod(clazz, "getRemoteDebuggingUrlSuper");

    }


    static {
        ReflectionHelper.registerConstructor("XWalkViewInternalContextAttributeSetConstructor", "org.xwalk.core.internal.XWalkViewBridge", Context.class, AttributeSet.class, Object.class);
        ReflectionHelper.registerConstructor("XWalkViewInternalContextActivityConstructor", "org.xwalk.core.internal.XWalkViewBridge", Context.class, Activity.class, Object.class);

    }

}
