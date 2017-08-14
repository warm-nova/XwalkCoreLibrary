package org.xwalk.core;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;




/**
 * This class represents a navigation history for a XWalkView instance.
 * It's not thread-safe and should be only called on UI thread.
 */
public class XWalkNavigationHistory  {





    /**
     * The direction for web page navigation.
     * @since 1.0
     */
    public enum Direction {
        /** The backward direction for web page navigation. */
        BACKWARD,
        /** The forward direction for web page navigation. */
        FORWARD
    }
    private Class<?> enumDirectionClass;
    private Method enumDirectionClassValueOfMethod;
    private Object ConvertDirection(Direction type) {
        return ReflectionHelper.invokeMethod(enumDirectionClassValueOfMethod, null, type.toString());
    }


    private final static String BRIDGE_CLASS = "org.xwalk.core.internal.XWalkNavigationHistoryBridge";
    private Object bridge;

    Object getBridge() {
        return bridge;
    }
    public XWalkNavigationHistory(Object bridge) {
        this.bridge = bridge;
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    private Method sizeMethod;


    /**
     * Total size of navigation history for the XWalkView.
     * @return the size of total navigation items.
     * @since 1.0
     */
    public int size() {
        return (Integer)ReflectionHelper.invokeMethod(sizeMethod, bridge);
    }

    private Method hasItemAtintMethod;


    /**
     * Test whether there is an item at a specific index.
     * @param index the given index.
     * @return true if there is an item at the specific index.
     * @since 1.0
     */
    public boolean hasItemAt(int index) {
        return (Boolean)ReflectionHelper.invokeMethod(hasItemAtintMethod, bridge, index);
    }

    private Method getItemAtintMethod;


    /**
     * Get a specific item given by index.
     * @param index the given index.
     * @return the navigation item for the given index.
     * @since 1.0
     */
    public XWalkNavigationItem getItemAt(int index) {
        return (XWalkNavigationItem)ReflectionHelper.getBridgeOrWrapper(
            ReflectionHelper.invokeMethod(getItemAtintMethod, bridge, index));
    }

    private Method getCurrentItemMethod;


    /**
     * Get the current item which XWalkView displays.
     * @return the current navigation item.
     * @since 1.0
     */
    public XWalkNavigationItem getCurrentItem() {
        return (XWalkNavigationItem)ReflectionHelper.getBridgeOrWrapper(
            ReflectionHelper.invokeMethod(getCurrentItemMethod, bridge));
    }

    private Method canGoBackMethod;


    /**
     * Test whether XWalkView can go back.
     * @return true if it can go back.
     * @since 1.0
     */
    public boolean canGoBack() {
        return (Boolean)ReflectionHelper.invokeMethod(canGoBackMethod, bridge);
    }

    private Method canGoForwardMethod;


    /**
     * Test whether XWalkView can go forward.
     * @return true if it can go forward.
     * @since 1.0
     */
    public boolean canGoForward() {
        return (Boolean)ReflectionHelper.invokeMethod(canGoForwardMethod, bridge);
    }

    private Method navigateDirectionInternalintMethod;


    /**
     * Navigates to the specified step from the current navigation item.
     * Do nothing if the offset is out of bound.
     * @param direction the direction of navigation.
     * @param steps go back or foward with a given steps.
     * @since 1.0
     */
    public void navigate(Direction direction, int steps) {
        ReflectionHelper.invokeMethod(navigateDirectionInternalintMethod, bridge, ConvertDirection(direction), steps);
    }

    private Method getCurrentIndexMethod;


    /**
     * Get the index for current navigation item.
     * @return current index in the navigation history.
     * @since 1.0
     */
    public int getCurrentIndex() {
        return (Integer)ReflectionHelper.invokeMethod(getCurrentIndexMethod, bridge);
    }

    private Method clearMethod;


    /**
     * Clear all history owned by this XWalkView.
     * @since 1.0
     */
    public void clear() {
        ReflectionHelper.invokeMethod(clearMethod, bridge);
    }



    private void reflectionInit() throws NoSuchMethodException,
            ClassNotFoundException {
        Class<?> clazz = bridge.getClass();
        enumDirectionClass = clazz.getClassLoader().loadClass("org.xwalk.core.internal.XWalkNavigationHistoryInternal$DirectionInternal");
        enumDirectionClassValueOfMethod = enumDirectionClass.getMethod("valueOf", String.class);
        sizeMethod = ReflectionHelper.loadMethod(clazz, "sizeSuper");
        hasItemAtintMethod = ReflectionHelper.loadMethod(clazz, "hasItemAtSuper", int.class);
        getItemAtintMethod = ReflectionHelper.loadMethod(clazz, "getItemAtSuper", int.class);
        getCurrentItemMethod = ReflectionHelper.loadMethod(clazz, "getCurrentItemSuper");
        canGoBackMethod = ReflectionHelper.loadMethod(clazz, "canGoBackSuper");
        canGoForwardMethod = ReflectionHelper.loadMethod(clazz, "canGoForwardSuper");
        navigateDirectionInternalintMethod = ReflectionHelper.loadMethod(clazz, "navigateSuper", enumDirectionClass, int.class);
        getCurrentIndexMethod = ReflectionHelper.loadMethod(clazz, "getCurrentIndexSuper");
        clearMethod = ReflectionHelper.loadMethod(clazz, "clearSuper");

    }



}
