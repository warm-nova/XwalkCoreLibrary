
package org.xwalk.core;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;



/**
 * This interface is used when XWalkUIClient offers a JavaScript
 * modal dialog (alert, beforeunload or confirm) to enable the client to
 * handle the dialog in their own way. XWalkUIClient will offer an object
 * that implements this interface to the client and when the client has handled
 * the dialog, it must either callback with confirm() or cancel() to allow
 * processing to continue.
 */
public interface XWalkJavascriptResult {

    /**
     * Handle a confirm with a result from caller.
     * @param result the result string from caller.
     * @since 1.0
     */
    public void confirmWithResult(String result);



    /**
     * Handle a confirm without a result.
     * @since 1.0
     */
    public void confirm();



    /**
     * Handle the result if the caller cancelled the dialog.
     * @since 1.0
     */
    public void cancel();


}
