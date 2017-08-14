// Copyright (c) 2013 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.core.internal;

import android.view.KeyEvent;

import org.chromium.base.CalledByNative;
import org.chromium.base.JNINamespace;
import org.chromium.components.web_contents_delegate_android.WebContentsDelegateAndroid;

@JNINamespace("xwalk")
abstract class XWalkWebContentsDelegate extends WebContentsDelegateAndroid {
    @CalledByNative
    public abstract boolean shouldOpenWithDefaultBrowser(String contentUrl);

    @CalledByNative
    public abstract boolean addNewContents(boolean isDialog, boolean isUserGesture);

    @CalledByNative
    public abstract void closeContents();

    @CalledByNative
    public abstract void activateContents();

    @CalledByNative
    public abstract void rendererUnresponsive();

    @CalledByNative
    public abstract void rendererResponsive();

    @CalledByNative
    public abstract void handleKeyboardEvent(KeyEvent event);

    @CalledByNative
    public abstract boolean shouldOverrideRunFileChooser(
            int processId, int renderId, int mode,
            String acceptTypes, boolean capture);

    @CalledByNative
    public void updatePreferredSize(int widthCss, int heightCss) {
    }

    @CalledByNative
    public void toggleFullscreen(boolean enterFullscreen) {
    }

    @CalledByNative
    public boolean isFullscreen() {
        return false;
    }
}
