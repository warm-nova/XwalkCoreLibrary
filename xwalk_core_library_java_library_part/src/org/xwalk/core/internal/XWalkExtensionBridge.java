package org.xwalk.core.internal;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class XWalkExtensionBridge extends XWalkExtensionInternal {
    private final static String WRAPPER_CLASS = "org.xwalk.core.Object";
    private Object wrapper;

    public Object getWrapper() {
        return wrapper;
    }


    public XWalkExtensionBridge(String name, String jsApi, Object wrapper) {
        super(name, jsApi);
        this.wrapper = wrapper;
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    public XWalkExtensionBridge(String name, String jsApi, String[] entryPoints, Object wrapper) {
        super(name, jsApi, entryPoints);
        this.wrapper = wrapper;
        try {
            reflectionInit();
        } catch (Exception e) {
            ReflectionHelper.handleException(e);
        }
    }

    private Method postMessageintStringMethod;
    @Override
    public void postMessage(int instanceID, String message) {
        ReflectionHelper.invokeMethod(
            postMessageintStringMethod, wrapper, instanceID, message);
    }

    public void postMessageSuper(int instanceID, String message) {
        super.postMessage(instanceID, message);
    }

    private Method broadcastMessageStringMethod;
    @Override
    public void broadcastMessage(String message) {
        ReflectionHelper.invokeMethod(
            broadcastMessageStringMethod, wrapper, message);
    }

    public void broadcastMessageSuper(String message) {
        super.broadcastMessage(message);
    }

    private Method onMessageintStringMethod;
    @Override
    public void onMessage(int instanceID, String message) {
        ReflectionHelper.invokeMethod(
            onMessageintStringMethod, wrapper, instanceID, message);
    }

    private Method onSyncMessageintStringMethod;
    @Override
    public String onSyncMessage(int instanceID, String message) {
        return (String)ReflectionHelper.invokeMethod(
            onSyncMessageintStringMethod, wrapper, instanceID, message);
    }



    private void reflectionInit() throws NoSuchMethodException,
            ClassNotFoundException {
        Class<?> clazz = wrapper.getClass();
        postMessageintStringMethod = ReflectionHelper.loadMethod(clazz, "postMessage", int.class, String.class);
        broadcastMessageStringMethod = ReflectionHelper.loadMethod(clazz, "broadcastMessage", String.class);
        onMessageintStringMethod = ReflectionHelper.loadMethod(clazz, "onMessage", int.class, String.class);
        onSyncMessageintStringMethod = ReflectionHelper.loadMethod(clazz, "onSyncMessage", int.class, String.class);

    }


}
