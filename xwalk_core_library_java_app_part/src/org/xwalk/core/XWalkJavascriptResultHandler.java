package org.xwalk.core;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;



public class XWalkJavascriptResultHandler   implements XWalkJavascriptResult {





    private final static String BRIDGE_CLASS = "org.xwalk.core.internal.XWalkJavascriptResultHandlerBridge";
    private Object bridge;

    Object getBridge() {
        return bridge;
    }
    public XWalkJavascriptResultHandler(Object bridge) {
        this.bridge = bridge;
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    private Method confirmMethod;

    public void confirm() {
        ReflectionHelper.invokeMethod(confirmMethod, bridge);
    }

    private Method confirmWithResultStringMethod;

    public void confirmWithResult(final String promptResult) {
        ReflectionHelper.invokeMethod(confirmWithResultStringMethod, bridge, promptResult);
    }

    private Method cancelMethod;

    public void cancel() {
        ReflectionHelper.invokeMethod(cancelMethod, bridge);
    }



    private void reflectionInit() throws NoSuchMethodException,
            ClassNotFoundException {
        Class<?> clazz = bridge.getClass();
        confirmMethod = ReflectionHelper.loadMethod(clazz, "confirmSuper");
        confirmWithResultStringMethod = ReflectionHelper.loadMethod(clazz, "confirmWithResultSuper", String.class);
        cancelMethod = ReflectionHelper.loadMethod(clazz, "cancelSuper");

    }



}
