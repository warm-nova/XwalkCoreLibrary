package org.xwalk.core;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;




/**
 * This class represents an extension and could be implemented by callers.
 */
public abstract class XWalkExtension  {





    private final static String BRIDGE_CLASS = "org.xwalk.core.internal.XWalkExtensionBridge";
    private Object bridge;

    Object getBridge() {
        return bridge;
    }


    /**
     * Constructor with name and javascript API.
     * @param name  the exposed namespace.
     * @param jsApi the string of javascript API.
     * @since 2.1
     */
    public XWalkExtension(String name, String jsApi) {

        bridge = ReflectionHelper.createInstance("XWalkExtensionInternalStringStringConstructor", name, jsApi, this);
        try {
            reflectionInit();
        } catch(Exception e) {
            ReflectionHelper.handleException(e);
        }

    }



    /**
     * Constructor with name, javascript API and entry points.
     * @param name the exposed namespace.
     * @param jsApi the string of javascript API.
     * @param entryPoints Entry points are used when the extension needs to
     *                    have objects outside the namespace that is
     *                    implicitly created using its name.
     * @since 2.1
     */
    public XWalkExtension(String name, String jsApi, String[] entryPoints) {

        bridge = ReflectionHelper.createInstance("XWalkExtensionInternalStringStringString[]Constructor", name, jsApi, entryPoints, this);
        try {
            reflectionInit();
        } catch(Exception e) {
            ReflectionHelper.handleException(e);
        }

    }

    private Method postMessageintStringMethod;


    /**
     * Send message to an instance.
     * @param instanceID the id of instance.
     * @param message the message.
     * @since 2.1
     */
    public void postMessage(int instanceID, String message) {
        ReflectionHelper.invokeMethod(postMessageintStringMethod, bridge, instanceID, message);
    }

    private Method broadcastMessageStringMethod;


    /**
     * Broadcast message to all extension instances.
     * @param message the message.
     * @since 2.1
     */
    public void broadcastMessage(String message) {
        ReflectionHelper.invokeMethod(broadcastMessageStringMethod, bridge, message);
    }



    /**
     * Notify the extension that the async message is received.
     * @param instanceID the id of instance.
     * @param message the received message.
     * @since 2.1
     */
    public abstract void onMessage(int instanceID, String message);



    /**
     * Notify the extension that the sync message is received.
     * @param instanceID the id of instance.
     * @param message the received message.
     * @since 2.1
     */
    public abstract String onSyncMessage(int instanceID, String message);



    private void reflectionInit() throws NoSuchMethodException,
            ClassNotFoundException {
        Class<?> clazz = bridge.getClass();
        postMessageintStringMethod = ReflectionHelper.loadMethod(clazz, "postMessageSuper", int.class, String.class);
        broadcastMessageStringMethod = ReflectionHelper.loadMethod(clazz, "broadcastMessageSuper", String.class);

    }


    static {
        ReflectionHelper.registerConstructor("XWalkExtensionInternalStringStringConstructor", "org.xwalk.core.internal.XWalkExtensionBridge", String.class, String.class, Object.class);
        ReflectionHelper.registerConstructor("XWalkExtensionInternalStringStringString[]Constructor", "org.xwalk.core.internal.XWalkExtensionBridge", String.class, String.class, String[].class, Object.class);

    }

}
