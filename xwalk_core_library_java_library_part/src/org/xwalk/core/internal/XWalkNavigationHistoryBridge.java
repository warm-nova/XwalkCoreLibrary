package org.xwalk.core.internal;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class XWalkNavigationHistoryBridge extends XWalkNavigationHistoryInternal {
    private final static String WRAPPER_CLASS = "org.xwalk.core.Object";
    private Object wrapper;

    public Object getWrapper() {
        return wrapper;
    }
    private XWalkNavigationHistoryInternal internal = null;
    XWalkNavigationHistoryBridge(XWalkNavigationHistoryInternal internal) {
        this.internal = internal;
        this.wrapper = ReflectionHelper.createInstance("XWalkNavigationHistoryBridgeConstructor", this);
        try {
          reflectionInit();
        } catch (Exception e) {
          ReflectionHelper.handleException(e);
        }
    }

    private Class<?> enumDirectionClass;
    private Method enumDirectionClassValueOfMethod;
    private Object ConvertDirectionInternal(DirectionInternal type) {
        return ReflectionHelper.invokeMethod(enumDirectionClassValueOfMethod, null, type.toString());
    }

    private Method sizeMethod;
    @Override
    public int size() {
        return (Integer)ReflectionHelper.invokeMethod(
            sizeMethod, wrapper);
    }

    public int sizeSuper() {
        int ret;
        if (internal == null) {
            ret = super.size();
        } else {
            ret = internal.size();
        }
        
        return ret;
    }

    private Method hasItemAtintMethod;
    @Override
    public boolean hasItemAt(int index) {
        return (Boolean)ReflectionHelper.invokeMethod(
            hasItemAtintMethod, wrapper, index);
    }

    public boolean hasItemAtSuper(int index) {
        boolean ret;
        if (internal == null) {
            ret = super.hasItemAt(index);
        } else {
            ret = internal.hasItemAt(index);
        }
        
        return ret;
    }

    private Method getItemAtintMethod;
    @Override
    public XWalkNavigationItemInternal getItemAt(int index) {
        return (XWalkNavigationItemBridge)ReflectionHelper.getBridgeOrWrapper(ReflectionHelper.invokeMethod(
            getItemAtintMethod, wrapper, index));
    }

    public XWalkNavigationItemBridge getItemAtSuper(int index) {
        XWalkNavigationItemInternal ret;
        if (internal == null) {
            ret = super.getItemAt(index);
        } else {
            ret = internal.getItemAt(index);
        }
        if (ret == null) return null;
        return (ret instanceof XWalkNavigationItemBridge ? ((XWalkNavigationItemBridge) ret ) : new XWalkNavigationItemBridge(ret));
    }

    private Method getCurrentItemMethod;
    @Override
    public XWalkNavigationItemInternal getCurrentItem() {
        return (XWalkNavigationItemBridge)ReflectionHelper.getBridgeOrWrapper(ReflectionHelper.invokeMethod(
            getCurrentItemMethod, wrapper));
    }

    public XWalkNavigationItemBridge getCurrentItemSuper() {
        XWalkNavigationItemInternal ret;
        if (internal == null) {
            ret = super.getCurrentItem();
        } else {
            ret = internal.getCurrentItem();
        }
        if (ret == null) return null;
        return (ret instanceof XWalkNavigationItemBridge ? ((XWalkNavigationItemBridge) ret ) : new XWalkNavigationItemBridge(ret));
    }

    private Method canGoBackMethod;
    @Override
    public boolean canGoBack() {
        return (Boolean)ReflectionHelper.invokeMethod(
            canGoBackMethod, wrapper);
    }

    public boolean canGoBackSuper() {
        boolean ret;
        if (internal == null) {
            ret = super.canGoBack();
        } else {
            ret = internal.canGoBack();
        }
        
        return ret;
    }

    private Method canGoForwardMethod;
    @Override
    public boolean canGoForward() {
        return (Boolean)ReflectionHelper.invokeMethod(
            canGoForwardMethod, wrapper);
    }

    public boolean canGoForwardSuper() {
        boolean ret;
        if (internal == null) {
            ret = super.canGoForward();
        } else {
            ret = internal.canGoForward();
        }
        
        return ret;
    }

    private Method navigateDirectionInternalintMethod;
    @Override
    public void navigate(DirectionInternal direction, int steps) {
        ReflectionHelper.invokeMethod(
            navigateDirectionInternalintMethod, wrapper, ConvertDirectionInternal(direction), steps);
    }

    public void navigateSuper(DirectionInternal direction, int steps) {
        if (internal == null) {
            super.navigate(direction, steps);
        } else {
            internal.navigate(direction, steps);
        }
    }

    private Method getCurrentIndexMethod;
    @Override
    public int getCurrentIndex() {
        return (Integer)ReflectionHelper.invokeMethod(
            getCurrentIndexMethod, wrapper);
    }

    public int getCurrentIndexSuper() {
        int ret;
        if (internal == null) {
            ret = super.getCurrentIndex();
        } else {
            ret = internal.getCurrentIndex();
        }
        
        return ret;
    }

    private Method clearMethod;
    @Override
    public void clear() {
        ReflectionHelper.invokeMethod(
            clearMethod, wrapper);
    }

    public void clearSuper() {
        if (internal == null) {
            super.clear();
        } else {
            internal.clear();
        }
    }



    private void reflectionInit() throws NoSuchMethodException,
            ClassNotFoundException {
        Class<?> clazz = wrapper.getClass();
        enumDirectionClass = clazz.getClassLoader().loadClass("org.xwalk.core.XWalkNavigationHistory$Direction");
        enumDirectionClassValueOfMethod = enumDirectionClass.getMethod("valueOf", String.class);
        sizeMethod = ReflectionHelper.loadMethod(clazz, "size");
        hasItemAtintMethod = ReflectionHelper.loadMethod(clazz, "hasItemAt", int.class);
        getItemAtintMethod = ReflectionHelper.loadMethod(clazz, "getItemAt", int.class);
        getCurrentItemMethod = ReflectionHelper.loadMethod(clazz, "getCurrentItem");
        canGoBackMethod = ReflectionHelper.loadMethod(clazz, "canGoBack");
        canGoForwardMethod = ReflectionHelper.loadMethod(clazz, "canGoForward");
        navigateDirectionInternalintMethod = ReflectionHelper.loadMethod(clazz, "navigate", enumDirectionClass, int.class);
        getCurrentIndexMethod = ReflectionHelper.loadMethod(clazz, "getCurrentIndex");
        clearMethod = ReflectionHelper.loadMethod(clazz, "clear");

    }

    static {
        ReflectionHelper.registerConstructor("XWalkNavigationHistoryBridgeConstructor", "org.xwalk.core.XWalkNavigationHistory", Object.class);
    }

}
