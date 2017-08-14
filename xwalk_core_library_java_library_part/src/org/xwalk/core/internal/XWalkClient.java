/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.xwalk.core.internal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.ValueCallback;
import android.webkit.WebResourceResponse;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * It's the Internal class to handle legacy resource related callbacks not
 * handled by XWalkResourceClientInternal.
 *
 * @hide
 */
public class XWalkClient {

    private Context mContext;
    private XWalkViewInternal mXWalkView;

    public XWalkClient(XWalkViewInternal view) {
        mContext = view.getContext();
        mXWalkView = view;
    }

    /**
     * Notify the host application that the renderer of XWalkViewInternal is hung.
     *
     * @param view The XWalkViewInternal on which the render is hung.
     */
    public void onRendererUnresponsive(XWalkViewInternal view) {
    }

    /**
     * Notify the host application that the renderer of XWalkViewInternal is no longer hung.
     *
     * @param view The XWalkViewInternal which becomes responsive now.
     */
    public void onRendererResponsive(XWalkViewInternal view) {
    }

    /**
     * Notify the host application that there have been an excessive number of
     * HTTP redirects. As the host application if it would like to continue
     * trying to load the resource. The default behavior is to send the cancel
     * message.
     *
     * @param view The XWalkViewInternal that is initiating the callback.
     * @param cancelMsg The message to send if the host wants to cancel
     * @param continueMsg The message to send if the host wants to continue
     * @deprecated This method is no longer called. When the XWalkViewInternal encounters
     *             a redirect loop, it will cancel the load.
     */
    @Deprecated
    public void onTooManyRedirects(XWalkViewInternal view, Message cancelMsg,
            Message continueMsg) {
        cancelMsg.sendToTarget();
    }

    /**
     * As the host application if the browser should resend data as the
     * requested page was a result of a POST. The default is to not resend the
     * data.
     *
     * @param view The XWalkViewInternal that is initiating the callback.
     * @param dontResend The message to send if the browser should not resend
     * @param resend The message to send if the browser should resend data
     */
    public void onFormResubmission(XWalkViewInternal view, Message dontResend,
            Message resend) {
        dontResend.sendToTarget();
    }

    /**
     * Notify the host application to update its visited links database.
     *
     * @param view The XWalkViewInternal that is initiating the callback.
     * @param url The url being visited.
     * @param isReload True if this url is being reloaded.
     */
    public void doUpdateVisitedHistory(XWalkViewInternal view, String url,
            boolean isReload) {
    }

    /**
     * Notify the host application that an SSL error occurred while loading a
     * resource, but the XWalkViewInternal chose to proceed anyway based on a
     * decision retained from a previous response to onReceivedSslError().
     * @hide
     */
    public void onProceededAfterSslError(XWalkViewInternal view, SslError error) {
    }

    /**
     * Notify the host application to handle a SSL client certificate
     * request (display the request to the user and ask whether to
     * proceed with a client certificate or not). The host application
     * has to call either handler.cancel() or handler.proceed() as the
     * connection is suspended and waiting for the response. The
     * default behavior is to cancel, returning no client certificate.
     *
     * @param view The XWalkViewInternal that is initiating the callback.
     * @param handler A ClientCertRequestHandler object that will
     *            handle the user's response.
     * @param host_and_port The host and port of the requesting server.
     *
     * @hide
     */
    // TODO: comment this method temporarily, will implemtent later when all
    //       dependencies are resovled.
    // public void onReceivedClientCertRequest(XWalkViewInternal view,
    //         ClientCertRequestHandler handler, String host_and_port) {
    //     handler.cancel();
    // }

    /**
     * Notify the host application to handle an authentication request. The
     * default behavior is to cancel the request.
     *
     * @param view The XWalkViewInternal that is initiating the callback.
     * @param handler The XWalkHttpAuthHandler that will handle the user's response.
     * @param host The host requiring authentication.
     * @param realm A description to help store user credentials for future
     *            visits.
     */
    public void onReceivedHttpAuthRequest(XWalkViewInternal view,
            XWalkHttpAuthHandler handler, String host, String realm) {
        if (view == null) return;

        final XWalkHttpAuthHandler haHandler = handler;
        LinearLayout layout = new LinearLayout(mContext);
        final EditText userNameEditText = new EditText(mContext);
        final EditText passwordEditText = new EditText(mContext);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPaddingRelative(10, 0, 10, 20);
        userNameEditText.setHint(R.string.http_auth_user_name);
        passwordEditText.setHint(R.string.http_auth_password);
        layout.addView(userNameEditText);
        layout.addView(passwordEditText);

        final Activity curActivity = mXWalkView.getActivity();
        AlertDialog.Builder httpAuthDialog = new AlertDialog.Builder(curActivity);
        httpAuthDialog.setTitle(R.string.http_auth_title)
                .setView(layout)
                .setCancelable(false)
                .setPositiveButton(R.string.http_auth_log_in, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String userName = userNameEditText.getText().toString();
                        String password = passwordEditText.getText().toString();
                        haHandler.proceed(userName, password);
                        dialog.dismiss();
                    }
                }).setNegativeButton(android.R.string.cancel, null)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        haHandler.cancel();
                    }
                }).create().show();
    }

    /**
     * Notify the host application that a request to automatically log in the
     * user has been processed.
     * @param view The XWalkViewInternal requesting the login.
     * @param realm The account realm used to look up accounts.
     * @param account An optional account. If not null, the account should be
     *                checked against accounts on the device. If it is a valid
     *                account, it should be used to log in the user.
     * @param args Authenticator specific arguments used to log in the user.
     */
    public void onReceivedLoginRequest(XWalkViewInternal view, String realm,
            String account, String args) {
    }

    // TODO(yongsheng): legacy method. Consider removing it?
    public void onLoadResource(XWalkViewInternal view, String url) {
    }
}
