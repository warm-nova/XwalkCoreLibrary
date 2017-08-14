package org.xwalk.core.internal;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class XWalkNavigationItemBridge extends XWalkNavigationItemInternal {
    private final static String WRAPPER_CLASS = "org.xwalk.core.Object";
    private Object wrapper;

    public Object getWrapper() {
        return wrapper;
    }
    private XWalkNavigationItemInternal internal = null;
    XWalkNavigationItemBridge(XWalkNavigationItemInternal internal) {
        this.internal = internal;
        this.wrapper = ReflectionHelper.createInstance("XWalkNavigationItemBridgeConstructor", this);
        try {
          reflectionInit();
        } catch (Exception e) {
          ReflectionHelper.handleException(e);
        }
    }


    private Method getUrlMethod;
    @Override
    public String getUrl() {
        return (String)ReflectionHelper.invokeMethod(
            getUrlMethod, wrapper);
    }

    public String getUrlSuper() {
        String ret;
        if (internal == null) {
            ret = super.getUrl();
        } else {
            ret = internal.getUrl();
        }
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
        if (internal == null) {
            ret = super.getOriginalUrl();
        } else {
            ret = internal.getOriginalUrl();
        }
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
        if (internal == null) {
            ret = super.getTitle();
        } else {
            ret = internal.getTitle();
        }
        if (ret == null) return null;
        return ret;
    }



    private void reflectionInit() throws NoSuchMethodException,
            ClassNotFoundException {
        Class<?> clazz = wrapper.getClass();
        getUrlMethod = ReflectionHelper.loadMethod(clazz, "getUrl");
        getOriginalUrlMethod = ReflectionHelper.loadMethod(clazz, "getOriginalUrl");
        getTitleMethod = ReflectionHelper.loadMethod(clazz, "getTitle");

    }

    static {
        ReflectionHelper.registerConstructor("XWalkNavigationItemBridgeConstructor", "org.xwalk.core.XWalkNavigationItem", Object.class);
    }

}
