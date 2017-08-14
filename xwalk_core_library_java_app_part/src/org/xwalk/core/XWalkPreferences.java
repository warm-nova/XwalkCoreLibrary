package org.xwalk.core;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;




/**
 * This class represents the preferences and could be set by callers.
 * It is not thread-safe and must be called on the UI thread.
 * Afterwards, the preference could be read from all threads and can impact
 * all XWalkView instances.
 */
public class XWalkPreferences  {



    /**
     * The key string to enable/disable remote debugging.
     * @since 1.0
     */
    public final static String REMOTE_DEBUGGING = "remote-debugging";


    /**
     * The key string to enable/disable animatable XWalkView. Default value is
     * true.
     *
     * If this key is set to False, then SurfaceView will be created internally as the
     * rendering backend.
     * If this key is set to True, the XWalkView created by Crosswalk can be
     * transformed and animated. Internally, Crosswalk is alternatively using
     * TextureView as the backend of XWalkView.
     *
     * <a href="http://developer.android.com/reference/android/view/TextureView.html">
     * TextureView</a> is a kind of
     * <a href="http://developer.android.com/reference/android/view/View.html">
     * android.view.View</a> that is different from
     * <a href="http://developer.android.com/reference/android/view/SurfaceView.html">
     * SurfaceView</a>. Unlike SurfaceView, it can be resized, transformed and
     * animated. Once this key is set to True, all XWalkView will use TextureView
     * as the rendering target instead of SurfaceView. The downside of TextureView
     * is, it would consume more graphics memory than SurfaceView and may have
     * 1~3 extra frames of latency to display updates.
     *
     * Note this key MUST be set before creating the first XWalkView, otherwise
     * a RuntimeException will be thrown.
     *
     * @since 2.0
     */
    public final static String ANIMATABLE_XWALK_VIEW = "animatable-xwalk-view";


    /**
     * The key string to allow/disallow javascript to open
     * window automatically.
     * @since 3.0
     */
    public final static String JAVASCRIPT_CAN_OPEN_WINDOW = "javascript-can-open-window";


    /**
     * The key string to allow/disallow having universal access
     * from file origin.
     * @since 3.0
     */
    public final static String ALLOW_UNIVERSAL_ACCESS_FROM_FILE = "allow-universal-access-from-file";


    /**
     * The key string to enable/disable multiple windows.
     * @since 3.0
     */
    public final static String SUPPORT_MULTIPLE_WINDOWS = "support-multiple-windows";


    /**
     * The key string to set xwalk profile name.
     * User data will be kept separated for different profiles.
     * Profile needs to be set before any XWalkView instance created.
     * @since 3.0
     */
    public final static String PROFILE_NAME = "profile-name";




    private final static String BRIDGE_CLASS = "org.xwalk.core.internal.XWalkPreferencesBridge";
    private Object bridge;

    Object getBridge() {
        return bridge;
    }



    /**
     * Set a boolean preference value into Crosswalk. An exception will be thrown if
     * the key for the preference is not valid.
     * @param key the string name of the key.
     * @param enabled true if setting it as enabled.
     * @since 1.0
     */
    public static void setValue(String key, boolean enabled) {
       Class<?> clazz = ReflectionHelper.loadClass("org.xwalk.core.internal.XWalkPreferencesBridge");
       Method method = ReflectionHelper.loadMethod(clazz, "setValue", String.class, boolean.class);
       ReflectionHelper.invokeMethod(method, null, key, enabled);
    }



    /**
     * Set an integer preference value into Crosswalk. An exception will be thrown if
     * the key for the preference is not valid.
     * @param key the string name of the key.
     * @param value the integer value.
     * @since 3.0
     */
    public static void setValue(String key, int value) {
       Class<?> clazz = ReflectionHelper.loadClass("org.xwalk.core.internal.XWalkPreferencesBridge");
       Method method = ReflectionHelper.loadMethod(clazz, "setValue", String.class, int.class);
       ReflectionHelper.invokeMethod(method, null, key, value);
    }



    /**
     * Set a string preference value into Crosswalk. An exception will be thrown if
     * the key for the preference is not valid.
     * @param key the string name of the key.
     * @param value the string value.
     * @since 3.0
     */
    public static void setValue(String key, String value) {
       Class<?> clazz = ReflectionHelper.loadClass("org.xwalk.core.internal.XWalkPreferencesBridge");
       Method method = ReflectionHelper.loadMethod(clazz, "setValue", String.class, String.class);
       ReflectionHelper.invokeMethod(method, null, key, value);
    }



    /**
     * Get a boolean preference value from Crosswalk. An exception will be thrown if
     * the key for the preference is not valid.
     * @param key the string name of the key.
     * @return true if it's enabled.
     * @since 1.0
     * @deprecated
     */
    public static boolean getValue(String key) {
       Class<?> clazz = ReflectionHelper.loadClass("org.xwalk.core.internal.XWalkPreferencesBridge");
       Method method = ReflectionHelper.loadMethod(clazz, "getValue", String.class);
       return (Boolean)ReflectionHelper.invokeMethod(method, null, key);
    }



    /**
     * Get a boolean preference value from Crosswalk. An exception will be thrown if
     * the key for the preference is not valid.
     * @param key the string name of the key.
     * @return true if it's enabled.
     * @since 3.0
     */
    public static boolean getBooleanValue(String key) {
       Class<?> clazz = ReflectionHelper.loadClass("org.xwalk.core.internal.XWalkPreferencesBridge");
       Method method = ReflectionHelper.loadMethod(clazz, "getBooleanValue", String.class);
       return (Boolean)ReflectionHelper.invokeMethod(method, null, key);
    }



    /**
     * Get a int preference value from Crosswalk. An exception will be thrown if
     * the key for the preference is not valid.
     * @param key the string name of the key.
     * @return the integer value.
     * @since 3.0
     */
    public static int getIntegerValue(String key) {
       Class<?> clazz = ReflectionHelper.loadClass("org.xwalk.core.internal.XWalkPreferencesBridge");
       Method method = ReflectionHelper.loadMethod(clazz, "getIntegerValue", String.class);
       return (Integer)ReflectionHelper.invokeMethod(method, null, key);
    }



    /**
     * Get a string preference value from Crosswalk. An exception will be thrown if
     * the key for the preference is not valid.
     * @param key the string name of the key.
     * @return the string value.
     * @since 3.0
     */
    public static String getStringValue(String key) {
       Class<?> clazz = ReflectionHelper.loadClass("org.xwalk.core.internal.XWalkPreferencesBridge");
       Method method = ReflectionHelper.loadMethod(clazz, "getStringValue", String.class);
       return (String)ReflectionHelper.invokeMethod(method, null, key);
    }






}
