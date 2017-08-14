package org.xwalk.core.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class XWalkPreferencesBridge extends XWalkPreferencesInternal {
    private final static String WRAPPER_CLASS = "org.xwalk.core.Object";
    private Object wrapper;

    public Object getWrapper() {
        return wrapper;
    }


    public static void setValue(String key, boolean enabled) {
        XWalkPreferencesInternal.setValue(key, enabled);
    }

    public static void setValue(String key, int value) {
        XWalkPreferencesInternal.setValue(key, value);
    }

    public static void setValue(String key, String value) {
        XWalkPreferencesInternal.setValue(key, value);
    }

    public static boolean getValue(String key) {
        return XWalkPreferencesInternal.getValue(key);
    }

    public static boolean getBooleanValue(String key) {
        return XWalkPreferencesInternal.getBooleanValue(key);
    }

    public static int getIntegerValue(String key) {
        return XWalkPreferencesInternal.getIntegerValue(key);
    }

    public static String getStringValue(String key) {
        return XWalkPreferencesInternal.getStringValue(key);
    }



    private void reflectionInit() throws NoSuchMethodException,
            ClassNotFoundException {
        Class<?> clazz = wrapper.getClass();

    }


}
