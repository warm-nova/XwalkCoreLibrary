package org.xwalk.core.internal;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class XWalkJavascriptResultHandlerBridge extends XWalkJavascriptResultHandlerInternal {
    private final static String WRAPPER_CLASS = "org.xwalk.core.Object";
    private Object wrapper;

    public Object getWrapper() {
        return wrapper;
    }
    private XWalkJavascriptResultHandlerInternal internal = null;
    XWalkJavascriptResultHandlerBridge(XWalkJavascriptResultHandlerInternal internal) {
        this.internal = internal;
        this.wrapper = ReflectionHelper.createInstance("XWalkJavascriptResultHandlerBridgeConstructor", this);
        try {
          reflectionInit();
        } catch (Exception e) {
          ReflectionHelper.handleException(e);
        }
    }


    private Method confirmMethod;
    @Override
    public void confirm() {
        ReflectionHelper.invokeMethod(
            confirmMethod, wrapper);
    }

    public void confirmSuper() {
        if (internal == null) {
            super.confirm();
        } else {
            internal.confirm();
        }
    }

    private Method confirmWithResultStringMethod;
    @Override
    public void confirmWithResult(final String promptResult) {
        ReflectionHelper.invokeMethod(
            confirmWithResultStringMethod, wrapper, promptResult);
    }

    public void confirmWithResultSuper(final String promptResult) {
        if (internal == null) {
            super.confirmWithResult(promptResult);
        } else {
            internal.confirmWithResult(promptResult);
        }
    }

    private Method cancelMethod;
    @Override
    public void cancel() {
        ReflectionHelper.invokeMethod(
            cancelMethod, wrapper);
    }

    public void cancelSuper() {
        if (internal == null) {
            super.cancel();
        } else {
            internal.cancel();
        }
    }



    private void reflectionInit() throws NoSuchMethodException,
            ClassNotFoundException {
        Class<?> clazz = wrapper.getClass();
        confirmMethod = ReflectionHelper.loadMethod(clazz, "confirm");
        confirmWithResultStringMethod = ReflectionHelper.loadMethod(clazz, "confirmWithResult", String.class);
        cancelMethod = ReflectionHelper.loadMethod(clazz, "cancel");

    }

    static {
        ReflectionHelper.registerConstructor("XWalkJavascriptResultHandlerBridgeConstructor", "org.xwalk.core.XWalkJavascriptResultHandler", Object.class);
    }

}
