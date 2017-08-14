package org.xwalk.core;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;




/**
 * This class represents a navigation item and is managed in XWalkNavigationHistory.
 */
public class XWalkNavigationItem  {





    private final static String BRIDGE_CLASS = "org.xwalk.core.internal.XWalkNavigationItemBridge";
    private Object bridge;

    Object getBridge() {
        return bridge;
    }
    public XWalkNavigationItem(Object bridge) {
        this.bridge = bridge;
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    private Method getUrlMethod;


    /**
     * Get the url of current navigation item.
     * @return the string of the url.
     * @since 1.0
     */
    public String getUrl() {
        return (String)ReflectionHelper.invokeMethod(getUrlMethod, bridge);
    }

    private Method getOriginalUrlMethod;


    /**
     * Get the original url of current navigation item.
     * @return the string of the original url.
     * @since 1.0
     */
    public String getOriginalUrl() {
        return (String)ReflectionHelper.invokeMethod(getOriginalUrlMethod, bridge);
    }

    private Method getTitleMethod;


    /**
     * Get the title of current navigation item.
     * @return the string of the title.
     * @since 1.0
     */
    public String getTitle() {
        return (String)ReflectionHelper.invokeMethod(getTitleMethod, bridge);
    }



    private void reflectionInit() throws NoSuchMethodException,
            ClassNotFoundException {
        Class<?> clazz = bridge.getClass();
        getUrlMethod = ReflectionHelper.loadMethod(clazz, "getUrlSuper");
        getOriginalUrlMethod = ReflectionHelper.loadMethod(clazz, "getOriginalUrlSuper");
        getTitleMethod = ReflectionHelper.loadMethod(clazz, "getTitleSuper");

    }



}
